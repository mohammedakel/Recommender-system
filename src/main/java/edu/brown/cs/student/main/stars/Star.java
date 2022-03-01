package edu.brown.cs.student.main.stars;

/**
 * Star class contains getter and setter methods for each properties of Star object. Each star object
 * is created from each line of the given CSV file. The properties are used to search for a specified
 * star or calculate distance away from a certain coordinate to later sort every Star object in a list
 *
 * @author Hannah Jeon
 */

public class Star {
  private String _id;
  private String _name;
  private double _x;
  private double _y;
  private double _z;
  private double _distance;

  /**
   * When an object of Star is instantiated, it takes in five variables as arguments and sets the
   * corresponding instance variable as the give value. Quotation marks are added to the name to
   * match the command convention.
   * @param id ID value of the star
   * @param name Name of the star
   * @param x x coordinate of the star's location
   * @param y y coordinate of the star's location
   * @param z z coordinate of the star's location
   */
  public Star(String id, String name, double x, double y, double z) {
    _id = id;
    _name = '"' + name + '"';
    _x = x;
    _y = y;
    _z = z;
    _distance = 0;

  }

  /**
   * Getter to return the ID of the Star.
   * Each Star in an input dataset has a unique ID.
   * Did not convert ID into int because there is no practical need to do so currently.
   *
   * @return String _id of the star
   */
  public String getId() {
    return _id;
  }

  /**
   * Getter to return the name of the Star.
   * There may be duplicate star names in an input dataset.
   *
   * @return String _name of the star
   */
  public String getName() {
    return _name;
  }

  /**
   * Getter to return the x coordinate of the Star.
   *
   * @return double _x of the star
   */
  public double getX() {
    return _x;
  }

  /**
   * Getter to return the y coordinate of the Star.
   *
   * @return double _y of the star
   */
  public double getY() {
    return _y;
  }

  /**
   * Getter to return the z coordinate of the Star.
   *
   * @return double _z of the star
   */
  public double getZ() {
    return _z;
  }

  /**
   * Getter to return the calculated distance of the Star.
   * The distance is used to sort all stars by shortest distance away to furthest.
   *
   * @return double _distance of the star
   */
  public double getDistance() {
    return _distance;
  }

  /**
   * Calculates this star's distance away from the given coordinate point using the distance formula.
   * The calculated distance is set as the _distance variable.
   * @param x x coordinate of a point
   * @param y y coordinate of a point
   * @param z z coordinate of a point
   */
  public void calcDistance(double x, double y, double z) {
    double distance;
    _x = Math.pow(_x - x, 2);
    _y = Math.pow(_y - y, 2);
    _z = Math.pow(_z - z, 2);
    distance = Math.sqrt(_x + _y + _z);

    _distance = distance;
  }

}