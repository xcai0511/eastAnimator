package model;

/**
 * This class represents a Rectangle shape.
 * @author whitneycai
 *
 */
public class Rectangle extends AbstractShape {
  
  private final ShapeType shapeType = ShapeType.RECTANGLE;

  /**
   * Construct a rectangle shape with given name, dimensions, color values, and locations.
   * @param name name of the rectangle.
   * @param width width of the rectangle.
   * @param height height of the rectangle.
   * @param red red value of the rectangle.
   * @param green green value of the rectangle.
   * @param blue blue value of the rectangle.
   * @param x x coordinate of the rectangle.
   * @param y y coordinate of the rectangle.
   * @throws IllegalArgumentException if dimensions are not positive or name is null.
   */
  public Rectangle(String name, double width, double height, int red, int green, int blue, 
      double x, double y) throws IllegalArgumentException {
    super(name, width, height, red, green, blue, x, y);
  }
  
  /**
   * Construct a rectangle shape with given name.
   * @param name name of the rectangle.
   * @throws IllegalArgumentException if name is null.
   */
  public Rectangle(String name) throws IllegalArgumentException {
    super(name);
  }
  
  @Override
  public String toString() {
    return "Name: " + this.name + "\n"
        + "Type: " + this.shapeType.toString() + "\n"
        + "Min corner: " + this.location.toString() + ", Width: " + this.width + ", Height: "
        + this.height + ", Color: " + this.color.toString() + "\n"
        + "Appears at t=" + this.appearTime + "\n"
        + "Disappears at t=" + this.disappearTime + "\n";
  }
  
  @Override
  public ShapeType getType() {
    return ShapeType.RECTANGLE;
  }
  
  @Override
  public String createString() {
    return "Create " + this.color.toColor() + " " + this.shapeType.toString() + " " + this.name
        + " with corner at " + this.location.toString() + ", width " + this.width + " and height "
        + this.height;
  }
  
  @Override
  public Shape copy() {
    return new Rectangle(this.name, this.width, this.height, this.color, this.location, 
        this.appearTime, this.disappearTime);
  }
  
  /**
   * Make of copy of this rectangle.
   * @param name name of the rectangle.
   * @param width width of the rectangle.
   * @param height height of the rectangle.
   * @param color RGB color of the rectangle.
   * @param location location of the rectangle.
   * @throws IllegalArgumentException if dimensions are not positive or name is null.
   */
  public Rectangle(String name, double width, double height, Color color, Point2D location,
      int appearTime, int disappearTime) {
    super(name, width, height, color, location, appearTime, disappearTime);
  }
  
  
  

}
