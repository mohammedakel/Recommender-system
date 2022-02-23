package edu.brown.cs.student.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
      REPL.addCommandObject("load_kd", studentTree); // add object
//
//      System.out.println(studentTree.getRoot().getId());
//      System.out.println(studentTree.getRoot().getLeft().getId());
//      System.out.println(studentTree.getRoot().getRight().getId());
//      System.out.println(studentTree.get(7).getLeft().getId());
//      System.out.println(studentTree.get(7).getRight().getId());
//      System.out.println(studentTree.get(4).getLeft().getId());
//      System.out.println(studentTree.get(4).getRight().getId());
//      System.out.println(studentTree.get(1).getLeft().getId());
//      System.out.println(studentTree.get(1).getRight().getId());
//      System.out.println(studentTree.get(3).getRight().getId());
//      System.out.println(studentTree.get(6).getRight().getId());
    }
  }

  public List<KdTreeNode> addNodes(List<Student> studentList) {
    List<KdTreeNode> nodeList = new ArrayList();
    for (int i = 0; i < studentList.size(); i++) {
      nodeList.add(studentList.get(i));
    }
    return nodeList;
  }
}
