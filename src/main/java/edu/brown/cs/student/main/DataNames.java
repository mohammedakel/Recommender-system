package edu.brown.cs.student.main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataNames implements REPL, Command {


  DataNames() {
    String commandName = "data_names";
    this.addCommands(commandName, this);
  }

  public void execute(String[] args) throws IOException, SQLException, ClassNotFoundException {
    Map<String, String> permissions = new HashMap<>();
    permissions.put("interests", "R");
    permissions.put("names", "R");
    permissions.put("skills", "R");
    permissions.put("traits", "R");
    String filePath = "data/project1/Recommendation Data/sql/data.sqlite3";
    Database proxy = new Database(filePath, permissions);
    ResultSet getNamesRS = proxy.executeSQL("DELETE FROM names");

    // Print all of the names
    if(getNamesRS!=null){
      while (getNamesRS.next()){

        System.out.println(getNamesRS.getString(1));

      }
    }

  }

}