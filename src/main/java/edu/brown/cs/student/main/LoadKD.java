package edu.brown.cs.student.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Run load_kd [path/to/file.csv]
 *
 * Loads in the student data to a KD tree
 *
 * Implements REPL and Command interface
 *
 * @author kkmarcus
 */
public class LoadKD implements REPL, Command {

  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  LoadKD() {
    String commandName = "load_kd";
    this.addCommands(commandName, this);
  }

  /**
   * execute must check to see if file exists, otherwise
   * throw error and check if there are the correct amount and type of args
   *
   * @param args (array of strings)
   */
  public void execute(String[] args) throws IOException {
    if (args.length == 1) {
      System.out.println("ERROR: No FileName");
    } else if (args.length != 2) {
      System.out.println("ERROR: Incorrect amount of args: run load_kd <path/to/file.csv>");
    } else {
      String filePath = args[1];
      CSVParser newParser = new CSVParser(filePath, Student::new, true); // instantiate parser w type of object specified
      newParser.readLine();
      List<KdTreeNode> students = newParser.getListOfObjects(); // get list of KdTreeNodes
      System.out.println("Read " + students.size() + " students from " + filePath);
      Tree studentTree = new Tree(students); // create new tree
      REPL.addCommandObject("load_kd", studentTree); // add object to REPL hashmap
    }
  }
}
