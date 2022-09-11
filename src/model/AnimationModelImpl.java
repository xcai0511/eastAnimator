package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * This class represents animation model implementation.
 * It implements the methods of the AnimationModel interface.
 * @author whitneycai
 *
 */
public class AnimationModelImpl implements AnimationModel {
  
  private Map<Shape, List<Event>> shapeMap;
  private int canvasX;
  private int canvasY;
  private int canvasWidth;
  private int canvasHeight;
  protected int endTick;
  
  /**
   * Construct a LinkedHashMap to store shapes and associated list of events.
   */
  public AnimationModelImpl() {
    shapeMap = new LinkedHashMap<>();
  }

  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    if (validShape(shape)) {
      throw new IllegalArgumentException("A shape with this name already exist.");
    }
    shapeMap.put(shape, new ArrayList<>()); 

  }

  @Override
  public void addShape(Shape shape, int appearTime, int disappearTime) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    if (validShape(shape)) {
      throw new IllegalArgumentException("A shape with this name already exist.");
    }
    if (appearTime < 0) {
      throw new IllegalArgumentException("Invalid appear time");
    }
    if (disappearTime < 0 || disappearTime <= appearTime) {
      throw new IllegalArgumentException("Invalid disappear time");
    }
    
    shape.setAppearTime(appearTime);
    shape.setDisappearTime(disappearTime);
    shapeMap.put(shape, new ArrayList<>());

  }
  
  /**
   * Check if given shape has the same name with shapes that are already added.
   * @param shape new shape to be added.
   * @return true if the the names are the same, false otherwise.
   */
  private boolean validShape(Shape shape) {
    for (Shape s : this.shapeMap.keySet()) {
      if (s.getName().equals(shape.getName())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Shape getShape(String shapeName) throws IllegalArgumentException {
    Shape shape;
    for (Shape s : shapeMap.keySet()) {
      if (s.getName().equals(shapeName)) {
        shape = s;
        return shape;
      }
    }
    throw new IllegalArgumentException("Cannot find shape with this name.");
  }

  @Override
  public Map<Shape, List<Event>> getShapeMap() {
    return this.shapeMap;
  }

  @Override
  public void addEvent(Shape shape, Event event, int beginTick, int endTick) 
      throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null.");
    }
    if (event == null) {
      throw new IllegalArgumentException("Event cannot be null.");
    }
    if (beginTick < 0) {
      throw new IllegalArgumentException("Invalid begin tick.");
    }
    if (endTick <= 0 || endTick <= beginTick) {
      throw new IllegalArgumentException("Invalid end tick.");
    }
    // The event start before the shape appears, or after the shape disappears.
    if (beginTick < shape.getAppearTime() || beginTick >= shape.getDisappearTime()) {
      throw new IllegalArgumentException(
          "Invalid begin tick. " + "The event should begin while the shape is on the screen.");
    }
    // The event end before the shape appears, or after the shape disappears.
    if (endTick < shape.getAppearTime() || endTick > shape.getDisappearTime()) {
      throw new IllegalArgumentException(
          "Invalid end tick. " + "The event should end before the shape disappears.");
    }
    
    Set<Shape> shapeSet = shapeMap.keySet();
    List<Event> eventList = shapeMap.get(shape);
    if (eventList == null) {
      throw new IllegalArgumentException("Cannot find the shape.");
    }
    
    // check if the event can be added to the event list.
    for (Shape s : shapeSet) {
      // go to the correct shape
      if (shape.getName() == s.getName()) {
        for (Event otherE : eventList) {
          // same type of event.
          if (event.getEventType() == otherE.getEventType()) {
            // the two event (same type) starts at the same time.
            if (event.getBeginTick() < otherE.getBeginTick()
                && event.getEndTick() > otherE.getEndTick()
                || event.getBeginTick() > otherE.getBeginTick()
                && event.getEndTick() < otherE.getEndTick()
                || event.getBeginTick() < otherE.getEndTick()
                && event.getEndTick() > otherE.getEndTick()) {
              throw new IllegalArgumentException("Same type of events cannot overlap.");
            }
          }
        }
      }
    }
    event.setBeginTick(beginTick);
    event.setEndTick(endTick);
    eventList.add(event);

  }

  @Override
  public List<Event> getEventList(Shape shape) {
    return shapeMap.get(shape);
  }

  @Override
  public List<Shape> getShapeAtTick(int tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("Tick cannot be negative.");
    }
    List<Shape> listOfShape = new ArrayList<>();
    for (Shape s : shapeMap.keySet()) {
      if (s.getAppearTime() <= tick && s.getDisappearTime() >= tick) {
        Shape newShape = s.copy();
        listOfShape.add(newShape);
        for (Event e : this.getEventList(s)) {
          if (e.getBeginTick() <= tick) {
            e.applyEvent(newShape, tick);
          }
        }
      }
    }
    return listOfShape;
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.canvasX = x;
    this.canvasY = y;
    this.canvasWidth = width;
    this.canvasHeight = height;

  }

  @Override
  public int getCanvasX() {
    return this.canvasX;
  }

  @Override
  public int getCanvasY() {
    return this.canvasY;
  }

  @Override
  public int getCanvasWidth() {
    return this.canvasWidth;
  }

  @Override
  public int getCanvasHeight() {
    return this.canvasHeight;
  }

  @Override
  public void setEndTick(int tick) {
    this.endTick = tick;

  }

  @Override
  public int getEndTick() {
    return this.endTick;
  }

  @Override
  public void clearAll() {
    this.shapeMap.clear();

  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Shapes:\n");
    List<String> s = shapeMap.keySet().stream().map(Object::toString).collect(Collectors.toList());
    for (String l : s) {
      sb.append(l);
      sb.append("\n");
    }

    // Sort the events in terms of begin & end time
    Comparator<Event> sortByEventBegin = Comparator.comparingInt(Event::getBeginTick);

    // Create a list of sorted events
    List<Event> t = shapeMap.values().stream().flatMap(Collection::stream)
            .sorted(sortByEventBegin).collect(Collectors.toList());

    // Add the changes/events to sb
    for (Event a : t) {
      sb.append(a.toString());
      sb.append("\n");
    }

    return sb.toString();
  }
  

}
