package edu.brown.cs.student.main.kdtree;

import edu.brown.cs.student.main.stars.Star;

import java.util.Comparator;
import java.util.Random;

/**
 * This class implements Comparator interface to create a comparator that can be used to sort each
 * stars in a list by their distance away from the given point. An instance of this class will be
 * created inside Collection.sort() method.
 */
public class SortByDistance implements Comparator<Star> {
  /**
   * Compare takes in two star objects and compares the two distances by subtracting the two and
   * returns the result. To randomize ties, an instance of Random is created to produce a random int
   * between [-1,1] when the two stars are equal distance away from the given point.
   * @param star1 First star object
   * @param star2 Second star object
   * @return int result which could be a positive int, a negative int, or a 0.
   */
  public int compare(Star star1, Star star2) {
    Random rand = new Random();
    int result = (int)star1.getDistance() - (int)star2.getDistance();
    if (star1.getDistance() - star2.getDistance() == 0) {
      result = (rand.nextInt(2) - 1);
    }
    return result;
  }

}
