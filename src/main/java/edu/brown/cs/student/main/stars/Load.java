package edu.brown.cs.student.main.stars;

import edu.brown.cs.student.main.stars.Star;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Load {
  public Load(List<String> tokens, List<edu.brown.cs.student.main.stars.Star> starList) throws IOException {
    try {
      starList.clear();
      if (tokens.size() == 2) {
        File stars = new File(tokens.get(1));
        BufferedReader br2 = new BufferedReader(new FileReader(stars));
        String dataLine = br2.readLine();
        if (dataLine.equals("StarID,ProperName,X,Y,Z")) {
          dataLine = br2.readLine();
          while (dataLine != null) {
            List<String> starData = Arrays.asList(dataLine.split(","));

            double xCoord = Double.parseDouble(starData.get(2));
            double yCoord = Double.parseDouble(starData.get(3));
            double zCoord = Double.parseDouble(starData.get(4));

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
