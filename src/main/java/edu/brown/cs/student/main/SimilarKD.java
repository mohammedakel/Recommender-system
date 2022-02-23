package edu.brown.cs.student.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Run similar_kd [k] [some_user_id]
 * <p>
 * similar_kd print out the user_ids of the most similar k users [closest in distance of years experience,
 * weekly available hours, and software engineering confidence]
 * <p>
 * <p>
 * It implements the REPL and Command interface, so it must add itself to the
 * commands hashMap and implement execute.
 *
 * @author kkmarcus
 */
public class SimilarKD implements REPL, Command {

  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  SimilarKD() {
    String commandName = "similar_kd";
    this.addCommands(commandName, this);
  }

  /**
   * execute must check if there are the correct amount and type of args
   * and call neighbors method
   *
   * @param args (array of strings)
   */
  public void execute(String[] args) {
    Tree studentTree = (Tree) REPL.getCommandObject("load_kd");

    // check if students have been loaded previously
    if (studentTree == null) {
      System.out.println("ERROR: No student data, run load_kd <path/to/file.csv> first");
    } else if (args.length != 3) {
      System.out.println("ERROR: Incorrect amount of args, run similar_kd <k> <some_user_id>");
    } else {
      try {
        int k = Integer.parseInt(args[1]);
        int user_id = Integer.parseInt((args[2]));
        ArrayList<Integer> neighbors = studentTree.findNeighbor(k, user_id);
        printNeighbors(neighbors);
      } catch (NumberFormatException e) {
        System.out.println("ERROR: Invalid arg(s). <k> and <some_user_id> must be Integers");
      }
    }
  }

  /**
   * Prints out the user_ids of the most similar k users
   *
   * @param neighbors list of user_ids
   */
  public void printNeighbors(ArrayList<Integer> neighbors) {
    if (!neighbors.isEmpty()) {
      for (int i = 0; i < neighbors.size(); i++) {
        System.out.println(neighbors.get(i));
      }
    }
  }
}
