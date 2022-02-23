package edu.brown.cs.student.main;

public class CreateStar implements CSVObject{
  @Override
  public Stars createObjectWithLineOfData(String[] lineOfData) {
    return new Stars(lineOfData);
  }
}
