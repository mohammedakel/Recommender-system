package edu.brown.cs.student.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Run load_bf [path-to-data]
 * loads bloom filters for every student in the dataset
 * load_bf outputs the number of students in the input dataset
 * as well as the file path of the input dataset
 *
 * It implements the REPL and Command interface, so it must add itself to the
 * commands hashMap and implement execute.
 */
public class LoadBF implements Command {

  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  LoadBF() {
    String commandName = "load_bf";
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
    } else if (args.length > 2) {
      System.out.println("ERROR: Too many args, run load_bf <path-to-data>");
    } else {
      String filePath = args[1];
      CSVParser newParser =
          new CSVParser(filePath, Student::new, true, false); // create parser specifying type of object
      newParser.readLine(); // read data
      List<Student> students = newParser.getListOfObjects(); // get list of students

//      if (REPL.getCommandObject("headers_load") != null) {
//        System.out.println("Using qualatative data to create bf");
//      }

      System.out.println("Read " + students.size() + " students from " + filePath);

      BloomFiltersLoader bloomLoader =
          new BloomFiltersLoader(students); // create new BloomFiltersLoader
      HashMap<String, BloomFilterBuilder> idsToBlooms = bloomLoader.loadAllBlooms();

      REPL.addCommandObject("load_bf",
          idsToBlooms); // add object to REPL hashmap so that it can be accessed by similar_bf

    }
  }
}

