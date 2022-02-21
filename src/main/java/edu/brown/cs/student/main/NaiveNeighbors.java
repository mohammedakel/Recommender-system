package edu.brown.cs.student.main;

import java.util.Collections;
import java.util.List;

/**
 * NaiveNeighbors class is called when naive_neighbors command is used. It checks to see if the name
 * of the star or the number coordinates are given. Then, it calculates the distance of all stars
 * and sorts the stars from shortest to furthest distance away. It contains at instance of
 * SortByDistance to do so.
 */
public class NaiveNeighbors {
  /**
   * NaiveNeighbors takes in two lists as arguments when first created just like Load. Tokens list
   * contains the user input and starList is where all star objects are stored in. If coordinates
   * are given, the distance of each star from the given coordinate will be calculated then used to
   * sort. If a star name is given, it will loop to find the corresponding star from the star list and
   * get its coordinates. The rest will be the same except that the given star will be excluded from
   * the final printed list of closest stars.
   *
   * @param tokens List of tokenized user input
   * @param starList List of star objects
   */
  public NaiveNeighbors(List<String> tokens, List<Star> starList) {
    if (starList.isEmpty()) {
      System.out.println("ERROR: There are no data");
    } else {
      //If coordinates are given
      if (tokens.size() == 5) {
        try {
          //Change String to int/double
          int k = Integer.parseInt(tokens.get(1));
          double x = Double.parseDouble(tokens.get(2));
          double y = Double.parseDouble(tokens.get(3));
          double z = Double.parseDouble(tokens.get(4));

          //Calculate distance for each star
          for (int i = 0; i < starList.size(); i++) {
            starList.get(i).calcDistance(x, y, z);
          }

          //Sort
          Collections.sort(starList, new SortByDistance());

          //Print however many closest star
          for (int i = 0; i < k; i++) {
            if (i == starList.size()) {
              break;
            }
            System.out.println(starList.get(i).getId());
          }

        } catch (Exception e) {
          System.out.println("ERROR: Exception");
        }
        // If Name is given
      } else if (tokens.size() == 3) {
        Star origin = null;
        double k = Double.parseDouble(tokens.get(1));
        //Find the corresponding star object
        for (int i = 0; i < starList.size(); i++) {
          if (starList.get(i).getName().equals(tokens.get(2))) {
            origin = starList.get(i);
            break;
          }
        }
        if (origin == null) {
          System.out.println("ERROR: Star does not exist");
        } else {
          //Calculate distance
          for (int i = 0; i < starList.size(); i++) {
            starList.get(i).calcDistance(origin.getX(), origin.getY(), origin.getZ());
          }
          //Sort
          Collections.sort(starList, new SortByDistance());
          for (int i = 1; i <= k; i++) {
            if (i == starList.size()) {
              break;
            }

            System.out.println(starList.get(i).getId());
          }
        }
      } else {
        System.out.println("ERROR: Invalid command");
      }
    }
  }
}
