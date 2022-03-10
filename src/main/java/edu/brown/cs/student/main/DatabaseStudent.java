package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.List;

public class DatabaseStudent {
  private int id;
  private String name;
  private String email;
  private String skill;
  private List<String> strengths;
  private List<String> weaknesses;
  private List<String> interests;

  public DatabaseStudent(int id, String name, String email, String skill) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.skill = skill;
    this.strengths = new ArrayList<>();
    this.weaknesses = new ArrayList<>();
    this.interests = new ArrayList<>();
  }

  public int getID(){
    return this.id;
  }

  public void addTrait(String type, String trait) {
    if(type.toLowerCase().equals("strengths")){
      this.strengths.add(trait);
    } else if(type.toLowerCase().equals("weaknesses")){
      this.weaknesses.add(trait);
    }
  }

  public void addInterest(String interest) {
    this.interests.add(interest);
  }
  /**
   * Returns the name of the student.
   * @return student name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the id of the student.
   * @return id of student
   */
  public int getId() {
    return this.id;
  }

  /**
   * Returns the email of the student.
   * @return email of student
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Returns the skill of the student.
   * @return skill of student
   */
  public String getSkill() {
    return this.skill;
  }

  /**
   * Returns the interest list of the student.
   * @return interest list of student
   */
  public List<String> getInterests() {
    return this.interests;
  }

  /**
   * Returns the traits of the student.
   * @return traits of student
   */
  public List<String> getStrengths() {
    if(strengths != null){
      return strengths;
    }
    return null;
  }

  public List<String> getWeaknesses() {
    if(weaknesses != null){
      return weaknesses;
    }
    return null;
  }
}
