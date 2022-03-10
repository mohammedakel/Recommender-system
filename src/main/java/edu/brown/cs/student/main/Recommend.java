package edu.brown.cs.student.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Run recommend [num_recomendations] [student_id]
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
          "ERROR: Incorrect amount of args: run recommend <num_recomendations> <student_id>");
    } else {
      try {
        num_recommendations = Integer.parseInt(args[1]);
        if (num_recommendations < 0) {
          System.out.println("ERROR: Number on recommendations must be nonnegative");
          return;
        }
        student_id = Integer.parseInt(args[2]);
        List<Student> students = (List<Student>) REPL.getCommandObject("recsys_load_students");
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
        System.out.println("Recommending " + num_recommendations + " students for student " + student_id + "...");
        recommendationAlgorithm();

      } catch (NumberFormatException e) {
        System.out.println(
            "ERROR: Incorrect type of argument(s). num_recommendations and studentID must be an integers");
        return;
      }

      // TO DO: implement recommendation system
    }
  }


  public void recommendationAlgorithm() {

    HashMap<String, BloomFilterBuilder> idsToBlooms =
        (HashMap<String, BloomFilterBuilder>) REPL.getCommandObject("recsys_load_bf");
    SimilarityGenerator generator = new SimilarityGenerator(idsToBlooms, num_recommendations);
    //PriorityQueue<SBLTuple> similarityResult = generator.findSimilar(student_id);

    Tree studentTree = (Tree) REPL.getCommandObject("recsys_load_kdtree");
    ArrayList<Integer> neighbors = studentTree.findNeighbor(num_recommendations, student_id);

    kd_distances = studentTree.getDistanceIDMap();
    bf_distances = generator.getDistanceMap();

//    System.out.println("KDTree Results: ");
//    printResults(neighbors, false);

//    System.out.println("DistanceMap Results: ");
//    printResults(kd_distances.keySet(), false);

//
//    System.out.println("BloomFilter Results: ");
//    printResults(similarityResult, true);

//    System.out.println("BF Distances Map Results: ");
//    printResults(bf_distances.keySet(), false);


//
//    // need to get just the nearest ones
//    Set<Integer> neighborsSet = new HashSet<>();
//    neighborsSet.addAll(neighbors);
//    Set<Integer> BFSet = generator.getStudentIds();
//
//    neighborsSet.addAll(BFSet); // get union of student IDS


//
//    System.out.println("kd_distances.size: " + kd_distances.size());
//    System.out.println("bf_distances.size: " + bf_distances.size());




//    HashMap<Integer, Double> combinedRecStudents =

    // 1. you will want to select from the union of these two sets of students by considering
    // both the bit vector distance and Euclidean distance from the target for each student


    // 2. Since these distances are not proportional, you will want to make them comparable
    // using min-max normalization
//    HashMap<Integer, Double> new_kd_distances = normalizeValues(kd_distances);
//    HashMap<Integer, Double> new_bf_distances = normalizeValues(bf_distances);
    normalizeValues(kd_distances);
    normalizeValues(bf_distances);

//    System.out.println("AFTER DistanceMap Results: ");
//    printResults(kd_distances.values(), false);

//    System.out.println("AFTER BF Distances Map Results: ");
//    printResults(bf_distances.values(), false);

    Set<Integer> student_ids = kd_distances.keySet();
    Set<Integer> student_ids_2 = bf_distances.keySet();

//    System.out.println("student_ids.size: " + student_ids.size());
//    System.out.println("student_ids2.size: " + student_ids_2.size());

    Set<Integer> unionSet = new HashSet<>();
    unionSet.addAll(student_ids);
    unionSet.addAll(student_ids_2);
    //student_ids.addAll(student_ids_2); // select union of these two sets of students

//    System.out.println("UNION Size: " + unionSet.size());
//
//    System.out.println("Union Results: ");
//    printResults(unionSet, false);

    // 3. for each student, you will need to take an average of both normalized distances
    // (i.e., the bit vector distance and the Euclidean distance), weighting them equally
    // 0.5 * <AND distance for student 1> + 0.5 * <Euclidean distance for student 1>
    // 4. you can rank and confidently select from the pool of recommended students based on
    // these composite distances
    HashMap<Integer, Double> normalizedValues = new HashMap<>();
    Set<Double> distances = new HashSet<>();
    for (Integer student : unionSet) {
      Double KDDistance = 0.0;
      Double BFDistance = 0.0;
      if (student_ids.contains(student)) {
        KDDistance = kd_distances.get(student);
      }
      if (student_ids_2.contains(student)){
        BFDistance = bf_distances.get(student);
      }

      double average = (0.5 * KDDistance ) + (0.5 * BFDistance);

      if (distances.contains(average)) { // tiebreak
        average = (0.4 * KDDistance ) + (0.6 * BFDistance); // weight qualitative data more
        if (distances.contains(average)) {
          average = (0.3 * KDDistance ) + (0.7 * BFDistance); // weight qualitative data more
        }
      }
      distances.add(average);
      normalizedValues.put(student, average);
    }

    List<Map.Entry<Integer, Double>> normalizedList = new ArrayList<>(normalizedValues.entrySet());
    Collections.sort(normalizedList, Map.Entry.comparingByValue());

    printRecommendations(normalizedList);

  }

  public void printRecommendations(List<Map.Entry<Integer, Double>> recs) {
    for (int i = 0; i < num_recommendations && i < recs.size(); i++) {
      System.out.println("Student " + recs.get(i).getKey() + " : " + recs.get(i).getValue());
    }
  }
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

  /**
   * https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap for entry set
   *
   * @param map
   * @return new map with normalized values
   */
  public void normalizeValues(HashMap<Integer, Double> map) {
//    HashMap<Integer, Double> normalizedMap = new HashMap<>();
    double min = getMin(map.values());
    double max = getMax(map.values());

    for (HashMap.Entry<Integer, Double> entry : map.entrySet()) {
      // min-max scaling/normalization, https://en.wikipedia.org/wiki/Feature_scaling
      Double normalizedValue = (entry.getValue() - min) / (max - min);
//      System.out.println("normalizedVal: " + normalizedValue);
      map.replace(entry.getKey(), normalizedValue);
//      normalizedMap.put(entry.getKey(), normalizedValue);
//      System.out.println("entry.getKey: " + entry.getValue());
    }
//    return normalizedMap;
  }


  public static double getMin(Collection<Double> values) {
    double min = Double.MAX_VALUE;
    for (double currVal : values) {
      //System.out.println("currVal: " + currVal);
      if (currVal < min) {
        min = currVal;
      }
    }
    //System.out.println("min: " + min);
    return min;
  }

  /**
   * Returns the maximum value
   *
   * @param values
   * @return
   */
  public static double getMax(Collection<Double> values) {
    double max = Double.MIN_VALUE;
    for (double currVal : values) {
      //System.out.println("currVal: " + currVal);
      if (currVal > max) {
        max = currVal;
      }
    }
    //System.out.println("max: " + max);
    return max;
  }
}
