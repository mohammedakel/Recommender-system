package edu.brown.cs.student.main;

import edu.brown.cs.student.main.BloomFilterr.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * a class that tests the similarity generator class and its relevant methods
 *
 * */
public class SimilarityGeneratorTest {
  /**
   * a method that creates a sample student one
   *
   */
  private Student createStudentsOne() {
    Student studentOne = new Student(1, "name", "email", "gender", "class year",
        "ssn", "nationality", "race", 20, "communication style",
        3, "meeting style", "meeting time", 10, "strength",
        "weakness", "skills", "intrests");
    return studentOne;
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

  private Student createStudentsFour() {
    Student studentFour = new Student(4, "name", "email", "gender", "class year",
        "ssn", "nationality", "race", 20, "communication style",
        3, "meeting style", "meeting", 10, "strength",
        "weakness", "skills", "int");
    return studentFour;
  }



  // test that tests if a list of (id, bloom filter) is created from the given map of ids to bloom filters
  @Test
  public void testToPairsList() {
    Student one = this.createStudentsOne();
    Student two = this.createStudentsTwo();
    Student three = this.createStudentsThree();
    List<Student> studentsList = new ArrayList<Student>() {{
      add(one);
      add(two);
      add(three);
    } };
    BloomFiltersLoader loader = new BloomFiltersLoader(studentsList, 0.01);
    HashSet<String> oneInfo = loader.extractStudentInformation(one);
    HashSet<String> twoInfo = loader.extractStudentInformation(two);
    HashSet<String> threeInfo = loader.extractStudentInformation(three);
    HashMap<String, BloomFilterBuilder> idsToBlooms = loader.loadAllBlooms();
    assertEquals(3, idsToBlooms.size());
    List<SBLTuple> expectedResult = new ArrayList<SBLTuple>() {{
      add(new SBLTuple(one.getId(), loader.createStudentBloomFilter(oneInfo)));
      add(new SBLTuple(two.getId(), loader.createStudentBloomFilter(twoInfo)));
      add(new SBLTuple(three.getId(), loader.createStudentBloomFilter(threeInfo)));
    }};
    SimilarityGenerator generator = new SimilarityGenerator(idsToBlooms, 1);
    List<SBLTuple> result = generator.getIdBloomPairs();
    assertEquals(expectedResult.size(), result.size());
    SBLTuple tempFirstPair = new SBLTuple(one.getId(), loader.createStudentBloomFilter(oneInfo));
    assertEquals(tempFirstPair.id, result.get(0).id);
    assertEquals(2, result.get(1).id);
  }

  // a method that test Xnor bitArray and similarity score computation
  @Test
  public void testXNorBitArray(){
    Student one = this.createStudentsOne();
    Student two = this.createStudentsTwo();
    Student three = this.createStudentsThree();
    Student four = this.createStudentsFour();
    List<Student> studentsList = new ArrayList<Student>() {{
      add(one);
      add(two);
      add(three);
      add(four);
    } };
    BloomFiltersLoader loader = new BloomFiltersLoader(studentsList, 0.01);
    HashMap<String, BloomFilterBuilder>  idsToBlooms = loader.loadAllBlooms();
    BitArray target = new BitArray(3);
    BitArray current = new BitArray(3);
    SimilarityGenerator generator = new SimilarityGenerator(idsToBlooms, 1);
    BitArray resultOne = generator.computeXnorArray(target, current);
    BitArray expected = new BitArray(3);
    expected.set(0, true);
    expected.set(1, true);
    expected.set(2, true);
    assertEquals(expected, resultOne);
    int similarityScore = generator.similarityScore(expected);
    assertEquals(3, similarityScore);
    target.set(0, true);
    target.set(1, true);
    target.set(2, true);
    assertEquals("111", target.toString());
    assertEquals("000", current.toString());
    BitArray resultTwo = generator.computeXnorArray(target, current);
    assertEquals("000", resultTwo.toString());
    int similarityScoreTwo = generator.similarityScore(resultTwo);
    assertEquals(0, similarityScoreTwo);
    current.set(1, true);
    BitArray resultThree = generator.computeXnorArray(target, current);
    assertEquals("010", resultThree.toString());
    int similarityScoreThree = generator.similarityScore(resultThree);
    assertEquals(1, similarityScoreThree);
  }

  // a method to test if the similar students are returned given a desired number of similar students and a target
  // student id
  @Test
  public void testFindSimilarStudents(){
    Student one = this.createStudentsOne();
    Student two = this.createStudentsTwo();
    Student three = this.createStudentsThree();
    Student four = this.createStudentsFour();
    List<Student> studentsList = new ArrayList<Student>() {{
      add(one);
      add(two);
      add(three);
      add(four);
    } };
    BloomFiltersLoader loader = new BloomFiltersLoader(studentsList, 0.01);
    HashMap<String, BloomFilterBuilder>  idsToBlooms = loader.loadAllBlooms();
    assertEquals(4, idsToBlooms.size());
    SimilarityGenerator findSimilars = new SimilarityGenerator(idsToBlooms, 2);
    PriorityQueue<SBLTuple> result = findSimilars.findSimilar(1);
    assertEquals(2, result.size());
    String SecondSimilar = String.valueOf(result.poll().id);
    String firstSimilar = String.valueOf(result.poll().id);
    assertEquals("4", SecondSimilar);
    assertEquals("1", firstSimilar);
  }

}
