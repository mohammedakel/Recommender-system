package edu.brown.cs.student.main.commands;

import edu.brown.cs.student.main.API.Client;
import edu.brown.cs.student.main.API.ReqGenerator;
import edu.brown.cs.student.main.REPL;

import java.io.IOException;
import java.net.http.HttpResponse;

/**
 * a class that handles the active command of the API aggregator componenet
 *
 */

public class APIActive implements Command{

    public APIActive() {
        String commandName = "active";
        this.addCommands(commandName, this);
    }

    @Override
    public void execute(String[] args) throws IOException {
        Client client = new Client();
        String result = "";
        HttpResponse<String> response;
        ReqGenerator generator = new ReqGenerator();
        String req = "";
        if (args.length != 2) {
            System.out.println("ERROR: invalid arguments; Enter in the form: active <type>");
        }
        if(args[0].equals("active")) {
            if(args[1].equals("info")) {
                req = "https://studentinfoapi.herokuapp.com/get-active";
            }
            else if (args[1].equals("match")) {
                req = "https://studentmatchapi.herokuapp.com/get-active";
            }
            else {
                System.out.println("ERROR: invalid type argument");
            }
            response = client.makeRequest(generator.getActive(req));
            result = response.body();
            System.out.println(result);
        }
        REPL.addCommandObject("active", result);
    }




}
