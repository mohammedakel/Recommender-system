package edu.brown.cs.student.main;

import edu.brown.cs.student.main.bloomfilter.BloomFilterBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Generic REPL that provides generic way to add arbitrary command
 * Commands can be created by implemented Command and REPL, and using
 * addCommand to add the commandName and class to HashMap
 * Commands are responsible for parsing and checking args, REPL
 * provides user input parsed into Array of Strings.
 *
 * @author kkmarcus
 */
public interface REPL {
  HashMap<String, Command> commandsHash = new HashMap<String, Command>();
  int[] starsLoaded = new int[1];
  int[] bloomFilterCreated = new int[1];
  ArrayList<String[]> stars = new ArrayList<String[]>();
  BloomFilterBuilder<String> bloom = null;


  /**
   * Reads user input and checks if the command exists, if it does, calls execute.
   *
   * @throws IOException
   */
  static void runREPL() throws IOException {
    try {
      while (true) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String str;

        while ((str = input.readLine()) != null) {
          String[] args = str.split(" ");
          if (checkCommandExists(args[0])) { // if command is in hashmap
            Command newCommand = commandsHash.get(args[0]);
            newCommand.execute(args); // command uses their own execute method
          }
        }
        break;
      }
    } catch (java.io.IOException e) {
      System.out.println("ERROR: invalid file input");
    }
  }

  /**
   * Adds a new command to the REPL HashMap
   *
   * @param name    string entered by user used to call command
   * @param command command class containing the command details
   */
  default void addCommands(String name, Command command) {
    if (commandsHash.containsKey(name)) {
      System.out.println("ERROR: Command already exists using that name, choose different name");
    } else if (name.isBlank()) {
      System.out.println("ERROR: No command entered, command name must be non-empty string");
    } else {
      commandsHash.put(name, command);
    }
  }

  /**
   * @param name of command (String)
   * @return true (command exists) or false (throw error)
   */
  static boolean checkCommandExists(String name) {
    if (!commandsHash.containsKey(name)) {
      System.out.println("ERROR: No command with that name exists");
      return false;
    } else if (name.isBlank()) {
      System.out.println("ERROR: Command not found, must be non-empty string");
      return false;
    }
    return true; // command exists
  }

  /**
   * called by Stars class when file is loaded, stores stars data and sets
   * boolean (starsLoaded[0]) equal to 1 (true)
   *
   * @param starsToLoad
   */
  static void loadStars(ArrayList<String[]> starsToLoad) {
    starsLoaded[0] = 1;
    for (int i = 0; i < starsToLoad.size(); i++) {
      stars.add(i, starsToLoad.get(i));
    }
  }

  /**
   * Used by naive_neighbors to both check if stars has loaded data and to
   * access list of stars
   *
   * @return
   */
  static ArrayList<String[]> starsLoaded() {
    if (starsLoaded[0] == 1) {
      return stars;
    }
    return null;
  }

  /**
   * Used by create_bf command when bloom filter has been created
   */
  static BloomFilterBuilder<String> createdBloomFilter(Double r, int n) {
    bloomFilterCreated[0] = 1;
    if (r != null){
      bloom =new BloomFilterBuilder<String>(n, r);
    }
    else {
      BloomFilterBuilder bloom =new BloomFilterBuilder<String>(n);
    }
    return bloom;
  }

  /**
   * Used by insert_bf and query_bf to make sure bloom filter exists
   *
   * @return true if create_bf has been called, false if not
   */
  static BloomFilterBuilder<String> bloomFilterExists() {
    if (bloomFilterCreated[0] == 1) {
      return bloom;
    }
    return null;
  }

}


