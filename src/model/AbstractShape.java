package model;

/**
 * This class represents an AbstractShape class that implements the methods of Shape interface.
 * @author whitneycai
 *
 */
public abstract class AbstractShape implements Shape {
  
  protected String name;
  protected double width;
  protected double height;
  protected Color color;
  protected Point2D location;
  protected int appearTime = -1;
  protected int disappearTime = -1;
  
  /**
   * Construct a shape with given name, width, height, RGB color values, and location coordinates.
   * @param name name of the shape.
   * @param width width of the shape.
   * @param height height of the shape.
   * @param red red value of the shape color.
   * @param green green value of the shape color.
   * @param blue blue value of the shape color.
   * @param x x coordinate of shape location.
   * @param y y coordinate of shape location.
   * @throws IllegalArgumentException if width or height is negative.
   * @throws IllegalArgumentException if name is null.
   */
  public AbstractShape(String name, double width, double height, int red, int green, int blue,
      double x, double y) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height should be positive.");
    }
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    this.name = name;
    this.width = width;
    this.height = height;
    this.color = new Color(red, green, blue);
    this.location = new Point2D(x, y);
  }
  
  /**
   * Construct a shape with given name.
   * @param name name of the shape.
   * @throws IllegalArgumentException if name is null.
   */
  public AbstractShape(String name) throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    this.name = name;

  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void setColor(int red, int green, int blue) {
    this.color = new Color(red, green, blue);

  }

  @Override
  public Point2D getLocation() {
    return this.location;
  }

  @Override
  public void setLocation(double x, double y) {
    this.location = new Point2D(x, y);

  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public void setWidth(double width) throws IllegalArgumentException {
    if (width <= 0) {
      throw new IllegalArgumentException("Width must be positive.");
    }
    this.width = width;

  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public void setHeight(double height) throws IllegalArgumentException {
    if (height <= 0) {
      throw new IllegalArgumentException("Height must be positive.");
    }
    this.height = height;

  }

  @Override
  public int getAppearTime() {
    return this.appearTime;
  }

  @Override
  public void setAppearTime(int time) throws IllegalArgumentException {
    if (time < 0) {
      throw new IllegalArgumentException("Appear time cannot be negative.");
    }
    this.appearTime = time;

  }

  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }

  @Override
  public void setDisappearTime(int time) throws IllegalArgumentException {
    if (time < 0) {
      throw new IllegalArgumentException("Disappear time cannot be negative.");
    }
    this.disappearTime = time;

  }

  @Override
  public String timeToString() {
    return this.getName() + " appears at t=" + this.appearTime
        + " and disappears at t=" + this.disappearTime;
  }
  
  /**
   * Creates a copy of this shape.
   * @param name name of the shape.
   * @param width width of the shape.
   * @param height height of the shape.
   * @param color color of the shape.
   * @param location location of the shape.
   * @param appearTime appear time of the shape.
   * @param disappearTime disappear time of the shape.
   */
  public AbstractShape(String name, double width, double height, Color color,
      Point2D location, int appearTime, int disappearTime) {
    this.name = name;
    this.width = width;
    this.height = height;
    this.color = color;
    this.location = location;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
  }

}
