package edu.brown.cs.student.main;

import java.io.IOException;
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
    List headers = (List) REPL.getCommandObject("headers_load");
    if (headers == null){
      System.out.println("ERROR: No headers loaded. First run headers_load <header_types.csv PATH>");
    }
    else if (args.length == 1) {
      System.out.println("ERROR: No FileName");
    } else if (args.length != 5) {
      System.out.println("ERROR: Incorrect amount of args: run recsys_load <4 provideed CSVs>");
    } else {
      REPL.addCommandObject("recsys_load", null);
    }
  }
}
