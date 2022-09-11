package model;

/**
 * This class represents a scaling shape event.
 * @author whitneycai
 *
 */
public class ScaleShape extends AbstractEvent {
  
  private double fromWidth;
  private double toWidth;
  private double fromHeight;
  private double toHeight;

  /**
   * Construct a scale shape event.
   * @param shape shape to be scaled.
   * @param fromWidth previous width of the shape.
   * @param fromHeight previous height of the shape.
   * @param toWidth new width of the shape.
   * @param toHeight new height of the shape.
   * @throws IllegalArgumentException if the new dimensions are the same from the old ones.
   *         Or if the dimensions are invalid. 
   */
  public ScaleShape(Shape shape, double fromWidth, double fromHeight, 
      double toWidth, double toHeight) throws IllegalArgumentException {
    super(shape);
    if (fromWidth == toWidth && fromHeight == toHeight) {
      throw new IllegalArgumentException("Cannot scale to same dimensions.");
    }
    if (fromWidth <= 0 || fromHeight <= 0 || toWidth <= 0 || toHeight <= 0) {
      throw new IllegalArgumentException("Invalid dimensions.");
    }
    this.fromWidth = fromWidth;
    this.fromHeight = fromHeight;
    this.toWidth = toWidth;
    this.toHeight = toHeight;
  }
  
  @Override
  public String toString() {
    return "Shape " + this.shape.getName() + " scales from Width: " + this.fromWidth 
        + ", Height: " + this.fromHeight + " to Width: " + this.toWidth + ", Height: "
        + this.toHeight + " from t=" + this.getBeginTick() + " to t=" + this.getEndTick();
  }
  
  @Override
  public EventType getEventType() {
    return EventType.SCALE;
  }
  
  /**
   * Return string with height before scale.
   * @return string with height before scale.
   */
  public String fromHeightString() {
    return String.valueOf(this.fromHeight);
  }
  
  /**
   * Return string with height after scale.
   * @return string with height after scale.
   */
  public String toHeightString() {
    return String.valueOf(this.toHeight);
  }
  
  /**
   * Return string with width before scale.
   * @return string with width before scale.
   */
  public String fromWidthString() {
    return String.valueOf(this.fromWidth);
  }
  
  /**
   * Return string with width after scale.
   * @return string with width after scale.
   */
  public String toWidthString() {
    return String.valueOf(this.toWidth);
  }
  
  @Override
  public void applyEvent(Shape shape, int tick) {
    double tweeningW = tweening(tick, this.fromWidth, this.toWidth);
    double tweeningH = tweening(tick, this.fromHeight, this.toHeight);
    shape.setWidth(tweeningW);
    shape.setHeight(tweeningH);
  }

}
