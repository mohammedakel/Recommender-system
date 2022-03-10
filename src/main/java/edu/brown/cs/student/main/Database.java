package edu.brown.cs.student.main;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Database {
  private static Connection conn = null;
  private Map<String, String> tablePermissions;
  private Map<String, String> sqlPermissions;
  private boolean write;
  private LoadingCache<String, ResultSet> cache;

  public Database(String filename, Map<String, String> permissions)throws ClassNotFoundException{
    try {
      Class.forName("org.sqlite.JDBC");
      String urlToDB = "jdbc:sqlite:" + filename;
      conn = DriverManager.getConnection(urlToDB);
      Statement stat = conn.createStatement();
      stat.executeUpdate("PRAGMA foreign_keys=ON;");
    }
    catch(SQLException e){
      System.out.println("ERROR: SQLException caught");
    }
    catch(ClassNotFoundException e){
      System.out.println("ERROR: ClassNotFoundException caught");
    }
    tablePermissions = permissions;
    sqlPermissions = new HashMap<>();
    this.write = false;
    this.SqlPermissions();
    this.initializeCache();
  }

  public void initializeCache(){
    CacheLoader<String, ResultSet> loader = new CacheLoader<>() {
      @Override
      public ResultSet load(String key) {
        return executeSQL(key);
      }
    };

    cache = CacheBuilder
        .newBuilder()
        .expireAfterAccess(60, TimeUnit.MINUTES)
        .maximumSize(10)
        .build(loader);
  }


  public void SqlPermissions() {
    sqlPermissions.put("SELECT", "R");
    sqlPermissions.put("INSERT", "W");
    sqlPermissions.put("DROP", "RW");
    sqlPermissions.put("UPDATE", "RW");
    sqlPermissions.put("DELETE", "RW");
    sqlPermissions.put("ALTER", "RW");
    sqlPermissions.put("JOIN", "R");
    sqlPermissions.put("TRUNCATE", "RW");
  }

  public boolean checkAccess(String sql){
    boolean permission = true;
    String[] token = sql.split(" ");
    List<String> tables = new ArrayList<>();
    List<Integer> indices = new ArrayList<>();

    for (int i = 0; i<token.length; i++) {
      if(tablePermissions.containsKey(token[i].toUpperCase())){
        tables.add(token[i].toUpperCase());
        indices.add(i);
      }

    }
    for (int i = 0; i<token.length; i++) {
      if(sqlPermissions.containsKey(token[i].toUpperCase())){
        if(token[i].equals("JOIN")){
          String sqlVal = sqlPermissions.get(token[i].toUpperCase());
          String tableVal = tablePermissions.get(token[i+1].toLowerCase());
          String tableVal2 = tablePermissions.get(token[i-1].toLowerCase());
          if (!hasAccess(sqlVal, tableVal) || !hasAccess(sqlVal, tableVal2)) {
            permission = false;
          }
        } else {
          for (int j = i + 1; j < token.length; j++) {
            if (tablePermissions.containsKey(token[j].toLowerCase())) {
              String sqlVal = sqlPermissions.get(token[i].toUpperCase());
              String tableVal = tablePermissions.get(token[j].toLowerCase());
              if (!hasAccess(sqlVal, tableVal)) {
                permission = false;
              }
              break;
            }
          }
        }
      } else if(token[i].charAt(token[i].length() - 1) == ','){ //Implicit JOIN case
        String t = token[i].toLowerCase();
        t = t.replace(",","");
        if(tablePermissions.containsKey(t) && tablePermissions.containsKey(token[i+1].toLowerCase())){
          String tableVal = tablePermissions.get(token[i+1].toLowerCase());
          if (!hasAccess("R", tableVal) || !hasAccess("R", t)) {
            permission = false;
          }
        }
      }

    }
    return permission;
  }

  public boolean hasAccess(String sql, String table){
    if (sql.equals("RW")) {
      if(table.equals("RW")) {
        this.write = true;
        return true;
      }
    } else if (sql.equals("R")) {
      if (table.equals("R") || table.equals("RW")) {
        this.write = false;
        return true;
      }
    } else if (sql.equals("W")) {
      if (table.equals("W") || table.equals("RW")) {
        this.write = true;
        return true;
      }
    } else {
      System.out.println("ERROR: Access");
    }
    return false;
  }

  public ResultSet executeSQL(String sql) {
    try {
      PreparedStatement statement = conn.prepareStatement(sql);
      boolean returnsResult = statement.execute();
      if (returnsResult) {
        return statement.getResultSet();
      } else {
        return null;
      }
    } catch (SQLException e) {
      System.out.println("ERROR: SQL Command is Invalid");
      return null;
    }
  }

  public ResultSet executeSQLWithCache(String sql){
    if (!checkAccess(sql)) {
      System.out.println("Access Denied");
      return null;
    }
    try {
      if (write) {
        executeSQL(sql);
        System.out.println("Cache Cleared");
        cache.invalidateAll();
        return null;
      } else {
        if (cache.getIfPresent(sql) != null) {
          System.out.println("Cache Retrieved");
          return cache.get(sql);
        } else {
          System.out.println("Cache Added");
          return cache.getUnchecked(sql);
        }
      }
    } catch (ExecutionException e) {
      System.out.println("ERROR: Exception caught");
      return null;
    }
  }

}
