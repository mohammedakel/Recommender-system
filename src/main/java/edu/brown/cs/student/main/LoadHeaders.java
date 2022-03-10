package edu.brown.cs.student.main;

import java.io.IOException;
import java.util.HashMap;

/**
 * Run headers_load [header_types.csv PATH]
 *
 * Load the CSV headers and their types
 *
 * Acceptance criterion: the headers are loaded into a suitable
 * data structure (hashmap) which is accessible for use by other REPL commands
 *
 * Implements REPL and Command interface
 *
 * @author kkmarcus
 */
public class LoadHeaders implements REPL, Command{

  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  LoadHeaders() {
    String commandName = "headers_load";
    this.addCommands(commandName, this);
  }

  /**
   * Execute for load_headers checks for the correct amount of args, and then creates
   * a hashmap of header names and types, that is stored by the REPL.
   * @param args (array of strings)
   * @throws IOException
   */
  public void execute(String[] args) throws IOException {
    if (args.length == 1) {
      System.out.println("ERROR: No FileName");
    } else if (args.length != 2) {
      System.out.println("ERROR: Incorrect amount of args: run headers_load <header_types.csv PATH");
    } else {
      String filePath = args[1];
      CSVParser newParser = new CSVParser(filePath,null); // instantiate parser w type of object specified
      newParser.readLine();
      HashMap headers = newParser.getHeaderTypes(); // method that returns HashMap<Header Name, Type (Qualitative, Quantitative, etc)
      REPL.addCommandObject("headers_load", headers); // load hashmap so it is accessible for other commands
      System.out.println("Loaded header types.");
    }
  }
}
