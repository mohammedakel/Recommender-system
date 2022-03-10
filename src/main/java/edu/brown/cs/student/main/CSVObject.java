package edu.brown.cs.student.main;

import java.util.HashMap;

/**
 * CSVObject interface allows the CSVParser to create any type of object
 * Each object needs a class that implements the method createObjectWithLineOfData
 * that takes in a line of parsed data and returns the desired object type with the attributes
 *
 * @author kkmarcus
 */
public interface CSVObject<T> {

  /**
   * To be implemented by classes
   */
  T createObjectWithLineOfData(String[] lineOfData, HashMap<String, String> attributeTypes);
}
