package edu.brown.cs.student.main.commands;

import edu.brown.cs.student.main.API.ApiAggregate;
import edu.brown.cs.student.main.REPL;

import java.io.IOException;

public class Aggregate implements Command {

    public Aggregate() {
        String commandName = "api_aggregate";
        this.addCommands(commandName, this);
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length < 2) {System.out.println("ERROR: invalid args");}
        String method = args[1];
        ApiAggregate aggregator = new ApiAggregate();
        aggregator.aggregate(method);
        if (method.equals("info")) {
            REPL.addCommandObject("aggregate_info", aggregator.getStudents);
        }
        else if (method.equals("match")) {
            REPL.addCommandObject("aggregate_match", aggregator.postStudents);
        }
        else {
            System.out.println("Error: Invalid Arguments");
        }
    }

}
