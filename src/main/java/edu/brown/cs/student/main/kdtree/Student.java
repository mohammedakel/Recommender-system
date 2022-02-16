package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student implements CSVParser, KdTreeNode<KdTreeNode> {
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


  public Student(int id, String name, String email, String gender,
                 String class_year, String ssn, String nationality, String race, double years,
                 String communciation_style, double hours, String meeting_style,
                 String meeting_time,
                 double confidence, String strengths, String weaknesses, String skills,
                 String interests) {
    this.id = id;
    this.name = name;
    this.years = years;
    this.hours = hours;
    this.confidence = confidence;
    this.email = email;
    this.gender = gender;
    this.class_year = class_year;
    this.ssn = ssn;
    this.nationality = nationality;
    this.race = race;
    this.communciation_style = communciation_style;
    this.meeting_style = meeting_style;
    this.meeting_time = meeting_time;
    this.strengths = strengths;
    this.weaknesses = weaknesses;
    this.skills = skills;
    this.interests = interests;
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
