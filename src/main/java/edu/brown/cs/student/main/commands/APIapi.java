package edu.brown.cs.student.main.commands;

import edu.brown.cs.student.main.API.Client;
import edu.brown.cs.student.main.API.ReqGenerator;
import edu.brown.cs.student.main.REPL;

import java.io.IOException;
import java.net.http.HttpResponse;

public class APIapi implements Command{

    public APIapi() {
        String commandName = "api";
        this.addCommands(commandName, this);
    }

    @Override
    public void execute(String[] args) throws IOException {
        Client client = new Client();
        ReqGenerator generator = new ReqGenerator();
        HttpResponse<String> responce = null;
        String result = null;
        if (args.length != 4) {
            System.out.println("ERROR: invalid args");
        }
        String method = args[1];
        String url = args[2];
        String param = args[3];
        System.out.println("method used is: " + method);
        System.out.println("url used is: " + url);
        System.out.println("param used are: " + param);
        if (method.equals("GET")) {
            responce = client.makeRequest(generator.makeGetRequest(url, param));
            result = responce.body();
            System.out.println(result);
        }
        else if (method.equals("POST")) {
            responce = client.makeRequest(generator.makePostRequest(url, param));
            result = responce.body();
            System.out.println(result);
        }
        REPL.addCommandObject("api", result);
    }
}
