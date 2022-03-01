package edu.brown.cs.student.main.CSVParser;


public interface CSVObject<T> {

  /**
   * To be implemented by classes
   */
  T createObjectWithLineOfData(String[] lineOfData);
}
