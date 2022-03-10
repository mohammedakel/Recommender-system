package edu.brown.cs.student.main;

import java.util.HashMap;

/**
 * Class that creates a Student given a parsed line of data
 * Implements the CSVObject interface so the generic CSV parser can create the object
 *
 * @author kkmarcus
 */
public class CreateStudent implements CSVObject{

  @Override
  public Student createObjectWithLineOfData(String[] lineOfData, HashMap attributeTypes) {
    System.out.println("HELLO");
    if (attributeTypes == null){
      System.out.println("attributeTypes is null");
      return new Student(lineOfData);
    }
    System.out.println("attributeTypes not null");
    return new Student(lineOfData, attributeTypes);
  }

//  @Override
//  public Student createObjectWithLineOfData(String[] lineOfData) {
//    return new Student(lineOfData);
//  }

//  @Override
//  public Student createObjectAndUseHeaderTypes(String[] lineOfData, HashMap attributeTypes) {
//    return new Student(lineOfData, attributeTypes);
//  }
}
