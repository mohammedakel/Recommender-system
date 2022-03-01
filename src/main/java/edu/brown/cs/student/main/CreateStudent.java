package edu.brown.cs.student.main;

<<<<<<< HEAD
public class CreateStudent implements CSVObject{

    @Override
    public Student createObjectWithLineOfData(String[] lineOfData) {
        return new Student(lineOfData);
    }
}
=======
/**
 * Class that creates a Student given a parsed line of data
 * Implements the CSVObject interface so the generic CSV parser can create the object
 *
 * @author kkmarcus
 */
public class CreateStudent implements CSVObject{

  @Override
  public Student createObjectWithLineOfData(String[] lineOfData) {
    return new Student(lineOfData);
  }
}
>>>>>>> 626b2bd90c3a71135cd25fe9bb0d1e0ce61a0f38
