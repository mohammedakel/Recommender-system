package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;

public class Repl {
  public Repl() throws IOException {
    List<Star> starList = new ArrayList<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String input = null;
    while ((input = br.readLine()) != null) {
      try {
        List<String> tokens = new ArrayList<>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
        Matcher regexMatcher = regex.matcher(input);
        while (regexMatcher.find()) {
          tokens.add(regexMatcher.group());
        }
        if (tokens.size() == 0) {
          continue;
        } else if (tokens.get(0).equals("stars")) {
          new Load(tokens, starList);
        } else if (tokens.get(0).equals("naive_neighbors")) {
          new NaiveNeighbors(tokens, starList);
        } else if (tokens.get(0).equals("exit")) {
          exit(0);
        } else {
          System.out.println("ERROR: Invalid command");
          continue;
        }
      } catch (IOException e) {
        System.out.println("ERROR: IOException");
      }
    }
  }
}
