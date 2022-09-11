package model;

/**
 * This interface includes the methods that all shape events should support.
 * @author whitneycai
 *
 */
public interface Event {
  
  /**
   * Set the begin tick of event.
   * @param begin begin tick of event.
   * @throws IllegalArgumentException if begin tick is negative.
   */
  void setBeginTick(int begin) throws IllegalArgumentException;
  
  /**
   * Set the end tick of event.
   * @param end end tick of event.
   * @throws IllegalArgumentException if end tick is negative or less than begin tick.
   */
  void setEndTick(int end) throws IllegalArgumentException;
  
  /**
   * Return the begin tick of event.
   * @return begin tick of event.
   */
  int getBeginTick();
  
  /**
   * Return the end tick of event.
   * @return end tick of event.
   */
  int getEndTick();
  
  /**
   * Return the type of event.
   * @return type of event.
   */
  EventType getEventType();
  
  /**
   * Apply event to the given shape with given event.
   * @param shape shape of event.
   * @param tick tick of event.
   */ 
  void applyEvent(Shape shape, int tick);
  
  

}
