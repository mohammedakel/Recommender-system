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
    if (attributeTypes != null){
      return new Student(lineOfData, attributeTypes);
    }
    return new Student(lineOfData);
  }

//  @Override
//  public Student createObjectAndUseHeaderTypes(String[] lineOfData, HashMap attributeTypes) {
//    return new Student(lineOfData, attributeTypes);
//  }
}
