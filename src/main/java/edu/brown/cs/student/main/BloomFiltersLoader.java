package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * A class that construct Bloom Filters for all Students
 */
public class BloomFiltersLoader {
  private List<Student> students;
  private int maxNumItems;
  public double falsePositivityRate;

  /**
   * A class constructor that takes in a list of student of data and falsePositivityrate
   *
   * @param studentsData        list of all valid students in the data set
   * @param falsePositivityRate desired false positivity rate
   */
  public BloomFiltersLoader(List<Student> studentsData, double falsePositivityRate) {
    this.students = studentsData;
    this.falsePositivityRate = falsePositivityRate;
  }

  /**
   * A class constructor that takes in a list of student of data and uses 0.01 as default values for fpr
   *
   * @param studentsData list of all valid students in the data set
   */
  public BloomFiltersLoader(List<Student> studentsData) {
    this.students = studentsData;
  }


  /**
   * A method that extract relevant information from a given students
   *
   * @param student given student with relevant information
   * @return HashSet<String>
   * a set of  individual string items of all the information in the given student
   */
  public HashSet<String> extractStudentInformation(Student student) {
    HashSet<String> allItems = new HashSet<>();
    // String[] relevantFields = student.getBloomData();
    //ArrayList relevantFields = student.getQualitativeData();
    //List<String> relevantFields = student.getBloomData();
    ArrayList<String> relevantFields;
    HashMap<String, String> headers =
        (HashMap<String, String>) REPL.getCommandObject("headers_load");
    //System.out.println("ARE HEADERS EMPTY: " + headers.isEmpty());
    if (headers == null || headers.isEmpty()) {
      relevantFields = new ArrayList<>(student.getBloomData());
      //System.out.println("getting bloom data");
    } else {
      //relevantFields = student.getQualitativeData();
      relevantFields = new ArrayList<>(student.getQualitativeData());
      //System.out.println("getting qualitative data");
    }
    for (int i = 0; i < relevantFields.size(); i++) {
      String[] attributeValues = relevantFields.get(i).split(",");
      for (String value : attributeValues) {
        allItems.add(value);
      }
    }
//    for (String attribute : relevantFields) {
//
//      String[] attributeValues = attribute.split(",");
//
//      for (String value : attributeValues) {
//        allItems.add(value);
//      }
//    }
    return allItems;
  }


  /**
   * A method that returns the maximum number of items to be inserted to any bloom filter
   *
   * @return int
   * max number of items to be inserted into the bloom filter
   */
  public int getMaxNumItems() {
    int currentMax = 0;
    for (Student student : this.students
    ) {
      HashSet<String> current = this.extractStudentInformation(student);
      if (current.size() > currentMax) {
        currentMax = current.size();
      }
    }
    this.maxNumItems = currentMax;
    return currentMax;
  }


  /**
   * A method that creates a bloom filter for a given student from their corresponding set of items
   *
   * @param studentItems a set of items for the given students
   * @return Bloom Filter
   * a bloom filter for the given item
   */
  public BloomFilterBuilder createStudentBloomFilter(HashSet<String> studentItems) {
    int n = this.getMaxNumItems();
    BloomFilterBuilder studentBloom = new BloomFilterBuilder(n);
    for (String Item : studentItems
    ) {
      studentBloom.add(Item);
    }
    return studentBloom;
  }


  /**
   * A method that creates hashmap of student id to their bloom filters
   *
   * @return HashMap<Integer, BloomFilter < String>>
   * a map of student ids to their corresponsing Bloom Filter
   */
  public HashMap<String, BloomFilterBuilder> loadAllBlooms() {
    HashMap<String, BloomFilterBuilder> idsToFilters = new HashMap<>();
    for (Student student :
        this.students) {
//      System.out.println("id: " + student.getId());
//      System.out.println("hours: " + student.getHours());
//      System.out.println("CommunicationStyle: " + student.getCommunicationStyle());

      HashSet<String> studentInfo = this.extractStudentInformation(student);
      BloomFilterBuilder currentStudentBloom = this.createStudentBloomFilter(
          studentInfo);
      idsToFilters.put(String.valueOf(student.getId()), currentStudentBloom);
    }
    return idsToFilters;
  }
}
