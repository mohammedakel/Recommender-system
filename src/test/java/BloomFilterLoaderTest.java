package edu.brown.cs.student.main;

import edu.brown.cs.student.main.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * A class that tests mapping a list of student into a map of student ids to their corresponding bloom filters
 *
 *
 */
public class BloomFilterLoaderTest {
  /**
   * a method that creates a sample student one
   *
   */
  private Student createStudentsOne() {
    Student studentOne = new Student(1, "name", "email", "gender", "class year",
        "ssn", "nationality", "race", 20, "communication style",
        3, "meeting style", "meeting time", 10, "strength",
        "weakness", "skills", "interests");
    return studentOne;
  }

  /**
   * a method that tests extracting relevant student attributes
   *
   */
  @Test
  public void testGetBloomData() {
    Student student = createStudentsOne();
    ArrayList<String> result = student.getBloomData();

    ArrayList<String> expected = new ArrayList<>();
    expected.add("communication style");
    expected.add("meeting style");
    expected.add("meeting time");
    expected.add("strength");
    expected.add("weakness");
    expected.add("skills");
    expected.add("interests");

//    String[] expected = {"communication style", "meeting style", "meeting time",
//        "strength", "weakness", "skills", "interests"};
    assertEquals(expected, result);
  }

  /**
   * a method that creates a sample student two
   *
   */
  private Student createStudentsTwo() {
    Student studentTwo = new Student(2, "name", "email", "gender", "class year",
        "ssn", "nationality", "race", 20, "written, email, " +
        "before midnight", 3, "zoom", "mornings, afternoons", 10,
        "a, b, c", "d, e, f", "aa, bb", "nothing");
    return studentTwo;
  }

  /**
   * a method that creates a sample student three
   *
   */
  private Student createStudentsThree() {
    Student studentThree = new Student(3, "name", "email", "gender", "class year",
        "ssn", "nationality", "race", 20, "written, email, call" +
        "before midnight", 3, "zoom, in person", "mornings, afternoons, nights", 10,
        "a, b, c, k", "d, e, f, l", "aa, bb", "swimming, sports");
    return studentThree;
  }

  /**
   * a method that tests weather a string set of ALL relevant fields and their attributes is created
   *
   */
  @Test
  public void testExtractingBloomInformation(){
    Student one = this.createStudentsOne();
    Student two = this.createStudentsTwo();
    List<Student> studentsList = new ArrayList<Student>() {{
      add(one);
      add(two);
    } };
    HashSet<String> expectedTwo = new HashSet<String>() {{
      add("written");
      add("email");
      add("before midnight");
      add("zoom");
      add("mornings");
      add("afternoons");
      add("a");
      add("b");
      add("c");
      add("d");
      add("e");
      add("f");
      add("aa");
      add("bb");
      add("nothing");
    } };
    BloomFiltersLoader loader = new BloomFiltersLoader(studentsList, 0.01);
    HashSet<String> resultTwo = loader.extractStudentInformation(two);
    //assertEquals(23, resultTwo.size());
    assertEquals(expectedTwo.size(), resultTwo.size());
  }

  /**
   * a method that tests wheather the system is able to get the maximum number of attributes a given student has
   *
   */
  @Test
  public void testMaxNumItems(){
    Student one = this.createStudentsOne();
    Student two = this.createStudentsTwo();
    Student three = this.createStudentsThree();
    List<Student> studentsList = new ArrayList<Student>() {{
      add(one);
      add(two);
      add(three);
    } };
    assertEquals(3, studentsList.size());
    BloomFiltersLoader loader = new BloomFiltersLoader(studentsList, 0.01);
    try {
      loader.toString();
      HashSet<String> allItems1 = loader.extractStudentInformation(one);
      System.out.println("is allItems1 null: " + (allItems1==null));
    } catch(NullPointerException e) {
      System.out.println("NULL POINTER CAUGHT");
    }

    HashSet<String> allItems2 = loader.extractStudentInformation(two);
    HashSet<String> allItems = loader.extractStudentInformation(three);

    System.out.println("is allItems2 null: " + (allItems2==null));
    System.out.println("is allItems null: " + (allItems==null));
    System.out.println("is loader null: " + (loader.extractStudentInformation(three)==null));
    int expectedMax = loader.extractStudentInformation(three).size();

    int result = loader.getMaxNumItems();
    assertEquals(expectedMax, result);
  }



  /**
   * a method that tests if a bloom filter is created given a student
   *
   */
  @Test
  public void testIndividualStudentBloomFilter(){
    Student one = this.createStudentsOne();
    Student two = this.createStudentsTwo();
    Student three = this.createStudentsThree();
    List<Student> studentsList = new ArrayList<>() {{
      add(one);
      add(two);
      add(three);
    } };

    BloomFiltersLoader loader = new BloomFiltersLoader(studentsList, 0.01);
    HashSet<String> oneInfo = loader.extractStudentInformation(one);

    assertEquals(7, oneInfo.size()); //old cause getBloom vs qualatative
    //assertEquals(15, oneInfo.size());
    BloomFilterBuilder result = loader.createStudentBloomFilter(oneInfo);
    //BloomFilterBuilder expected = new BloomFilterBuilder(loader.getMaxNumItems());
    BloomFilterBuilder expected = new BloomFilterBuilder(7);
    expected.add("communication style");
    expected.add("meeting style");
    expected.add("meeting time");
    expected.add("strength");
    expected.add("weakness");
    expected.add("skills");
    expected.add("interests");
//    assertEquals(expected.getLen(), result.getLen()); qualatative fix
//    assertEquals(expected.toBinaryString(), result.toBinaryString());
  }

  /**
   * a method that tests weather the system loads a list of students into a hashmap of student ids
   * to their corresponding bloom filters
   *
   */
  @Test
  public void testLoadStudentsBloomFilters() {
    Student one = this.createStudentsOne();
    Student two = this.createStudentsTwo();
    Student three = this.createStudentsThree();
    List<Student> studentsList = new ArrayList<Student>() {{
      add(one);
      add(two);
      add(three);
    } };
    assertEquals(3, studentsList.size());
    BloomFiltersLoader loader = new BloomFiltersLoader(studentsList, 0.01);
    HashSet<String> oneInfo = loader.extractStudentInformation(one);
    HashSet<String> twoInfo = loader.extractStudentInformation(two);
    HashSet<String> threeInfo = loader.extractStudentInformation(three);
    HashMap<String, BloomFilterBuilder> expected = new HashMap<>() {{
      put("1", loader.createStudentBloomFilter(oneInfo));
      put("2", loader.createStudentBloomFilter(twoInfo));
      put("3", loader.createStudentBloomFilter(threeInfo));
    }};
    HashMap<String, BloomFilterBuilder> result = loader.loadAllBlooms();
    assertEquals(3, result.size());
    String binaryStrExpected = expected.get("1").toBinaryString();
    String binaryStrResult = result.get("1").toBinaryString();
    assertEquals(binaryStrExpected, binaryStrResult);
  }
}
