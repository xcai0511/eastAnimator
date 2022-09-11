package model;

/**
 * This enum represents the types of the shapes.
 * Ovals and ellipses will be treated the same.
 * @author whitneycai
 *
 */
public enum ShapeType {
  RECTANGLE, OVAL, ELLIPSE;
  
  /**
   * Return string representing the type of the shape.
   * @return string representing the type of shape.
   */
  @Override
  public String toString() {
    String s;
    
    switch (this) {
      case RECTANGLE:
        s = "rectangle";
        break;
      case OVAL:
        s = "ellipse";
        break;
      case ELLIPSE:
        s = "ellipse";
        break;
      default:
        s = "Invalid shape.";
        break;
    }
    return s;

  }

}
