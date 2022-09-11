package model;

import java.util.List;
import java.util.Map;

/**
 * This interface represents an animation.
 * It includes  methods that all animation classes should offer.
 * @author whitneycai
 *
 */
public interface AnimationModel {
  
  /**
   * Create s shape.
   * @param shape shape.
   * @throws IllegalArgumentException if shape is null or already exist in the map.
   */
  void addShape(Shape shape) throws IllegalArgumentException;
  
  /**
   * Create a shape with given appear and disappear time.
   * @param shape shape.
   * @param appearTime appear time of shape.
   * @param disappearTime disappear time of shape.
   * @throws IllegalArgumentException if shape is null, or if appear and disappearTime are invalid
   *         (are negative or disappear time is less than appear time), or if the shape already 
   *         exists.
   */
  void addShape(Shape shape, int appearTime, int disappearTime) throws IllegalArgumentException;
  
  /**
   * Return shape based on given shape name.
   * @param shapeName name of shape.
   * @return shape.
   * @throws IllegalArgumentException if the shape is not found.
   */
  Shape getShape(String shapeName) throws IllegalArgumentException;
  
  /**
   * Return the map of shapes and event list.
   * @return map of shapes and event list.
   */
  Map<Shape, List<Event>> getShapeMap();
  
  /**
   * Create an event to the given shape with begin and end tick.
   * @param shape shape.
   * @param event event of the shape.
   * @param beginTick begin tick of the event.
   * @param endTick end tick of the event.
   * @throws IllegalArgumentException if shape or event are null, or if begin or end tick
   *         are invalid, or if shape cannot be found in the list, or if same event already exist.
   */
  void addEvent(Shape shape, Event event, int beginTick, int endTick) 
      throws IllegalArgumentException;
  
  /**
   * Return list of event based on given shape.
   * @param shape shape of event.
   * @return list of event based on given shape.
   */
  List<Event> getEventList(Shape shape);
  
  /**
   * Return list of shapes at given tick.
   * @param tick tick of frame.
   * @return list of shapes.
   * @throws IllegalArgumentException if tick is negative.
   */
  List<Shape> getShapeAtTick(int tick) throws IllegalArgumentException;
  
  /**
   * Set up canvas with given information.
   * @param x x coordinate of the canvas.
   * @param y y coordinate of the canvas.
   * @param width width of the canvas.
   * @param height height of the canvas.
   */
  void setCanvas(int x, int y, int width, int height);
  
  /**
   * Return x coordinate of the canvas.
   * @return x coordinate of the canvas.
   */
  int getCanvasX();
  
  /**
   * Return y coordinate of the canvas.
   * @return y coordinate of the canvas.
   */
  int getCanvasY();
  
  /**
   * Return canvas width.
   * @return canvas width.
   */
  int getCanvasWidth();
  
  /**
   * Return canvas height.
   * @return canvas height.
   */
  int getCanvasHeight();
  
  /**
   * Set the end tick of the model.
   * @param tick end tick.
   */
  void setEndTick(int tick);
  
  /**
   * Return end tick of the model.
   * @return end tick of the model.
   */
  int getEndTick();
  
  /**
   * Removes all shapes and events from the map.
   */
  void clearAll();

}
