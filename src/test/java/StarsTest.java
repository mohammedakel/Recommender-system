package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StarsTest {

  @Test
  public void testDistance() {
    double x = 0;
    double y = 0;
    double z = 0;
    double x1 = 4;
    double y1 = 3;
    double z1 = 0;
    double distance = NaiveNeighbors.calculateDistances(x, y, z, x1, y1, z1);
    assertEquals(5, distance, 0.01);
  }

  @Test
  public void testStarName() {
    String[] args = {"0", "Sol", "0", "0", "0"};
    Stars star = new Stars(args);
    String starName = star.getProperName();
    assertEquals("Sol", starName);
  }

  @Test
  public void testWrongStarName() {
//    String[] args = {"naive_neighbors", "2", "'Sols'"};
//    List<Stars> stars = new ArrayList<>();
//    Stars star = new Stars(new String[] {"0, Sol, 0, 0, 0"});
//    Stars star2 = new Stars(new String[] {"87666, Barnard's Star, -0.01729, -1.81533, 0.14824"});
//    stars.add(star);
//    stars.add(star2);
//    String starName = "Sols";
//    NaiveNeighbors.getNeighborsFromName(starName, stars, 1 );
    // need to make code more testable ... lots of void function hard to test output

  }

  @Test
  public void testReadFileGetsCorrectNumStars() throws IOException {
    String filePath = "data/stars/ten-star.csv";
    CSVParser newParser = new CSVParser(filePath, Stars::new, true);
    newParser.readLine();
    List<Stars> stars = newParser.getListOfObjects();
    //ArrayList<String[]> stars = Stars.findStars("data/stars/ten-star.csv");
    assertEquals(10, stars.size());

    String filePath2 = "data/stars/one-star.csv";
    CSVParser newParser2 = new CSVParser(filePath2, Stars::new, true);
    newParser2.readLine();
    List<Stars> stars2 = newParser2.getListOfObjects();
    //ArrayList<String[]> stars2 = Stars.findStars("data/stars/one-star.csv");
    assertEquals(1, stars2.size());

    String filePath3 = "data/stars/three-star.csv";
    CSVParser newParser3 = new CSVParser(filePath3, Stars::new, true);
    newParser3.readLine();
    List<Stars> stars3 = newParser3.getListOfObjects();
    //ArrayList<String[]> stars3 = Stars.findStars("data/stars/three-star.csv");
    assertEquals(3, stars3.size());
  }

  @Test
  public void testReadFilesReadsCSV() throws IOException {
    String filePath = "data/stars/ten-star.csv";
    CSVParser newParser = new CSVParser(filePath, Stars::new, true);
    newParser.readLine();
    List<Stars> stars = newParser.getListOfObjects();
//    ArrayList<String[]> stars = Stars.findStars("data/stars/ten-star.csv");

    Stars starOne = stars.get(0);
    assertEquals("Sol", starOne.getProperName());

    Stars starTwo = stars.get(8);
    assertEquals("Barnard's Star", starTwo.getProperName());
  }

}
