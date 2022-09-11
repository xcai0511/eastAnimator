package model;

/**
 * This class represents a recolor event.
 * @author whitneycai
 *
 */
public class RecolorShape extends AbstractEvent {
  private Color from;
  private Color to;

  /**
   * Construct a recolor event on given shape.
   * @param shape shape of the event.
   * @param fromR red value of original color.
   * @param fromG green value of original color.
   * @param fromB blue value of original color.
   * @param toR red value of new color.
   * @param toG green value of new color.
   * @param toB blue value of new color.
   * @throws IllegalArgumentException is the two colors are the same or color values are invalid.
   */
  public RecolorShape(Shape shape, int fromR, int fromG, int fromB, int toR, int toG, int toB) 
      throws IllegalArgumentException {
    super(shape);
    if (fromR == toR && fromG == toG && fromB == toB) {
      throw new IllegalArgumentException("Cannot change to the same color.");
    }
    this.from = new Color(fromR, fromG, fromB);
    this.to = new Color(toR, toG, toB);
  }
  
  @Override
  public String toString() {
    return "Shape " + this.shape.getName() + " changes color from " + this.from.toString() + " to "
        + this.to.toString() + " from t=" + this.getBeginTick() + " to t=" + this.getEndTick();
  }
  
  @Override
  public EventType getEventType() {
    return EventType.RECOLOR;
  }
  
  /**
   * Return string with original color.
   * @return string with original color.
   */
  public String fromColorString() {
    return this.from.toString();
  }
  
  /**
   * Return string with new color.
   * @return string with new color.
   */
  public String toColorString() {
    return this.to.toString();
  }
  
  @Override
  public void applyEvent(Shape shape, int tick) {
    int tweeningR = (int)tweening(tick, this.from.getRed(), this.to.getRed());
    int tweeningG = (int)tweening(tick, this.from.getGreen(), this.to.getGreen());
    int tweeningB = (int)tweening(tick, this.from.getBlue(), this.to.getBlue());
    shape.setColor(tweeningR, tweeningG, tweeningB);
  }

}
