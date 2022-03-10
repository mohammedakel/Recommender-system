package edu.brown.cs.student.main;

// look into using these imports for your REPL!

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stars class
 *
 * @author kkmarcus
 */
public class Stars {

  private String starID;
  private String properName;
  private String x;
  private String y;
  private String z;

  public Stars(String[] CSVParsedLine) {
    this.starID = CSVParsedLine[0];
    this.properName = CSVParsedLine[1];
    this.x = CSVParsedLine[2];
    this.y = CSVParsedLine[3];
    this.z = CSVParsedLine[4];
  }

  public Stars(String[] CSVParsedLine, HashMap<String, String> attributeType) {
    this.starID = CSVParsedLine[0];
    this.properName = CSVParsedLine[1];
    this.x = CSVParsedLine[2];
    this.y = CSVParsedLine[3];
    this.z = CSVParsedLine[4];
  }

  public String getProperName() {
    return this.properName;
  }

  public String getStarID() {
    return this.starID;
  }

  public String getX() {
    return x;
  }

  public String getY() {
    return y;
  }

  public String getZ() {
    return z;
  }

}
