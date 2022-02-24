package edu.brown.cs.student.main;

import java.util.ArrayList;

public class SimilarKD implements REPL, Command {

  SimilarKD() {
    String commandName = "similar_kd";
    this.addCommands(commandName, this);
  }

  public void execute(String[] args) {
    // check if students have been loaded previously
    if (args.length < 3) {
      System.out.println("ERROR: Not enough args, type similar_kd <k> <some_user_id>");
    }
    else if (args.length == 3) {
      try {
        int k = Integer.parseInt(args[1]);
        int user_id = Integer.parseInt((args[2]));
        Tree studentTree = (Tree) REPL.getCommandObject("load_kd");
        if(studentTree == null){
          System.out.println("ERROR: The tree is empty");
          return;
        }
        ArrayList<Integer> neighbors = studentTree.findNeighbor(k, user_id);
        printNeighbors(neighbors);

      } catch (NumberFormatException e) {
        System.out.println("ERROR: Invalid arg(s). <k> and <some_user_id> must be Integers");
      }
    }
    else if (args.length > 3) {
      System.out.println("ERROR: Too many args, type similar_kd <k> <some_user_id>");
    }
  }

  public void printNeighbors(ArrayList<Integer> neighbors) {
    if (neighbors.isEmpty()) {
      return;
    } else {
      for (int i = 0; i < neighbors.size(); i++) {
        System.out.println(neighbors.get(i));
      }
    }
  }
}