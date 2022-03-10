package edu.brown.cs.student.main;

import java.util.HashMap;

public class ApiStudent {
  private  int id;
  private  String name;
  private  String class_year;
  private  String email;
  private  int years_experience;
  private  String communication_style;
  private  int weekly_avail_hours;
  private  String meeting_style;
  private  String meeting_time;
  private  String gender;
  private  String ssn;
  private  String nationality;
  private  String race;
  private  int software_eng_confidence;
  private String[] studentAttributes;

  public ApiStudent(int id, String name, String class_year, String email, int years_experience,
                    String communication_style, int weekly_avail_hours, String meeting_style, String meeting_time,
                    String gender, String ssn, String nationality, String race, int software_eng_confidence){
    this.id = id;
    this.name = name;
    this.class_year = class_year;
    this.email = email;
    this.years_experience = years_experience;
    this.communication_style = communication_style;
    this.weekly_avail_hours = weekly_avail_hours;
    this.meeting_style = meeting_style;
    this.meeting_time = meeting_time;
    this.gender = gender;
    this.ssn = ssn;
    this.nationality = nationality;
    this.race = race;
    this.software_eng_confidence = software_eng_confidence;

    studentAttributes = new String[18];
    studentAttributes[0] = String.valueOf(id);
    studentAttributes[1] = name;
    studentAttributes[2] = email;
    studentAttributes[3] = gender;
    studentAttributes[4] = class_year;
    studentAttributes[5] = ssn;
    studentAttributes[6] = nationality;
    studentAttributes[7] = race;
    studentAttributes[8] = String.valueOf(years_experience);
    studentAttributes[9] = communication_style;
    studentAttributes[10] = String.valueOf(weekly_avail_hours);
    studentAttributes[11] = meeting_style;
    studentAttributes[12] = meeting_style;
    studentAttributes[13] = String.valueOf(software_eng_confidence);
    // no 14, that is strengths (DB)
    // no 15, that is weaknesses (DB)
    // no 16, that is skills (DB)
    // no 17, that is interests (DB)
  }

  public String[] getStudentAttributes() {
    return studentAttributes;
  }

  /**
   * Coverts any given API student object into a single string
   * @return String
   */
  public String convertToString() {
    return String.join(", ",
        Integer.toString(this.id),
        this.name,
        this.class_year,
        Integer.toString(this.years_experience),
        this.communication_style,
        Integer.toString(this.weekly_avail_hours),
        this.meeting_style, this.meeting_time, this.gender,
        this.ssn,
        this.nationality,
        this.race,
        Integer.toString(this.software_eng_confidence)
    );
  }
}

