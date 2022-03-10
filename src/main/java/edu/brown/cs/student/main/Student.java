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
  private String communication_style;
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
  public HashMap attributeData;

  /**
   * Alternative Constructor for Testing
   *
   * @param id
   * @param name
   * @param email
   * @param gender
   * @param class_year
   * @param ssn
   * @param nationality
   * @param race
   * @param years
   * @param communication_style
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
                 String communication_style, double hours, String meeting_style,
                 String meeting_time,
                 double confidence, String strengths, String weaknesses, String skills,
                 String interests) {
    //System.out.println("Created student manually");
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
    this.communication_style = communication_style;
    this.meeting_style = meeting_style;
    this.meeting_time = meeting_time;
    this.strengths = strengths;
    this.weaknesses = weaknesses;
    this.skills = skills;
    this.interests = interests;
  }


  /**
   * Student constructor
   *
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
    this.communication_style = CSVParsedLine[9];
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
   *
   * @param CSVParsedLine
   */
  public Student(String[] CSVParsedLine, HashMap<String, String> attributeType) {
    if (attributeType == null) {
      //System.out.println("made new student w just line");
      this.id = Integer.parseInt(CSVParsedLine[0]);
      this.name = CSVParsedLine[1];
      this.email = CSVParsedLine[2];
      this.gender = CSVParsedLine[3];
      this.class_year = CSVParsedLine[4];
      this.ssn = CSVParsedLine[5];
      this.nationality = CSVParsedLine[6];
      this.race = CSVParsedLine[7];
      this.years = Double.parseDouble(CSVParsedLine[8]);
      this.communication_style = CSVParsedLine[9];
      this.hours = Double.parseDouble(CSVParsedLine[10]);
      this.meeting_style = CSVParsedLine[11];
      this.meeting_time = CSVParsedLine[12];
      this.confidence = Double.parseDouble(CSVParsedLine[13]);
      this.strengths = CSVParsedLine[14];
      this.weaknesses = CSVParsedLine[15];
      this.skills = CSVParsedLine[16];
      this.interests = CSVParsedLine[17];
      return;
    }
    this.quantitativeData = new ArrayList();
    this.qualitativeData = new ArrayList();

    this.id = Integer.parseInt(CSVParsedLine[0]);
    setAttributeType(attributeType, "id", String.valueOf(this.id));
    this.name = CSVParsedLine[1];
    setAttributeType(attributeType, "name", this.name);
    this.email = CSVParsedLine[2];
    setAttributeType(attributeType, "email", this.email);
    this.gender = CSVParsedLine[3];
    setAttributeType(attributeType, "gender", this.gender);
    this.class_year = CSVParsedLine[4];
    setAttributeType(attributeType, "class_year", this.class_year);
    this.ssn = CSVParsedLine[5];
    setAttributeType(attributeType, "ssn", this.ssn);
    this.nationality = CSVParsedLine[6];
    setAttributeType(attributeType, "nationality", this.nationality);
    this.race = CSVParsedLine[7];
    setAttributeType(attributeType, "race", this.race);
    this.years = Double.parseDouble(CSVParsedLine[8]);
    setAttributeType(attributeType, "years_experience", this.years);
    this.communication_style = CSVParsedLine[9];
    setAttributeType(attributeType, "communication_style", this.communication_style);
    this.hours = Double.parseDouble(CSVParsedLine[10]);
    setAttributeType(attributeType, "weekly_avail_hours", this.hours);
    this.meeting_style = CSVParsedLine[11];
    setAttributeType(attributeType, "meeting_style", this.meeting_style);
    this.meeting_time = CSVParsedLine[12];
    setAttributeType(attributeType, "meeting_time", this.meeting_time);
    this.confidence = Double.parseDouble(CSVParsedLine[13]);
    setAttributeType(attributeType, "software_engn_confidence", this.confidence);
    this.strengths = CSVParsedLine[14];
    setAttributeType(attributeType, "strengths", this.strengths);
    this.weaknesses = CSVParsedLine[15];
    setAttributeType(attributeType, "weaknesses", this.weaknesses);
    this.skills = CSVParsedLine[16];
    setAttributeType(attributeType, "skills", this.skills);
    this.interests = CSVParsedLine[17];
    setAttributeType(attributeType, "interests", this.interests);
    this.attributeData = attributeType;
  }


  public int getId() {
    return id;
  }

  public Double getHours() {
    return hours;
  }

  public String getCommunicationStyle() {
    return communication_style;
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
   *
   * @return
   */
  public ArrayList<String> getBloomData() {
//    String[] result = {this.communication_style, this.meeting_style, this.meeting_time,
//        this.strengths, this.weaknesses, this.skills, this.interests};
    ArrayList<String> result = new ArrayList<>();

    result.add(communication_style);
//    System.out.println("comstyle: " + communication_style);
    result.add(this.meeting_style);
//    System.out.println("meeting_style: " + this.meeting_style);
    result.add(this.meeting_time);
//    System.out.println("meeting_time: " + this.meeting_time);
//    System.out.println("strengths: " + this.strengths);
//    System.out.println("weaknesses: " + this.weaknesses);
    result.add(this.strengths);
    result.add(this.weaknesses);
    result.add(this.skills);
    result.add(this.interests);
    return result;
  }

  /**
   * Checks the description of the header attribute and adds to respective list
   *
   * @param headerType    hashMap of headers and type
   * @param attributeName String name of attribute to use as key in map
   * @param attribute     actual value of attribute to be added to either qualitative data list or quantitative data list
   */
  public void setAttributeType(HashMap<String, String> headerType, String attributeName,
                               Object attribute) {
    if (headerType.get(attributeName).equals("qualitative")) { // checks value of key
      this.qualitativeData.add(attribute);
    } else {
      this.quantitativeData.add(attribute);
    }
  }

  /**
   * Returns the list of qualitative data, to be used by the bloom filter.
   */
  public ArrayList<String> getQualitativeData() {
    return qualitativeData;
//    if (this.attributeData != null){
//      return qualitativeData;
//    }
//    return getBloomData();
  }

  /**
   * Returns the list of quantitative data, to be used by the KD tree.
   */
  @Override
  public List<Double> getQuantitativeData() {
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

}

