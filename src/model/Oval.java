package model;

/**
 * This class represents an Oval shape.
 * @author whitneycai
 *
 */
public class Oval extends AbstractShape {
  
  private final ShapeType shapeType = ShapeType.OVAL;
  private double radiusX = 0;
  private double radiusY = 0;

  /**
   * Construct an oval with given name, dimensions, color values, and position coordinates.
   * @param name name of the oval.
   * @param width width of the oval.
   * @param height height of the oval.
   * @param red red value of the oval's color.
   * @param green green value of the oval's color.
   * @param blue blue value of the oval's color.
   * @param x x coordinate of the oval's location.
   * @param y y coordinate of the oval's location.
   * @throws IllegalArgumentException if width or height are negative, or if name is null.
   */
  public Oval(String name, double width, double height, int red, int green, int blue, double x,
      double y) throws IllegalArgumentException {
    super(name, width, height, red, green, blue, x, y);
    this.radiusX = width / 2;
    this.radiusY = height / 2;
  }
  
  /**
   * Construct an oval with given name.
   * @param name name of oval.
   * @throws IllegalArgumentException is name is null.
   */
  public Oval(String name) throws IllegalArgumentException {
    super(name);
  }
  
  @Override
  public ShapeType getType() {
    return ShapeType.OVAL;
  }
  
  @Override
  public String toString() {
    return "Name: " + this.name + "\n"
        + "Type: " + this.shapeType.toString() + "\n"
        + "Center: " + this.location.toString() + ", X radius: " + this.radiusX
        + ", Y radius: " + this.radiusY + ", Color: " + this.color.toString() + "\n"
        + "Appears at t=" + this.appearTime + "\n"
        + "Disappears at t=" + this.disappearTime + "\n";
  }
  
  @Override
  public void setWidth(double width) {
    super.setWidth(width);
    this.radiusX = width / 2;
  }
  
  @Override
  public void setHeight(double height) {
    super.setHeight(height);
    this.radiusY = height / 2;
  }
  
  @Override
  public String createString() {
    return "Create " + this.color.toColor() + " " + this.shapeType.toString() + " " + this.name
        + " with center at " + this.location.toString() + ", radius " + this.radiusX + " and "
        + this.radiusY;
  }
  
  @Override
  public Shape copy() {
    return new Oval(this.name, this.width, this.height, this.color, this.location, 
        this.appearTime, this.disappearTime);
  }
  
  /**
   * Make a copy of this oval.
   * @param name name of oval.
   * @param width width of oval.
   * @param height height of oval.
   * @param color color of oval. 
   * @param location location coordinates of oval.
   * @param appearTime appear time of oval. 
   * @param disappearTime disappear time of oval.
   */
  public Oval(String name, double width, double height, Color color, Point2D location,
      int appearTime, int disappearTime) {
    super(name, width, height, color, location, appearTime, disappearTime);
    this.radiusX = width / 2;
    this.radiusY = height / 2;
  }

}
