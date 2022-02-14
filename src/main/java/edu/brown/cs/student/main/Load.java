package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Load class is called when "stars" command is used. It creates a star object for each star specified
 * in a given CSV file and fills the star arraylist, which would later be used in naive neighbor.
 * It catches any exceptions or edge cases thrown by the input and CSV files.
 *
 * @author Hannah Jeon
 */
public class Load {
  /**
   * When instantiated, Load takes in two lists as arguments. Tokens list contains the user input and
   * starList is where all star objects will be stored in. It reads the file specified by the input
   * and creates a star object for each, which is then loaded into the star list for later use.
   * If the file is invalid or does not follow the proper format, it prints Error.
   *
   * @param tokens List of tokenized user input
   * @param starList List of star objects
   * @throws IOException
   */
  public Load(List<String> tokens, List<Star> starList) throws IOException {
    try {
      //Clear the star list every time
      starList.clear();
      if (tokens.size() == 2) {
        File stars = new File(tokens.get(1));
        //Creat file reader
        BufferedReader br2 = new BufferedReader(new FileReader(stars));
        String dataLine = br2.readLine();
        //Check header
        if (dataLine.equals("StarID,ProperName,X,Y,Z")) {
          dataLine = br2.readLine();
          while (dataLine != null) {
            //Split data by comma
            List<String> starData = Arrays.asList(dataLine.split(","));

            //Change string coordinate values into doubles for calculation
            double xCoord = Double.parseDouble(starData.get(2));
            double yCoord = Double.parseDouble(starData.get(3));
            double zCoord = Double.parseDouble(starData.get(4));

            //Create a star object and add to list
            starList.add(new Star(starData.get(0), starData.get(1), xCoord, yCoord, zCoord));
            dataLine = br2.readLine();
          }
          System.out.println("Read " + starList.size() + " stars from " + tokens.get(1));
        } else {
          System.out.println("ERROR: Invalid heading");
        }
      } else {
        System.out.println("ERROR: Wrong format");
      }
    } catch (IOException e) {
      System.out.println("ERROR: IOException");
    }
  }
}
