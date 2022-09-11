package model;

/**
 * This class represents an AbstractEvent object.
 * @author whitneycai
 *
 */
public abstract class AbstractEvent implements Event {
  
  protected Shape shape;
  protected int eventBegin;
  protected int eventEnd;
  
  /**
   * Construct a shape change event on the given shape.
   * @param shape shape of this event.
   * @throws IllegalArgumentException if shape is null.
   */
  public AbstractEvent(Shape shape)  throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("Invalid shape.");
    }
    this.shape = shape;
  }

  @Override
  public void setBeginTick(int begin) throws IllegalArgumentException {
    if (begin < 0) {
      throw new IllegalArgumentException("Event begin tick cannot be negative.");
    }
    this.eventBegin = begin;

  }

  @Override
  public void setEndTick(int end) throws IllegalArgumentException {
    if (end < 0 || end < this.eventBegin) {
      throw new IllegalArgumentException("Event end tick cannot be negative "
          + "or less than begin tick.");
    }
    this.eventEnd = end;

  }

  @Override
  public int getBeginTick() {
    return this.eventBegin;
  }

  @Override
  public int getEndTick() {
    return this.eventEnd;
  }
  
  /**
   * Helper method for tweening using linear interpolation.
   * @param tick time.
   * @param from a value.
   * @param to b value.
   * @return tweening value.
   */
  protected double tweening(int tick, double from, double to) {
    double valueDif = to - from;
    double timeDif = this.eventEnd - this.eventBegin;
    double interP = (tick - this.eventBegin) / timeDif;
    interP = Math.max(Math.min(interP, 1), 0);
    double result = (interP * valueDif) + from;
    return result;
  }

}
