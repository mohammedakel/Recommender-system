package edu.brown.cs.student.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Run recsys_load [4 provideded CSVs]
 *
 * Only when headers are loaded, load consolidated student data from the
 * 4 provided CSVs into a k-d tree and Bloom filters
 *
 * Implements REPL and Command interface
 *
 * Output: (where k is the number of students in the dataset):
 * Loaded Recommender with k student(s).
 *
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
    HashMap<String, String> headers =
        (HashMap<String, String>) REPL.getCommandObject("headers_load");
    if (headers == null){
      System.out.println("ERROR: No headers loaded. First run headers_load <header_types.csv PATH>");
    }
    else if (args.length == 1) {
      System.out.println("ERROR: No FileName");
    } else if (args.length != 2) { // change to 5 later on
      System.out.println("ERROR: Incorrect amount of args: run recsys_load <4 provided CSVs>");
    } else {
      String filePath = args[1];
      loadStudents(filePath);
    }
  }

  /**
   * Helper method that loads kdtree and bf with correct attributes.
   * @param filePath to create Students from
   * @throws IOException
   */
  public void loadStudents(String filePath) throws IOException {
    CSVParser newParserKDTree = new CSVParser(filePath, Student::new, true, true); // instantiate parser w type of object specified
    newParserKDTree.readLine();

    List<KdTreeNode> students = newParserKDTree.getListOfObjects(); // get list of KdTreeNodes
    Tree studentTree = new Tree(students); // create new tree
    REPL.addCommandObject("load_kd", studentTree);
    System.out.println("Using quantitative data to create kd_tree");

    CSVParser newParserBF = new CSVParser(filePath, Student::new, true, true); // instantiate parser w type of object specified
    newParserBF.readLine();
    System.out.println("Using qualitative data to create bf");

    System.out.println("Loaded Recommender with " + students.size() + " student(s).");

    REPL.addCommandObject("recsys_load", null);
  }

}
