package edu.brown.cs.student.main;

import org.checkerframework.checker.regex.qual.Regex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * the CSVParser interface is to be used by command classes for parsing the user input
 * It takes in
 * <p>
 * Regex parser: https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
 *
 * @author kkmarcus
 */
public class CSVParser<T extends CSVObject> {

  private String delimiter;
  private CSVObject<T> object;
  private List<Object> listOfObjects;
  private String filePath;
  private Boolean headersIncluded;

  /**
   * One constructor that does not take in a delimiter type, so the default refex
   * parser is used. Takes in a string filePath, T type of object, and a boolean
   * that tells whether or not the headers are included (if so the first line of data should be skipped
   * and not made an object)
   * <p>
   * Each class is to instantiate a CSVParser
   * and specify the type of object to make, as well as other params.
   *
   * @param filePath
   * @param type
   * @param headersIncluded
   */
  public CSVParser(String filePath, T type, Boolean headersIncluded) {
    this.filePath = filePath;
    this.delimiter = null; // sets to null so that default is used
    this.object = type;
    this.listOfObjects = new ArrayList<>(); // list of objects to return
    this.headersIncluded = headersIncluded;
  }

  /**
   * Second constructor that does take in a delimiter type, which tells how to parse the data.
   * Takes in a string filePath, T type of object, and the same boolean as other constructor
   *
   * Each class is to instantiate a CSVParser and specify the "strategy" for parsing (delimiter),
   * and the type of object to make, as well as other params.
   *
   * @param filePath
   * @param type
   * @param headersIncluded
   */
  public CSVParser(String filePath, T type, String delimiter, Boolean headersIncluded) {
    this.filePath = filePath;
    this.delimiter = delimiter;
    this.object = type;
    this.listOfObjects = new ArrayList<>();
    this.headersIncluded = headersIncluded;
  }

  /**
   * Reads and parses the data. Adds each new object from one line of data to list of objects.
   *
   * @throws IOException
   */
  public void readLine() throws IOException {
    BufferedReader csvReader = new BufferedReader(new FileReader(filePath)); // read file
    String input;
    int i = 0;
    if (!headersIncluded) { // if headers are not included, the first line of data should become an object
      i = 1;
    }
    while ((input = csvReader.readLine()) != null) {
      if (i != 0) { // if i = 0, the headers are included and we should skip the first line of data
        String[] args;
        if (delimiter != null) { // if delimiter isn't null, use specified type
          args = input.split(delimiter);
        } else { // otherwise use default regex
          args = input.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        }
        listOfObjects.add(object.createObjectWithLineOfData(args)); // calls the createObjectWithLineOfData method and adds to list
      }
      i++;
    }
  }

  /**
   * For each class to call to get list of objects
   * @return list of Object type
   */
  public List<Object> getListOfObjects() {
    return listOfObjects;
  }
}
