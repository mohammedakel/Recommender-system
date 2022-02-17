package edu.brown.cs.student.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoadKD implements REPL, Command, CSVParser {


  LoadKD() {
    String commandName = "load_kd";
    this.addCommands(commandName, this);
  }

  public void execute(String[] args) throws IOException {
    if (args.length == 1) {
      System.out.println("ERROR: No FileName");
    } else {
      String filePath = args[1];
      ArrayList<ArrayList<String>> parsedData = CSVParser.readAll(filePath);
      List<Student> students = this.createObject(parsedData);
      System.out.println("Read " + students.size() + " students from " + filePath);
      Tree studentTree = new Tree(addNodes(students));

//      System.out.println(studentTree.getRoot().getId());
//      System.out.println(students.get(7).getLeft().getId());
//      System.out.println(students.get(7).getRight().getId());
//      System.out.println(students.get(4).getLeft().getId());
//      System.out.println(students.get(4).getRight().getId());
//      System.out.println(students.get(1).getLeft().getId());
//      System.out.println(students.get(1).getRight().getId());
//      System.out.println(students.get(3).getRight().getId());
//      System.out.println(students.get(6).getRight().getId());
    }
  }

  public List<KdTreeNode> addNodes(List<Student> studentList) {
    List<KdTreeNode> nodeList = new ArrayList();
    for (int i = 0; i < studentList.size(); i++) {
      nodeList.add(studentList.get(i));
    }
    return nodeList;
  }

  public void loadStudents(HashMap<String, String> attributes) {

    List<KdTreeNode> nodeList = new ArrayList();
    int id = Integer.parseInt(attributes.get("id"));
    String name = attributes.get("name");
    double years = Double.parseDouble(attributes.get("years"));
    double hours = Double.parseDouble(attributes.get("hours"));
    double confidence = Double.parseDouble(attributes.get("confidence"));
    String email = attributes.get("email");
    String gender = attributes.get("gender");
    String class_year = attributes.get("class_year");
    String ssn = attributes.get("ssn");
    String nationality = attributes.get("nationality");
    String race = attributes.get("race");
    String communication_style = attributes.get("communication_style");
    String meeting_style = attributes.get("meeting_style");
    String meeting_time = attributes.get("meeting_time");
    String strengths = attributes.get("strengths");
    String weaknesses = attributes.get("weaknesses");
    String skills = attributes.get("skills");
    String interests = attributes.get("interests");

    Student newStudent =
        new Student(id, name, email, gender, class_year, ssn, nationality, race, years,
            communication_style, hours, meeting_style, meeting_time, confidence, strengths,
            weaknesses, skills,
            interests);
  }

  public List<Student> createObject(ArrayList<ArrayList<String>> allData) throws IOException {
    String[] attributesArray = this.attributesArray(allData.get(0));
    allData.remove(0);
    int size = allData.size();
    List<Student> students = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      ArrayList<String> lineOfData = allData.get(i);
      Student newStudent = this.createStudent(attributesArray, lineOfData);
      students.add(newStudent);
    }
    return students;
  }

  public String[] attributesArray(ArrayList<String> parsedData) {
    int size = parsedData.size();
    String[] attributes = new String[size];
    for (int i = 0; i < size; i++) {
      attributes[i] = parsedData.get(i);
    }
    return attributes;
  }


  public Student createStudent(String[] attributesArray, ArrayList<String> lineOfData)
      throws IOException {
    HashMap<String, String> attributes = readData(attributesArray, lineOfData);

    int id = Integer.parseInt(attributes.get("id"));
    String name = attributes.get("name");
    double years = Double.parseDouble(attributes.get("years_experience"));
    double hours = Double.parseDouble(attributes.get("weekly_avail_hours"));
    double confidence = Double.parseDouble(attributes.get("software_engn_confidence"));
    String email = attributes.get("email");
    String gender = attributes.get("gender");
    String class_year = attributes.get("class_year");
    String ssn = attributes.get("ssn");
    String nationality = attributes.get("nationality");
    String race = attributes.get("race");
    String communication_style = attributes.get("communication_style");
    String meeting_style = attributes.get("meeting_style");
    String meeting_time = attributes.get("meeting_time");
    String strengths = attributes.get("strengths");
    String weaknesses = attributes.get("weaknesses");
    String skills = attributes.get("skills");
    String interests = attributes.get("interests");

    Student newStudent =
        new Student(id, name, email, gender, class_year, ssn, nationality, race, years,
            communication_style, hours, meeting_style, meeting_time, confidence, strengths,
            weaknesses, skills,
            interests);
    return newStudent;

  }

  public HashMap<String, String> readData(String[] attributesArray, ArrayList<String> parsedData)
      throws IOException {
    HashMap<String, String> values = new HashMap<String, String>();

    int size = parsedData.size();

    for (int i = 0; i < size; i++) {
      values.put(attributesArray[i], parsedData.get(i));
    }
    return values;
  }

}
