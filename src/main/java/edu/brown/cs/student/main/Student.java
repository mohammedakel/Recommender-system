package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Student class with attributes, takes in a parsed line of data and assigns the attributes.
 */
public class Student implements KdTreeNode {
  int cd;
  private int id;
  private String name;
  private String email;
  private String gender;
  private String class_year;
  private String ssn;
  private String nationality;
  private String race;
  private double years;
  private String communciation_style;
  private double hours;
  private String meeting_style;
  private String meeting_time;
  private double confidence;
  private String strengths;
  private String weaknesses;
  private String skills;
  private String interests;
  private HashMap<String, String> values;
  private KdTreeNode right;
  private KdTreeNode left;


  /**
   * Student constructor
   * @param CSVParsedLine
   */
  public Student(String[] CSVParsedLine) {
    this.id = Integer.parseInt(CSVParsedLine[0]);
    this.name = CSVParsedLine[1];
    this.email = CSVParsedLine[2];
    this.gender = CSVParsedLine[3];
    this.class_year = CSVParsedLine[4];
    this.ssn = CSVParsedLine[5];
    this.nationality = CSVParsedLine[6];
    this.race = CSVParsedLine[7];
    this.years = Double.parseDouble(CSVParsedLine[8]);
    this.communciation_style = CSVParsedLine[9];
    this.hours = Double.parseDouble(CSVParsedLine[10]);
    this.meeting_style = CSVParsedLine[11];
    this.meeting_time = CSVParsedLine[12];
    this.confidence = Double.parseDouble(CSVParsedLine[13]);
    this.strengths = CSVParsedLine[14];
    this.weaknesses = CSVParsedLine[15];
    this.skills = CSVParsedLine[16];
    this.interests = CSVParsedLine[17];
  }


  public int getId() {
    return id;
  }

  @Override
  public List<Double> getData() {
    List<Double> dataList = new ArrayList();
    dataList.add(years);
    dataList.add(hours);
    dataList.add(confidence);
    return dataList;
  }

  public String[] getBloomData() {
    String[] result = {this.communciation_style, this.meeting_style, this.meeting_time,
        this.strengths, this.weaknesses, this.skills, this.interests};
    return result;
  }

  @Override
  public KdTreeNode getRight() {
    return right;
  }

  @Override
  public KdTreeNode getLeft() {
    return left;
  }

  @Override
  public int getDimension() {
    return cd;
  }

  @Override
  public void setRight(KdTreeNode right) {
    this.right = right;
  }

  @Override
  public void setLeft(KdTreeNode left) {
    this.left = left;
  }

  @Override
  public void setDimension(int cd) {
    this.cd = cd;
  }
}
