package edu.brown.cs.student.main;

public class CreateStudent implements CSVObject{

  @Override
  public Student createObjectWithLineOfData(String[] lineOfData) {
    System.out.println("HI");
    return new Student(lineOfData);
  }
}
