package edu.brown.cs.student.main;

/**
<<<<<<< HEAD
 *
 */
public interface CSVObject<T> {

    /**
     * To be implemented by classes
     */
    T createObjectWithLineOfData(String[] lineOfData);
}
=======
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
  T createObjectWithLineOfData(String[] lineOfData);
}
>>>>>>> 626b2bd90c3a71135cd25fe9bb0d1e0ce61a0f38
