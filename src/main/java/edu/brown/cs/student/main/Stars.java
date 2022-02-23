package edu.brown.cs.student.main;

// look into using these imports for your REPL!

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Stars class
 *
 * @author kkmarcus
 */
public class Stars {

  private String starID;
  private String properName;
  private String x;
  private String y;
  private String z;

  public Stars(String[] CSVParsedLine) {
    this.starID = CSVParsedLine[0];
    //this.starID = Integer.parseInt(CSVParsedLine[0]);
    this.properName = CSVParsedLine[1];
    this.x = CSVParsedLine[2];
    this.y = CSVParsedLine[3];
    this.z = CSVParsedLine[4];
//    this.x = Double.parseDouble(CSVParsedLine[2]);
//    this.y = Double.parseDouble(CSVParsedLine[3]);
//    this.z = Double.parseDouble(CSVParsedLine[4]);
  }

  public String getProperName() {
    return this.properName;
  }

  public String getStarID() {
    return this.starID;
  }

  public String getX() {
    return x;
  }

  public String getY() {
    return y;
  }

  public String getZ() {
    return z;
  }

  //  public static ArrayList<String[]> findStars(String filePath) throws FileNotFoundException,
//      IOException {
//    BufferedReader csvReader = new BufferedReader(new FileReader(filePath)); // read file
//    String starsData;
//    ArrayList<String[]> stars = new ArrayList<String[]>();
//    try {
//      int t = -1;
//      while ((starsData = csvReader.readLine()) != null) {
//        String[] args = starsData.split(","); // split CSV
//        String[] star = new String[5]; // create "star"
//        for (int i = 0; i < 5; i++) {
//          star[i] = args[i]; // add attributes of star (starID, propername, x, y, z)
//        }
//        if (t >= 0) {
//          stars.add(star); // add star to arraylist
//        }
//        t++;
//      }
//      REPL.loadStars(stars);
//      System.out.println("Read " + t + " stars from " + filePath); // print message
//    } catch (FileNotFoundException e) {
//      System.out.println("ERROR: invalid file input");
//      e.printStackTrace();
//      throw new FileNotFoundException("file not found");
//    } catch (java.io.IOException e) {
//      System.out.println("ERROR: invalid file input");
//    }
//    return stars;
//  }

//  @Override
//  public T createObjectWithLineOfData(String[] lineOfData) {
//    T Stars = null;
//    return Stars;
//  }
}
