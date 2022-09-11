package model;

/**
 * This class represents a 2D point. A Point2D has x and y coordinate.
 * @author whitneycai
 *
 */
public class Point2D {
  
  private double x;
  private double y;
  
  /**
   * Construct a 2d point with given x and y coordinates.
   * @param x x coordinate of the point.
   * @param y y coordinate of the point.
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Return the x coordinate of the point.
   * @return x coordinate of the point.
   */
  public double getX() {
    return this.x;
  }
  
  /**
   * Return the y coordinate of the point.
   * @return y coordinate of the point.
   */
  public double getY() {
    return this.y;
  }
  
  
  /**
   * Return a string representing the coordinate of the point.
   * @return string representing the coordinate of the point.
   */
  @Override
  public String toString() {
    return String.format("(%.1f,%.1f)", this.x, this.y);
  }

}
