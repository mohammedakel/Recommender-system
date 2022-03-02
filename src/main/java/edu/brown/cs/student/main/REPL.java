package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
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

  HashMap<String, Object> objectsForCommands = new HashMap<>();

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
        aa:
        while ((str = input.readLine()) != null) {
          String[] args = str.split(" ");
          if (args[0].equals("exit")) {
            break aa;
          } else if (checkCommandExists(args[0])) { // checks if command is in hashmap
            Command newCommand = commandsHash.get(args[0]);
            newCommand.execute(args); // command uses their own execute method
          }
        }
        break;
      }
    } catch (IOException e) {
      System.out.println("ERROR: invalid file input");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * @param commandName
   * @return object the command creates, if any
   */
  static Object getCommandObject(String commandName) {
    return objectsForCommands.get(commandName);
  }

  /**
   * Adds the object the command stores, if any
   *
   * @param commandName
   * @param commandObject
   */
  static void addCommandObject(String commandName, Object commandObject) {
    objectsForCommands.put(commandName, commandObject);
  }


  /**
   * Adds a new command to the REPL HashMap
   *
   * @param name    string entered by user used to call command
   * @param command command class containing the command details
   */
  default void addCommands(String name, Command command) {
    if (commandsHash.containsKey(name)) {
      //System.out.print("test");
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
   * Removes command from hashmap
   *
   * @param name    of command to remove
   * @param command class
   */
  default void removeCommands(String name, Command command) {
    if (commandsHash.containsKey(name)) {
      commandsHash.remove(name); // remove command
      System.out.println("Removed command: " + name);
    } else {
      System.out.println("ERROR: No command with name: " + name + " exists, cannot remove");
    }
  }
}


