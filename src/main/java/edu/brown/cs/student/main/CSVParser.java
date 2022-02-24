package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * the CSVParser interface is to be used by command classes for parsing the user input
 * Needs work on making it more generic, works for Student class right now
 *
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

  public CSVParser(String file, T type, Boolean headersIncluded) {
    this.filePath = file;
    this.delimiter = null;
    this.object = type;
    this.listOfObjects = new ArrayList<>();
    this.headersIncluded = headersIncluded;
  }

  public CSVParser(String file, T type, String delimiter, Boolean headersIncluded) {
    this.filePath = file;
    this.delimiter = delimiter;
    this.object = type;
    this.listOfObjects = new ArrayList<>();
    this.headersIncluded = headersIncluded;
  }

  public void readLine() throws IOException {
    BufferedReader csvReader = new BufferedReader(new FileReader(filePath)); // read file
    String input;
    int i = 0;
    if (!headersIncluded) {
      i = 1;
    }
    while ((input = csvReader.readLine()) != null) {
      if (i != 0){
        String[] args;
        if (delimiter != null) {
          args = input.split(delimiter);
        } else {
          args = input.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        }
        listOfObjects.add(object.createObjectWithLineOfData(args));
      }
      i++;
    }
  }

  public List<Object> getListOfObjects() {
    return listOfObjects;
  }
}