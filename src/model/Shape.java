package model;

/**
 * This Shape interface includes the methods that all types of shapes should support.
 * @author whitneycai
 *
 */
public interface Shape {
  
  /**
   * Return the name of the shape.
   * @return name of the shape.
   */
  String getName();
  
  /**
   * Set the name of the shape.
   * @param name name of the shape.
   */
  void setName(String name);
  
  /**
   * Return the type of the shape.
   * @return type of shape.
   */
  ShapeType getType();
  
  /**
   * Return the RGB color of the shape.
   * @return RGB color of the shape.
   */
  Color getColor();
  
  /**
   * Set the shape color with given red, green, blue values.
   * @param red red value of the RGB color.
   * @param green green value of the RGB color.
   * @param blue blue value of the RGB color.
   */
  void setColor(int red, int green, int blue);
  
  /**
   * Return the location coordinate of the shape.
   * @return the location coordinate of the shape.
   */
  Point2D getLocation();
  
  /**
   * Set the shape location to given point (x and y coordinates).
   * @param x x coordinate of the shape.
   * @param y y coordinate of the shape.
   */
  void setLocation(double x, double y);
  
  /**
   * Return the width of the shape or x radius if the shape is oval.
   * @return width of the shape.
   */
  double getWidth();
  
  /**
   * Set shape width to given value.
   * @param width width of the shape.
   */
  void setWidth(double width);
  
  /**
   * Return the height or the shape or y radius if the shape is oval.
   * @return height of the shape.
   */
  double getHeight();
  
  /**
   * Set shape height with given value.
   * @param height height of the shape.
   */
  void setHeight(double height);
  
  /**
   * Return the appear time of the shape.
   * @return appear time of the shape.
   */
  int getAppearTime();
  
  /**
   * Set appear time of the shape with given value.
   * @param time appear time of shape.
   */
  void setAppearTime(int time);
  
  /**
   * Return disappear time of the shape.
   * @return disappear time of the shape.
   */
  int getDisappearTime();
  
  /**
   * Set disappear time of the shape.
   * @param time disappear time of the shape.
   */
  void setDisappearTime(int time);
  
  /**
   * Return the text output of the shape's appear and disappear time.
   * @return text output of the shape's appear and disappear time.
   */
  String timeToString();
  
  /**
   * Return the text view output.
   * @return text view output.
   */
  String createString();
  
  /**
   * Make a copy of the shape.
   * @return new copy of the shape.
   */
  Shape copy();

}
