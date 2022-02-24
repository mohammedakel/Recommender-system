package edu.brown.cs.student.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadKD implements REPL, Command {


  LoadKD() {
    String commandName = "load_kd";
    this.addCommands(commandName, this);
  }

  public void execute(String[] args) throws IOException {
    if (args.length == 1) {
      System.out.println("ERROR: No FileName");
    } else {
      String filePath = args[1];
      CSVParser newParser = new CSVParser(filePath, Student::new, true);
      newParser.readLine();
      List<KdTreeNode> students = newParser.getListOfObjects();
      System.out.println("Read " + students.size() + " students from " + filePath);
      Tree studentTree = new Tree(students);
      REPL.addCommandObject("load_kd", studentTree);
    }
  }

}