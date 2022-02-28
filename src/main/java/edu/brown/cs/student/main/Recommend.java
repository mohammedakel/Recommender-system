package edu.brown.cs.student.main;

import java.io.IOException;

/**
 * Run recommend [num_recomendations] [student_id]
 *
 * After loading student data, generate recommendations for a particular student’s team
 *
 * To get as output (where each [matched…] is one of k student IDs in the dataset):
 * [matched_student_id_1] [matched_student_id_2] . . . [matched_student_id_k]
 *
 * Acceptance criterion: the student IDs are presented in preference-order for the input
 * student, according to the loaded preference data.
 *
 * Implements REPL and Command interface
 *
 * @author kkmarcus
 */
public class Recommend implements REPL, Command{
  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  Recommend() {
    String commandName = "recommend";
    this.addCommands(commandName, this);
  }

  /**
   * execute must check to see if file exists, otherwise
   * throw error and check if there are the correct amount and type of args
   *
   * @param args (array of strings)
   */
  public void execute(String[] args) throws IOException {
    if (REPL.getCommandObject("recsys_load") == null) {
      System.out.println("ERROR: No student data loaded. First run recsys_load <4 CSVs>");
    }
    else if (args.length == 1) {
      System.out.println("ERROR: No FileName");
    } else if (args.length != 2) {
      System.out.println("ERROR: Incorrect amount of args: run load_kd <path/to/file.csv>");
    } else {

    }
  }
}
