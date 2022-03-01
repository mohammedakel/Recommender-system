package edu.brown.cs.student.main.commands;

import edu.brown.cs.student.main.CSVParser.CSVParser;
import edu.brown.cs.student.main.REPL;
import edu.brown.cs.student.main.commands.Command;
import edu.brown.cs.student.main.stars.Stars;

import java.io.IOException;
import java.util.List;

/**
 * Run stars [filepath]
 *
 * Loads list of stars
 *
 * Implements REPL and Command interface
 *
 * @author kkmarcus
 */
public class LoadStars implements REPL, Command {

  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  public LoadStars() {
    String commandName = "stars";
    this.addCommands(commandName, this);
  }

  /**
   * execute must check to see if file exists, otherwise
   * throw error and check if there are the correct amount and type of args
   *
   * @param args (array of strings)
   */
  public void execute(String[] args) {
    try {
      if (args.length == 1) {
        System.out.println("ERROR: No FileName");
      } else if (args.length != 2) {
        System.out.println("ERROR: Incorrect amount of args: run load_kd <path/to/file.csv>");
      }else {
        String filePath = args[1];
        Stars star = null;
        CSVParser newParser = new CSVParser(filePath, Stars::new, true);
        newParser.readLine();
        List<Stars> stars = newParser.getListOfObjects();
        REPL.addCommandObject("stars", stars);
        int t = stars.size();
        System.out.println("Read " + t + " stars from " + filePath); // print message
      }
    } catch (IOException e) {
      System.out.println("ERROR: invalid file input");
    }
  }
}
