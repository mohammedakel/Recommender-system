package edu.brown.cs.student.main;

import java.util.HashMap;

/**
 * Class that creates a Star given a parsed line of data
 * Implements the CSVObject interface so the generic CSV parser can create the list of stars
 *
 * @author kkmarcus
 */
public class CreateStar implements CSVObject {
  @Override
  public Stars createObjectWithLineOfData(String[] lineOfData, HashMap attributeTypes) {
    if (attributeTypes == null){
      return new Stars(lineOfData);
    }
    return new Stars(lineOfData, attributeTypes);
  }
//
//  @Override
//  public Stars createObjectWithLineOfData(String[] lineOfData) {
//    return new Stars(lineOfData);
//  }

//  @Override
//  public Stars createObjectAndUseHeaderTypes(String[] lineOfData, HashMap attributeTypes) {
//    return null;
//  }
}
