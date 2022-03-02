package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.Collections;
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
  public ArrayList qualitativeData;
  public List quantitativeData;

  /**
   * Alternative Constructor for Testing
   * @param id
   * @param name
   * @param email
   * @param gender
   * @param class_year
   * @param ssn
   * @param nationality
   * @param race
   * @param years
   * @param communciation_style
   * @param hours
   * @param meeting_style
   * @param meeting_time
   * @param confidence
   * @param strengths
   * @param weaknesses
   * @param skills
   * @param interests
   */
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

  /**
   * Other Student constructor that takes in parsed CSV line and hashmap of attribute/types
   * To be used for recommender system, load_headers
   * @param CSVParsedLine
   */
  public Student(String[] CSVParsedLine, HashMap<String, String> attributeType) {
    this.quantitativeData = new ArrayList();
    this.qualitativeData = new ArrayList();
    this.id = Integer.parseInt(CSVParsedLine[0]);
    setAttributeType(attributeType,"id", String.valueOf(this.id));
    this.name = CSVParsedLine[1];
    setAttributeType(attributeType,"name", this.name);
    this.email = CSVParsedLine[2];
    setAttributeType(attributeType,"email", this.email);
    this.gender = CSVParsedLine[3];
    setAttributeType(attributeType,"gender", this.gender);
    this.class_year = CSVParsedLine[4];
    setAttributeType(attributeType,"class_year", this.class_year);
    this.ssn = CSVParsedLine[5];
    setAttributeType(attributeType,"ssn", this.ssn);
    this.nationality = CSVParsedLine[6];
    setAttributeType(attributeType,"nationality", this.nationality);
    this.race = CSVParsedLine[7];
    setAttributeType(attributeType,"race", this.race);
    this.years = Double.parseDouble(CSVParsedLine[8]);
    setAttributeType(attributeType,"years_experience", this.years);
    this.communciation_style = CSVParsedLine[9];
    setAttributeType(attributeType,"communciation_style", this.communciation_style);
    this.hours = Double.parseDouble(CSVParsedLine[10]);
    setAttributeType(attributeType,"weekly_avail_hours", this.hours);
    this.meeting_style = CSVParsedLine[11];
    setAttributeType(attributeType,"meeting_style", this.meeting_style);
    this.meeting_time = CSVParsedLine[12];
    setAttributeType(attributeType,"meeting_time", this.meeting_time);
    this.confidence = Double.parseDouble(CSVParsedLine[13]);
    setAttributeType(attributeType,"software_engn_confidence", this.confidence);
    this.strengths = CSVParsedLine[14];
    setAttributeType(attributeType,"strengths", this.strengths);
    this.weaknesses = CSVParsedLine[15];
    setAttributeType(attributeType,"weaknesses", this.id);
    this.skills = CSVParsedLine[16];
    setAttributeType(attributeType,"skills", this.skills);
    this.interests = CSVParsedLine[17];
    setAttributeType(attributeType,"interests", this.interests);
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

  /**
   * GetBloomData is used by bloomLoader
   * @return
   */
  public ArrayList getBloomData() {
//    String[] result = {this.communciation_style, this.meeting_style, this.meeting_time,
//        this.strengths, this.weaknesses, this.skills, this.interests};
    ArrayList<String> result = new ArrayList<>();
    result.add(this.communciation_style);
    result.add(this.meeting_style);
    result.add(this.meeting_time);
    result.add(this.strengths);
    result.add(this.weaknesses);
    result.add(this.skills);
    result.add(this.interests);
    return result;
  }

  /**
   *
   * @param headerType hashMap of headers and type
   * @param attributeName String name of attribute to use as key in map
   * @param attribute actual value of attribute to be added to either qualitative data list or quantitative data list
   */
  public void setAttributeType(HashMap<String, String> headerType, String attributeName, Object attribute) {
    if (headerType.get(attributeName).equals("qualitative")) { // checks value of key
      this.qualitativeData.add(attribute);
    } else {
      this.quantitativeData.add(attribute);
    }
  }


  public ArrayList getQualitativeData(){
    //setHeaderType();
    return qualitativeData;
  }

  @Override
  public List<Double> getQuantitativeData() {
    //setHeaderType();
    return quantitativeData;
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

  @Override
  public void setHeaderType () {
    this.quantitativeData = new ArrayList();
    this.qualitativeData = new ArrayList();

    HashMap<String, String> headerType =
        (HashMap<String, String>) REPL.getCommandObject("headers_load");


    if (headerType.get("id").equals("qualitative")) {
      this.qualitativeData.add(String.valueOf(this.id));
      //this.qualitativeData.add(this.id);
    }
    else {
      this.quantitativeData.add(this.id);
    }
    if (headerType.get("name").equals("qualitative")) {
      this.qualitativeData.add(this.name);
    }
    else {
      this.quantitativeData.add(this.name);
    }
    if (headerType.get("email").equals("qualitative")) {
      this.qualitativeData.add(this.email);
    }
    else {
      this.quantitativeData.add(this.email);
    }
    if (headerType.get("gender").equals("qualitative")) {
      this.qualitativeData.add(this.gender);
    }
    else {
      this.quantitativeData.add(this.gender);
    }
    if (headerType.get("class_year").equals("qualitative")) {
      this.qualitativeData.add(this.class_year);
    }
    else {
      this.quantitativeData.add(this.class_year);
    }
    if (headerType.get("ssn").equals("qualitative")) {
      this.qualitativeData.add(this.ssn);
    }
    else {
      this.quantitativeData.add(this.ssn);
    }
    if (headerType.get("nationality").equals("qualitative")) {
      this.qualitativeData.add(this.nationality);
    }
    else {
      this.quantitativeData.add(this.nationality);
    }
    if (headerType.get("race").equals("qualitative")) {
      this.qualitativeData.add(this.race);
    }
    else {
      this.quantitativeData.add(this.race);
    }
    if (headerType.get("years_experience").equals("qualitative")) {
      this.qualitativeData.add(this.years);
    }
    else {
      this.quantitativeData.add(this.years);
    }
    if (headerType.get("communication_style").equals("qualitative")) {
      this.qualitativeData.add(this.communciation_style);
    }
    else {
      this.quantitativeData.add(this.communciation_style);
    }
    if (headerType.get("weekly_avail_hours").equals("qualitative")) {
      this.qualitativeData.add(this.hours);
    }
    else {
      this.quantitativeData.add(this.hours);
    }
    if (headerType.get("meeting_style").equals("qualitative")) {
      this.qualitativeData.add(this.meeting_style);
    }
    else {
      this.quantitativeData.add(this.meeting_style);
    }
    if (headerType.get("meeting_time").equals("qualitative")) {
      this.qualitativeData.add(this.meeting_time);
    }
    else {
      this.quantitativeData.add(this.meeting_time);
    }
    if (headerType.get("software_engn_confidence").equals("qualitative")) {
      this.qualitativeData.add(this.confidence);
    }
    else {
      this.quantitativeData.add(this.confidence);
    }
    if (headerType.get("strengths").equals("qualitative")) {
      this.qualitativeData.add(this.strengths);
    }
    else {
      this.quantitativeData.add(this.strengths);
    }
    if (headerType.get("weaknesses").equals("qualitative")) {
      this.qualitativeData.add(this.weaknesses);
    }
    else {
      this.quantitativeData.add(this.weaknesses);
    }
    if (headerType.get("skills").equals("qualitative")) {
      this.qualitativeData.add(this.skills);
    }
    else {
      this.quantitativeData.add(this.skills);
    }
    if (headerType.get("interests").equals("qualitative")) {
      this.qualitativeData.add(this.interests);
    }
    else {
      this.quantitativeData.add(this.interests);
    }
  }
}
