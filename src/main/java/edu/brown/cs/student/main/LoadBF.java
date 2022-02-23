package edu.brown.cs.student.main;

import java.io.IOException;
import java.util.List;

public class LoadBF implements Command {
  LoadBF() {
    String commandName = "load_bf";
    this.addCommands(commandName, this);
  }

  public void execute(String[] args) throws IOException {
    if (args.length == 1) {
      System.out.println("ERROR: No FileName");
    } else if (args.length > 2) {
      System.out.println("ERROR: Too many args, type load_bf <path-to-data>");
    } else {
      String filePath = args[1];
      CSVParser newParser = new CSVParser(filePath, Student::new, true);
      newParser.readLine();
      List<Student> students = newParser.getListOfObjects();
      System.out.println("Read " + students.size() + " students from " + filePath);

      REPL.addCommandObject("load_bf", students); // does this need to be bloomfilters? Adjust
      // TO DO: load bloom filters for every student in the dataset
    }
  }
}

