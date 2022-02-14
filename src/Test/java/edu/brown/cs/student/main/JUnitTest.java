package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JUnitTest {

  @Test
  public void testLoad() throws IOException {
    List<String> tokens = new ArrayList<>();
    List<Star> starList = new ArrayList<>();
    tokens.add("stars");
    tokens.add("data/stars/stardata.csv");
    new Load(tokens, starList);
    assertEquals(119617, starList.size(), 0.01);
    assertEquals("1", starList.get(1).getId());
    assertEquals("20582", starList.get(20582).getId());

  }

  @Test
  public void testNaiveNeighborsCoord() {
    List<String> tokens = new ArrayList<>();
    List<Star> starList = new ArrayList<>();
    Star star1 = new Star("0", "Zero", 5, 2, 1);
    Star star2 = new Star("1", "One", 6, 6, 6);
    Star star3 = new Star("2", "Two", 3, 6, 2);
    tokens.add("naive_neighbors");
    tokens.add("1");
    tokens.add("1");
    tokens.add("1");
    tokens.add("1");
    starList.add(star1);
    starList.add(star2);
    starList.add(star3);
    new NaiveNeighbors(tokens, starList);
    assertEquals(star1,starList.get(0));
    assertEquals(star3,starList.get(1));
    assertEquals(star2,starList.get(2));
  }

  @Test
  public void testNaiveNeighborsName() {
    List<String> tokens = new ArrayList<>();
    List<Star> starList = new ArrayList<>();
    Star star1 = new Star("0", "Zero", 5, 2, 1);
    Star star2 = new Star("1", "One", 6, 6, 6);
    Star star3 = new Star("2", "Two", 3, 6, 2);
    tokens.add("naive_neighbors");
    tokens.add("1");
    tokens.add('"'+"Zero"+'"');
    starList.add(star1);
    starList.add(star2);
    starList.add(star3);
    new NaiveNeighbors(tokens, starList);
    assertEquals(star1,starList.get(0));
    assertEquals(star3,starList.get(1));
    assertEquals(star2,starList.get(2));
  }

  @Test
  public void testSortByDistance() {
    Star star1 = new Star("0", "One", 4, 4, 4);
    star1.calcDistance(0, 0, 0);
    Star star2 = new Star("1", "Two", 0, 0, 0);
    star2.calcDistance(0, 0, 0);
    SortByDistance sort = new SortByDistance();
    assertEquals(6, sort.compare(star1, star2), 0.01);

  }

  @Test
  public void testSortRandom() {
    List<String> tokens = new ArrayList<>();
    List<Star> starList = new ArrayList<>();
    Star star1 = new Star("0", "Zero", 1, 1, 1);
    Star star2 = new Star("1", "One", 1, 1, 1);
    Star star3 = new Star("2", "Two", 1, 1, 1);
    tokens.add("naive_neighbors");
    tokens.add("2");
    tokens.add("0");
    tokens.add("0");
    tokens.add("0");
    starList.add(star1);
    starList.add(star2);
    starList.add(star3);
    new NaiveNeighbors(tokens, starList);
    assertEquals(3.46,starList.get(0).getDistance()+starList.get(1).getDistance(),0.01);

  }

  @Test
  public void testStar() {
    Star star = new Star("ID", "Name", 1, 2, 3);
    assertEquals("ID", star.getId());
    assertEquals('"' + "Name" + '"', star.getName());
    assertEquals(1, star.getX(), 0.01);
    assertEquals(2, star.getY(), 0.01);
    assertEquals(3, star.getZ(), 0.01);
  }

  @Test
  public void testDistance() {
    Star star = new Star("ID", "Name", 2, 2, 2);
    star.calcDistance(0, 0, 0);
    assertEquals(3.46, star.getDistance(), 0.01);
  }

}
