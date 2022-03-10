package edu.brown.cs.student.main;

import java.io.IOException;

public class Aggregate implements Command{

  Aggregate() {
    String commandName = "api_aggregate";
    this.addCommands(commandName, this);
  }

  @Override
  public void execute(String[] args) throws IOException {
    if (args.length < 2) {System.out.println("ERROR: invalid args");}
    String method = args[1];
    ApiAggregate agregator = new ApiAggregate();
    agregator.aggregate(method);
    if (method.equals("info")) {
      REPL.addCommandObject("aggregate_info", agregator.getInfoStudents());
    }
    else if (method.equals("match")) {
      REPL.addCommandObject("aggregate_match", agregator.getMatchStudents());
    }
    else {
      System.out.println("Error: Invalid Arguments");
    }
  }
}
