package edu.brown.cs.student.main;

public interface CSVObject<T> {

  /**
   * To be implemented by classes
   */
  T createObjectWithLineOfData(String[] lineOfData);
}