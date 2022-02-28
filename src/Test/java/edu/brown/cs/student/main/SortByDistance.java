package edu.brown.cs.student.main;

import java.util.Comparator;
import java.util.Random;

public class SortByDistance implements Comparator<Star> {

  public int compare(Star star1, Star star2) {
    Random rand = new Random();
    int result = (int) (star1.getDistance() - star2.getDistance());
    if (result == 0) {
      result = (rand.nextInt(2) - 1);
    }
    return result;
  }

}
