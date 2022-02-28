package edu.brown.cs.student.main;

public class Star {
  private String _id;
  private String _name;
  private double _x;
  private double _y;
  private double _z;
  private double _distance;

  public Star(String id, String name, double x, double y, double z) {
    _id = id;
    _name = '"' + name + '"';
    _x = x;
    _y = y;
    _z = z;
    _distance = 0;

  }

  public String getId() {
    return _id;
  }

  public String getName() {
    return _name;
  }

  public double getX() {
    return _x;
  }

  public double getY() {
    return _y;
  }

  public double getZ() {
    return _z;
  }

  public double getDistance() {
    return _distance;
  }

  public void calcDistance(double x, double y, double z) {
    double distance;
    _x = Math.pow(_x - x, 2);
    _y = Math.pow(_y - y, 2);
    _z = Math.pow(_z - z, 2);
    distance = Math.sqrt(_x + _y + _z);

    _distance = distance;
  }

}