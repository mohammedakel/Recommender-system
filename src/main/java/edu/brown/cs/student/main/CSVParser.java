package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * the CSVParser interface is to be used by command classes for parsing the user input
 * Needs work on making it more generic, works for Student class right now
 *
 * @author kkmarcus
 */
public interface CSVParser {

  /**
   *
   * @param filePath
   * @return ArrayList<ArrayList<String>> , ArrayList of an ArrayList of strings, where
   * each ArrayList of strings represents an object, so an ArrayList of the classes object
   * the class will create the specific object list itself using this ArrayList
   * @throws IOException
   */
  static ArrayList<ArrayList<String>> readAll(String filePath) throws IOException {
    BufferedReader csvReader = new BufferedReader(new FileReader(filePath)); // read file
    String input;
    ArrayList<ArrayList<String>> objects = new ArrayList<ArrayList<String>>();
    while ((input = csvReader.readLine()) != null) {
      String[] args = input.split(",");
      int size = args.length;
      boolean multiples = false;
      ArrayList<String> combineArgs = new ArrayList<String>();
      String concatenatedString = "";
      String currentString = "";
      for (int i = 0; i < size; i++) {
        currentString = args[i];
        if (currentString.startsWith("\"")) {
          multiples = true;
          concatenatedString = currentString;
        } else if (currentString.endsWith(("\""))) {
          concatenatedString = concatenatedString + "," + currentString;
          multiples = false;
          currentString = concatenatedString;
          combineArgs.add(currentString);
          concatenatedString = "";
        } else if (multiples) {
          concatenatedString = concatenatedString + "," + currentString;
        } else {
          combineArgs.add(currentString);
        }
      }
      objects.add(combineArgs);
    }
    return objects;
  }

  static ArrayList<String> createObject(ArrayList<String> lineOfData) {
    return lineOfData;
  }
}
