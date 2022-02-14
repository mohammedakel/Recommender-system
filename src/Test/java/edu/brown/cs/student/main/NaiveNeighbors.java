package edu.brown.cs.student.main;

import java.util.Collections;
import java.util.List;

public class NaiveNeighbors {
  public NaiveNeighbors(List<String> tokens, List<Star> starList) {
    if (starList.isEmpty()) {
      System.out.println("ERROR: There are no data");
    } else {
      if (tokens.size() == 5) {
        try {
          double k = Double.parseDouble(tokens.get(1));
          double x = Double.parseDouble(tokens.get(2));
          double y = Double.parseDouble(tokens.get(3));
          double z = Double.parseDouble(tokens.get(4));

          for (int i = 0; i < starList.size(); i++) {
            starList.get(i).calcDistance(x, y, z);
          }

          Collections.sort(starList, new SortByDistance());

          for (int i = 0; i < k; i++) {
            if (i == starList.size()) {
              break;
            }
            System.out.println(starList.get(i).getId());
          }

        } catch (Exception e) {
          System.out.println("ERROR: Coordinate is not an integer");
        }
      } else if (tokens.size() == 3) {
        Star origin = null;
        double k = Double.parseDouble(tokens.get(1));
        for (int i = 0; i < starList.size(); i++) {
          if (starList.get(i).getName().equals(tokens.get(2))) {
            origin = starList.get(i);
            break;
          }
        }
        if (origin == null) {
          System.out.println("ERROR: Star does not exist");
        } else {
          for (int i = 0; i < starList.size(); i++) {
            starList.get(i).calcDistance(origin.getX(), origin.getY(), origin.getZ());
          }
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
