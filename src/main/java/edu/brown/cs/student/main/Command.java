package edu.brown.cs.student.main;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The command interface is used to create new commands.
 * Each command that implements this interface must create their own execute
 * method. The param is the command line arguments parsed into an array of strings.
 * Each command class is responsible for checking if the arguments are correct and throwibg
 * exceptions / errors where necessary. The REPL calls execute on the command entered if it exists.
 *
 * @author kkmarcus
 */
public interface Command extends REPL{


  // pass in the entire parsed repl line and have each respective class expect the arguments at certain indices
  // pass the parsed REPL line as a string array of ar    guments to each of your command classes


  /**
   * To be filled in by each Command object, must parse args themselves
   * into specific data categories depending on command
   *
   * @param args (array of strings)
   * @throws IOException
   */
  void execute(String[] args) throws IOException, SQLException, ClassNotFoundException;
}
