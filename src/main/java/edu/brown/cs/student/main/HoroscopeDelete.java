package edu.brown.cs.student.main;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HoroscopeDelete implements REPL, Command {
  HoroscopeDelete() {
    String commandName = "h_delete";
    this.addCommands(commandName, this);
  }

  @Override
  public void execute(String[] args) throws IOException, SQLException, ClassNotFoundException {
    Map<String, String> permissions = new HashMap<>();
    permissions.put("horoscopes", "R");
    permissions.put("sqlite_sequence", "R");
    permissions.put("ta_horoscope", "RW");
    permissions.put("tas", "RW");
    String fileName = "data/sprint2/sql/horoscopes.sqlite3";

    Database proxy = new Database(fileName, permissions);
    String sql1 = "SElECT name FROM tas";
    ResultSet rs = proxy.executeSQLWithCache(sql1);
    System.out.println("Original TA List");
    while (rs.next()) {
      System.out.println(rs.getString(1));
    }

    System.out.println("\nList without HTAs");
    String sql2 = "DELETE FROM tas WHERE role = 'HTA'";
    proxy.executeSQLWithCache(sql2);
    String sql3 = "SELECT name FROM tas";
    rs = proxy.executeSQLWithCache(sql3);
    while (rs.next()) {
      System.out.println(rs.getString(1));
    }

    System.out.println("\nList with HTAs");
    String insert1 = "INSERT INTO tas VALUES (2, 'Hari', 'HTA')";
    proxy.executeSQLWithCache(insert1);
    String insert2 = "INSERT INTO tas VALUES (3, 'Eva', 'HTA')";
    proxy.executeSQLWithCache(insert2);
    String insert3 = "INSERT INTO tas VALUES (4, 'Colton', 'HTA')";
    proxy.executeSQLWithCache(insert3);

    String sql4 = "SElECT name FROM tas";
    rs = proxy.executeSQLWithCache(sql4);
    while (rs.next()) {
      System.out.println(rs.getString(1));
    }
  }

}

