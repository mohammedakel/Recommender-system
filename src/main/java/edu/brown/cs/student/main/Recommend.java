package edu.brown.cs.student.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Run recommend [num_recommendations] [student_id]
 * <p>
 * After loading student data, generate recommendations for a particular student’s team
 * <p>
 * To get as output (where each [matched…] is one of k student IDs in the dataset):
 * [matched_student_id_1] [matched_student_id_2] . . . [matched_student_id_k]
 * <p>
 * Acceptance criterion: the student IDs are presented in preference-order for the input
 * student, according to the loaded preference data.
 * <p>
 * Implements REPL and Command interface
 *
 * @author kkmarcus
 */
public class Recommend implements REPL, Command {

  HashMap<Integer, Double> kd_distances;
  HashMap<Integer, Double> bf_distances;
  Integer num_recommendations;
  Integer student_id;

  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  public Recommend() {
    String commandName = "recommend";
    this.addCommands(commandName, this);
    this.bf_distances = new HashMap<>();
    this.kd_distances = new HashMap<>();
  }

  /**
   * execute must check to see if file exists, otherwise
   * throw error and check if there are the correct amount and type of args
   *
   * @param args (array of strings)
   */
  public void execute(String[] args) throws IOException {
    if (REPL.getCommandObject("recsys_load_students") == null) {
      System.out.println(
          "ERROR: No student data loaded. First run recsys_load <4 CSVs> or recsys_load API-DB <data.sqlite3 PATH>");
    } else if (args.length != 3) {
      System.out.println(
          "ERROR: Incorrect amount of args: run recommend <num_recommendations> <student_id>");
    } else {
      try {
        num_recommendations = Integer.parseInt(args[1]);
        if (num_recommendations < 0) {
          System.out.println("ERROR: Number on recommendations must be non-negative");
          return;
        }
        student_id = Integer.parseInt(args[2]);
        List<Student> students = (List<Student>) REPL.getCommandObject("recsys_load_students"); // get list of students that was loaded from recsys_load
        int numStudents = students.size();
        boolean studentExists = false;
        for (Student currStudent : students) { // check if studentID exists in loaded data
          if (currStudent.getId() == student_id) {
            studentExists = true;
          }
        }
        if (!studentExists) {
          System.out.println("ERROR: No student with studentID: " + student_id + " exists.");
          return;
        }
        if (num_recommendations == 0) {
          return;
        }
        if (numStudents <= num_recommendations) {
          System.out.println("Cannot recommend " + num_recommendations + " students, only " + numStudents + " students' data loaded." );
          num_recommendations = numStudents - 1; // don't include given student id
          System.out.println("Recommending " + num_recommendations + " students for student " + student_id + " instead...");
        } else {
          System.out.println("Recommending " + num_recommendations + " students for student " + student_id + "...");
        }
        recommendationAlgorithm(); // create recommendations

      } catch (NumberFormatException e) {
        System.out.println(
            "ERROR: Incorrect type of argument(s). num_recommendations and studentID must be an integers");
        return;
      }
    }
  }


  /**
   * The recommendationAlgorithm takes the union of the set of students
   * recommended by the bloom filter and kd tree. The distances are normalized,
   * and the average of these distances is computer and assigned as the new value
   * for that student. Finally, these new distances are used to sort
   */
  public void recommendationAlgorithm() {

    // get BloomFilter from stored data
    HashMap<String, BloomFilterBuilder> idsToBlooms =
        (HashMap<String, BloomFilterBuilder>) REPL.getCommandObject("recsys_load_bf");
    // create similarity generator
    SimilarityGenerator generator = new SimilarityGenerator(idsToBlooms, num_recommendations);

    // get KDTree from stored data
    Tree studentTree = (Tree) REPL.getCommandObject("recsys_load_kdtree");
    // create list of nearest neighbors using tree
    ArrayList<Integer> neighbors = studentTree.findNeighbor(num_recommendations, student_id);

    // get the map containing studentIDs and their respective distances from the target student
    // for both kd tree and bloom filter
    kd_distances = studentTree.getDistanceIDMap();
    bf_distances = generator.getDistanceMap();

    // Since the bit vector distance and Euclidean distance are not proportional, need to
    // make them comparable using min-max normalization
    normalizeValues(kd_distances);
    normalizeValues(bf_distances);

    // get the set of student ids from both maps with recommended students
    Set<Integer> student_ids = kd_distances.keySet();
    Set<Integer> student_ids_2 = bf_distances.keySet();

    // take the union of this set, so we can check all students that were recommended
    Set<Integer> unionSet = new HashSet<>();
    unionSet.addAll(student_ids);
    unionSet.addAll(student_ids_2);

    // for each student, take an average of both normalized distances
    // (the bit vector distance and the Euclidean distance), weighting them equally
    // 0.5 * <AND distance for student 1> + 0.5 * <Euclidean distance for student 1>
    HashMap<Integer, Double> normalizedValues = new HashMap<>();
    Set<Double> distances = new HashSet<>(); // keep track of distances to use for tiebreak
    int t = 0;
    // loop through students in union set
    for (Integer student : unionSet) {
      Double KDDistance = 0.0;
      Double BFDistance = 0.0;
      if (student_ids.contains(student)) {
        KDDistance = kd_distances.get(student); // check if student is in kd recommended students list
      }
      if (student_ids_2.contains(student)){
        BFDistance = bf_distances.get(student); // check if student is in bf recommended students list
      }

      double average = (0.5 * KDDistance ) + (0.5 * BFDistance); // calculate average

      if (distances.contains(average) && t == num_recommendations -1 ) { // tiebreak if at last recommendation
        average = (0.4 * KDDistance ) + (0.6 * BFDistance); // weight qualitative data more
        if (distances.contains(average)) {
          average = (0.3 * KDDistance ) + (0.7 * BFDistance); // weight qualitative data more if still tied
        }
      }
      distances.add(average);
      normalizedValues.put(student, average); // put average value in map with student id as key
      t++;
    }

    //  rank and select from the pool of recommended students based on
    // the composite distances
    List<Map.Entry<Integer, Double>> normalizedList = new ArrayList<>(normalizedValues.entrySet());
    Collections.sort(normalizedList, Map.Entry.comparingByValue()); // sort using value

    printRecommendations(normalizedList); // print the recommendations
    REPL.addCommandObject("recommend", normalizedList);
  }

  /**
   * Helper class that prints out the recommended students with their student ids and distances.
   * @param recs
   */
  public void printRecommendations(List<Map.Entry<Integer, Double>> recs) {
    for (int i = 0; i < num_recommendations && i < recs.size(); i++) {
      System.out.println("Student " + recs.get(i).getKey() + " : " + recs.get(i).getValue());
    }
  }

  /**
   * https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap for entry set
   *
   * @param map of student ids and distances
   */
  public void normalizeValues(HashMap<Integer, Double> map) {
    double min = getMin(map.values()); // get min of distances
    double max = getMax(map.values()); // get max of all distances

    for (HashMap.Entry<Integer, Double> entry : map.entrySet()) {
      // min-max scaling/normalization, https://en.wikipedia.org/wiki/Feature_scaling
      Double normalizedValue = (entry.getValue() - min) / (max - min);
      // replace student ids distance with new normalized value
      map.replace(entry.getKey(), normalizedValue);
    }
  }

  /**
   * Helper method for normalize values, finds min of distances
   * @param values to search for min
   * @return min value
   */
  public static double getMin(Collection<Double> values) {
    double min = Double.MAX_VALUE;
    for (double currVal : values) {
      if (currVal < min) {
        min = currVal;
      }
    }
    return min;
  }

  /**
   * Helper method for normalize values, finds max of distances
   * @param values to search for max
   * @return max value
   */
  public static double getMax(Collection<Double> values) {
    double max = Double.MIN_VALUE;
    for (double currVal : values) {
      if (currVal > max) {
        max = currVal;
      }
    }
    return max;
  }

  /**
   * Used for testing, prints out results, uses boolean BF to print tuples specifically,
   * otherwise generic
   * @param toEnumerate collection to iterate through
   * @param BF
   */
  public void printResults(Collection toEnumerate, boolean BF) {
    for (Object currObject : toEnumerate) {
      if (BF) {
        SBLTuple curr = (SBLTuple) currObject;
        System.out.println(curr.id);
      } else {
        System.out.println(currObject);
      }
    }
  }
}
