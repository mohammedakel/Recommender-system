package edu.brown.cs.student.main;

// look into using these imports for your REPL!

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Stars class reads stars file and creates stars list
 *
 * @author kkmarcus
 */
final class Stars implements REPL, Command {

  Stars() {
    String commandName = "stars";
    this.addCommands(commandName, this);
  }

  public void execute(String[] args) {
    try {
      if (args.length == 1) {
        System.out.println("ERROR: No FileName");
      } else {
        String filePath = args[1];
        this.findStars(filePath);
      }
    } catch (IOException e) {
      System.out.println("ERROR: invalid file input");
      //e.printStackTrace();
    }
  }

  public static ArrayList<String[]> findStars(String filePath) throws FileNotFoundException,
      IOException {
    BufferedReader csvReader = new BufferedReader(new FileReader(filePath)); // read file
    String starsData;
    ArrayList<String[]> stars = new ArrayList<String[]>();
    try {
      int t = -1;
      while ((starsData = csvReader.readLine()) != null) {
        String[] args = starsData.split(","); // split CSV
        String[] star = new String[5]; // create "star"
        for (int i = 0; i < 5; i++) {
          star[i] = args[i]; // add attributes of star (starID, propername, x, y, z)
        }
        if (t >= 0) {
          stars.add(star); // add star to arraylist
        }
        t++;
      }
      REPL.loadStars(stars);
      System.out.println("Read " + t + " stars from " + filePath); // print message
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: invalid file input");
      e.printStackTrace();
      throw new FileNotFoundException("file not found");
    } catch (java.io.IOException e) {
      System.out.println("ERROR: invalid file input");
    }
    return stars;
  }
}
