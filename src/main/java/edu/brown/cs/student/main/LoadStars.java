package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * loads list of stars
 */
public class LoadStars implements REPL, Command {

  LoadStars() {
    String commandName = "stars";
    this.addCommands(commandName, this);
  }

  public void execute(String[] args) {
    try {
      if (args.length == 1) {
        System.out.println("ERROR: No FileName");
      } else {
        String filePath = args[1];
        Stars star = null;
        CSVParser newParser = new CSVParser(filePath, Stars::new, true);
        newParser.readLine();
        List<Stars> stars = newParser.getListOfObjects();
        REPL.addCommandObject("stars", stars);
        int t = stars.size();
        System.out.println("Read " + t + " stars from " + filePath); // print message
      }
    } catch (IOException e) {
      System.out.println("ERROR: invalid file input");
      //e.printStackTrace();
    }
  }
}
