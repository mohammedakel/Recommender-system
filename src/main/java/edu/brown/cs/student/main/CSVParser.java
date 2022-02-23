package edu.brown.cs.student.main;

<<<<<<< HEAD
import org.checkerframework.checker.regex.qual.Regex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
=======
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
>>>>>>> fb63d51cbd07892f4a802ba4fbe3261cff0c01d6

/**
 * the CSVParser interface is to be used by command classes for parsing the user input
 * Needs work on making it more generic, works for Student class right now
 *
<<<<<<< HEAD
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
=======
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
>>>>>>> fb63d51cbd07892f4a802ba4fbe3261cff0c01d6
