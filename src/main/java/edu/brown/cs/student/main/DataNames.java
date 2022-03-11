package edu.brown.cs.student.main;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Run data_names
 *
 * Loads in the database student data into a hashmap and iterates through them to add all elements
 * to the student object and prints them out to make sure it worked properly
 *
 * Implements REPL and Command interface
 *
 * @author hjeon15
 */
public class DataNames implements REPL, Command {
  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  DataNames() {
    String commandName = "data_names";
    this.addCommands(commandName, this);
  }

  /**
   * Gives permission to each table elements and runs sql statement using database proxy
   *
   * @param args (array of strings)
   * @throws IOException
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public void execute(String[] args) throws IOException, SQLException, ClassNotFoundException {
    Map<String, String> permissions = new HashMap<>();
    permissions.put("interests", "R");
    permissions.put("names", "R");
    permissions.put("skills", "R");
    permissions.put("traits", "R");
    String filePath = "data/sprint2/sql/data.sqlite3";
    Database proxy = new Database(filePath, permissions);
    ResultSet getNamesRS = proxy.executeSQL("SELECT name FROM names");

    // Print all of the names
    if(getNamesRS!=null){
      while (getNamesRS.next()){

        System.out.println(getNamesRS.getString(1));

      }
    }

  }
}
