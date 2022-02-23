package edu.brown.cs.student.main;

public class CreateStudent implements CSVObject{

    @Override
    public Student createObjectWithLineOfData(String[] lineOfData) {
        return new Student(lineOfData);
    }
}