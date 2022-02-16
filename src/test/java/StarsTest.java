package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

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
    String[] args = {"naive_neighbors", "2", "'Sol'"};
    String starName = NaiveNeighbors.getStarName(args);
    assertEquals("Sol", starName);
  }

  @Test
  public void testWrongStarName() {
    String[] args = {"naive_neighbors", "2", "'Sols'"};
    ArrayList<String[]> stars = new ArrayList<String[]>();
    String[] star = {"0", "Sol", "0", "0", "0"};
    String[] star2 = {"87666", "Barnard's Star", "-0.01729", "-1.81533", "0.14824"};
    stars.add(star);
    stars.add(star2);
    String starName = "Sols";
    NaiveNeighbors.getNeighborsFromName(args, stars, 1 );
    // need to make code more testable ... lots of void function hard to test output

  }

  @Test
  public void testReadFileGetsCorrectNumStars() throws IOException {
    ArrayList<String[]> stars = Stars.findStars("data/stars/ten-star.csv");
    assertEquals(10, stars.size());

    ArrayList<String[]> stars2 = Stars.findStars("data/stars/one-star.csv");
    assertEquals(1, stars2.size());

    ArrayList<String[]> stars3 = Stars.findStars("data/stars/three-star.csv");
    assertEquals(3, stars3.size());
  }

  @Test
  public void testReadFilesReadsCSV() throws IOException {
    ArrayList<String[]> stars = Stars.findStars("data/stars/ten-star.csv");

    String[] star = stars.get(0);
    assertEquals("Sol", star[1]);

    String[] star2 = stars.get(8);
    assertEquals("Barnard's Star", star2[1]);
  }

}
