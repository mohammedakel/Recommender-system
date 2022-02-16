package edu.brown.cs.student.main;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

final class NaiveNeighbors implements REPL, Command {

  NaiveNeighbors() {
    String commandName = "naive_neighbors";
    this.addCommands(commandName, this);
  }

  public void execute(String[] args) {
    ArrayList<String[]> stars = REPL.starsLoaded();
    if (stars == null) { // if boolean false, stars data hasn't been loaded
      System.out.println("ERROR: No stars file loaded, run stars <filename> first");
    } else if (args.length < 3) {
      System.out.println(
          "ERROR: missing args, run naive_neighbors < k starName > or < k, x, y, z>");
    } else if (Integer.parseInt(args[1]) < 0) { // check that k isn't negative
      System.out.println("ERROR: k must be nonnegative");
    } else if (args[2].contains("\"")) { // ie is using "starname"
      if (stars.size() != 1) {
        this.getNeighborsFromName(args, stars, Integer.parseInt(args[1]));
      }
    } else if (args.length == 5) { // is using coordinates
      this.getNeighborsFromPosition(stars, Integer.parseInt(args[1]), args[2],
          args[3], args[4]);
    } else {
      System.out.println("ERROR: not correct amount of args");
    }
  }

  public static void getNeighborsFromPosition(ArrayList<String[]> stars, int k, String x, String y,
                                              String z) {
    int length = stars.size();
    HashMap<Double, String> starDistances = new HashMap<Double, String>();
    double[] distances = new double[length];

    double x1 = Double.parseDouble(x);  // turn into double
    double y1 = Double.parseDouble(y);
    double z1 = Double.parseDouble(z);

    for (int i = 0; i < length; i++) {
      String[] currStar = stars.get(i);
      String starsID = currStar[0];
      double x2 = Double.parseDouble(currStar[2]);
      double y2 = Double.parseDouble(currStar[3]);
      double z2 = Double.parseDouble(currStar[4]);
      double distance = calculateDistances(x1, y1, z1, x2, y2, z2);
      starDistances.put(distance, starsID); // use hash map to store star id and distance
      distances[i] = distance; // add distance to array of distances
    }

    Arrays.sort(distances); // sort the array

    int numIterations = k;
    if (k > length) {
      numIterations =
          length; // if k is greater than number of stars, want to do fewer iterations so we don't get index out of bounds
    }
    for (int i = 0; i < numIterations; i++) {
      System.out.println(starDistances.get(
          distances[i])); // since distances[] is in ascending order, print k stars starting at smallest
    }
  }

  public static void getNeighborsFromName(String[] args, ArrayList<String[]> stars, int k) {
    int length = stars.size();
    String name = getStarName(args);
    boolean starNameCorrect =
        false; // boolean to confirm the star name is in the arraylist from CSV

    for (int i = 0; i < length; i++) { // loop through all stars
      String[] currStar = stars.get(i);
      String starName = currStar[1];
      if (starName.equals(name)) {
        starNameCorrect = true; // set boolean to true, found starname
        String x = currStar[2];
        String y = currStar[3];
        String z = currStar[4];
        stars.remove(currStar); // remove so that it is not included in naive_neighbors
        getNeighborsFromPosition(stars, k, x, y, z); // call get neighbors using stars coordinates
        stars.add(currStar); // add star back
        break;
      }
    }
    if (!starNameCorrect) { // if the name was not found, star DNE
      System.out.println("ERROR: No star with that name exists");
    }
  }

  public static double calculateDistances(double x1, double y1, double z1, double x2, double y2,
                                          double z2) {
    double sum =
        Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2); // use distance formula
    double distance = Math.sqrt(sum);
    return distance;
  }

  public static String getStarName(String[] args) {
    int size = args.length;
    String starName = "";
    for (int i = 2; i < size; i++) {
      starName = starName + " " + args[i]; // concatenate string so that "Lonely Star" is one arg instead of two
    }
    starName = starName.substring(2, starName.length() - 1); // remove quotation marks
    return starName;
  }
}
