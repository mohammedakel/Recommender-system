package edu.brown.cs.student.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Run recsys_load [4 provideded CSVs] or recsys_load API-DB <data.sqlite3 PATH>
 * <p>
 * Only when headers are loaded, load consolidated student data from the
 * 4 provided CSVs into a k-d tree and Bloom filters
 * <p>
 * Also can be used via API-DB
 * <p>
 * Implements REPL and Command interface
 * <p>
 * Output: (where k is the number of students in the dataset):
 * Loaded Recommender with k student(s).
 * <p>
 * Acceptance criterion: the data are loaded into a suitable data structure
 * which is accessible for use by other REPL commands.
 *
 * @author kkmarcus
 */
public class LoadRecSys implements REPL, Command {

  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  LoadRecSys() {
    String commandName = "recsys_load";
    this.addCommands(commandName, this);
  }

  /**
   * execute must check to see if file exists, otherwise
   * throw error and check if there are the correct amount and type of args
   *
   * @param args (array of strings)
   */
  public void execute(String[] args) throws IOException {
    if (args.length != 3 && args.length != 5) { // if 3, API-DB, if 5, CSV
      System.out.println(
          "ERROR: Incorrect amount of args: run recsys_load CSV <4 provided CSVs> or recsys_load API-DB <data.sqlite3 PATH>");
    } else {
      HashMap<String, String> headers =
          (HashMap<String, String>) REPL.getCommandObject("headers_load");
      List<ApiStudent> apiStudentList =
          (List<ApiStudent>) REPL.getCommandObject("api_aggregate_all");
      HashMap<Integer, DatabaseStudent> dbStudentList =
          (HashMap<Integer, DatabaseStudent>) REPL.getCommandObject("add_students");
      if (args.length == 3) { // API-DB
        if (apiStudentList == null || apiStudentList.isEmpty()) {
          System.out.println(
              "ERROR: No students loaded from API. First run api_aggregate_all");
          return;
        }
        if (dbStudentList == null || dbStudentList.isEmpty()) {
          System.out.println(
              "ERROR: No students loaded from DataBase. First run add_students");
          return;
        }
        String dataSource = args[1];
        String filePath = args[2];
        loadStudentsAPIDB(filePath, apiStudentList, dbStudentList);
      } else { // CSV
        if (headers == null) {
          System.out.println(
              "ERROR: No headers loaded. First run headers_load <header_types.csv PATH>");
          return;
        }
        String filePath = args[1]; // ignore other CSVs
        getStudentsFromCSV(filePath);
      }

    }
  }

  /**
   * Uses list of APIStudents and list of DBStudents to aggregate data and create list of students with combined
   * attributes and loads list of students into recsys_load data object to be used by recommend
   *
   * @param filePath
   * @param apiStudentList list of APIStudents
   * @param dbStudentList  list of DBStudents w/ attributes id, strengths, weaknesses,skill, interests
   * @throws IOException
   */
  public void loadStudentsAPIDB(String filePath, List<ApiStudent> apiStudentList,
                                HashMap<Integer, DatabaseStudent> dbStudentList)
      throws IOException {
    System.out.println("using api-db to get students");

    List<Student> students = new ArrayList<>();
    List<KdTreeNode> nodes = new ArrayList<>();
    // loop through APIStudent list
    for (ApiStudent apiStudent : apiStudentList) {
      String[] attributes = apiStudent.getStudentAttributes(); // get string array of attributes
      int studentID = Integer.parseInt(attributes[0]);
      DatabaseStudent currDBStudent =
          dbStudentList.get(studentID); // get student with same student id from db student list
      attributes[14] = listToString(currDBStudent.getStrengths());  // set attributes
      attributes[15] = listToString(currDBStudent.getWeaknesses());
      attributes[16] = currDBStudent.getSkill();
      attributes[17] = listToString(currDBStudent.getInterests());
      Student newStudent = new Student(attributes); // create student with aggregated data
      students.add(newStudent); // add to list
      nodes.add(newStudent); // create node
    }

    System.out.println("Loaded Recommender with " + students.size() + " student(s).");
    REPL.addCommandObject("recsys_load_students", students);
    Tree studentTree = new Tree(nodes); // create new tree

    loadStudentsBFKD(studentTree, students); // load bf and kd tree with student info
  }

  /**
   * Helper method that turns List<String> to a single string
   *
   * @param stringList
   * @return
   */
  public String listToString(List<String> stringList) {
    String newString = String.join(",", stringList);
    return newString;
  }

  /**
   * Used for recsys_load CSV. Creates parser, gets list of students and nodes from parsed data
   *
   * @param filePath
   * @throws IOException
   */
  public void getStudentsFromCSV(String filePath) throws IOException {
    CSVParser newParser = new CSVParser(filePath, Student::new, true); // instantiate parser w type of object specified
    newParser.readLine();
    List<Student> students = newParser.getListOfObjects();
    List<KdTreeNode> studentNodes = newParser.getListOfObjects();
    Tree studentTree = new Tree(studentNodes); // create new tree
    loadStudentsBFKD(studentTree, students); // load bf and kd tree with student info
  }

  /**
   * Used by API-DB and CSV call, takes in Tree and Students, creates Bloom Filter and KDTree to be used by
   * recommend command
   *
   * @param studentTree
   * @param students
   */
  public void loadStudentsBFKD(Tree studentTree, List<Student> students) {
    REPL.addCommandObject("load_kd", studentTree);
    System.out.println("Using quantitative data to create kd_tree");

    BloomFiltersLoader bloomLoader =
        new BloomFiltersLoader(students); // create new BloomFiltersLoader
    HashMap<String, BloomFilterBuilder> idsToBlooms = bloomLoader.loadAllBlooms();
    System.out.println("Using qualitative data to create bf");

    REPL.addCommandObject("load_bf",
        idsToBlooms);

    System.out.println("Loaded Recommender with " + students.size() + " student(s).");
    REPL.addCommandObject("recsys_load_bf", idsToBlooms); // store bf
    REPL.addCommandObject("recsys_load_kdtree", studentTree); // store kd tree
    REPL.addCommandObject("recsys_load_students", students); // store student list
  }
}
