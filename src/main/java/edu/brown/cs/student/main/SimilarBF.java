package edu.brown.cs.student.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimilarBF implements Command {

  SimilarBF() {
    String commandName = "similar_bf";
    this.addCommands(commandName, this);
  }

  public void execute(String[] args) throws IOException {
    if (args.length < 3) {
      System.out.println("ERROR: Not enough args, type similar_bf <k> <user_id>");
    } else if (args.length == 3) {
      try {
        int k = Integer.parseInt(args[1]);
        int user_id = Integer.parseInt((args[2]));
        // TODO
        //List<Student> studentBloomFilters = (Student) REPL.getCommandObject("load_bf");
        // call similar_bf(k, user_id) method

      } catch (NumberFormatException e) {
        System.out.println("ERROR: Invalid arg(s). <k> and <some_user_id> must be Integers");
      }
    } else if (args.length > 3) {
      System.out.println("ERROR: Too many args, type similar_kd <k> <some_user_id>");
    }
  }
}
