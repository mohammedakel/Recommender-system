package edu.brown.cs.student.main.commands;


import edu.brown.cs.student.main.CSVParser.CSVObject;
import edu.brown.cs.student.main.Student;

/**
 * Class that creates a Student given a parsed line of data
 * Implements the CSVObject interface so the generic CSV parser can create the object
 *
 * @author kkmarcus
 */
public class CreateStudent implements CSVObject {

  @Override
  public Student createObjectWithLineOfData(String[] lineOfData) {
    return new Student(lineOfData);
  }
}
