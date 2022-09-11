package model;

/**
 * This class represents a move shape event.
 * @author whitneycai
 *
 */
public class MoveShape extends AbstractEvent {
  
  private Point2D from;
  private Point2D to;

  /**
   * Construct a MoveShape event.
   * @param shape shape of event.
   * @param fromX original x coordinate of shape.
   * @param fromY original y coordinate of shape.
   * @param toX new x coordinate of shape.
   * @param toY new y coordinate of shape.
   * @throws IllegalArgumentException if the 2 locations are the same.
   */
  public MoveShape(Shape shape, double fromX, double fromY, double toX, double toY) 
      throws IllegalArgumentException {
    super(shape);
    if (fromX == toX && fromY == toY) {
      throw new IllegalArgumentException("Cannot move to same position.");
    }
    this.from = new Point2D(fromX, fromY);
    this.to = new Point2D(toX, toY);
  }
  
  @Override
  public String toString() {
    return "Shape " + this.shape.getName() + " moves from " + this.from.toString() + " to "
        + this.to.toString() + " from t=" + this.getBeginTick() + " to t=" + this.getEndTick();
  }
  
  @Override
  public EventType getEventType() {
    return EventType.MOVE;
  }
  
  /**
   * Return the from location.
   * @return from location.
   */
  public Point2D getFrom() {
    return this.from;
  }
  
  /**
   * Return the to location.
   * @return to location.
   */
  public Point2D getTo() {
    return this.to;
  }
  
  @Override
  public void applyEvent(Shape shape, int tick) {
    double tweeningX = tweening(tick, this.from.getX(), this.to.getX());
    double tweeningY = tweening(tick, this.from.getY(), this.to.getY());
    shape.setLocation(tweeningX, tweeningY);
  }

}
