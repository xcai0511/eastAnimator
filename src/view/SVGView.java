package view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.Timer;

import model.AnimationModel;
import model.Event;
import model.EventType;
import model.MoveShape;
import model.ScaleShape;
import model.RecolorShape;
import model.Shape;
import model.ShapeType;

/**
 * This class represents SVG view.
 * It produces a textual description of the animation that is compliant with SVG file format.
 * @author whitneycai
 *
 */
public class SVGView implements View {
  
  private AnimationModel model;
  private int speed;
  private StringBuilder stringBuilder;
  private PrintStream out;
  
  /**
   * Construct a SVG view.
   * @param model model of the view.
   * @param outString string representing the out file.
   * @param speed speed of animation.
   * @throws IOException if file is not found.
   */
  public SVGView(AnimationModel model, String outString, int speed) throws IOException {
    this.model = model;
    this.speed = speed;
    this.stringBuilder = new StringBuilder();
    
    if (outString.equals("System.out")) {
      this.out = new PrintStream(System.out);
    } else {
      this.out = new PrintStream(outString);
    }
    
  }
  
  /**
   * Build the SVG view output string.
   */
  private void buildSVGString() {
    String str = "<svg width=\"" + model.getCanvasWidth() + "\" height=\"" + model.getCanvasHeight()
            + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n";
    stringBuilder.append(str);
    
    for (Shape shape : model.getShapeMap().keySet()) {
      if (shape.getColor() == null || shape.getLocation() == null 
          || shape.getWidth() == 0 || shape.getHeight() == 0) {
        throw new IllegalArgumentException("Shape is not completed yet.");
      }
      
      if (shape.getType() == ShapeType.RECTANGLE) {
        stringBuilder.append(rectangleString(shape));
        List<Event> listOfEvent = new ArrayList<>(model.getShapeMap().get(shape));
        for (Event event : listOfEvent) {
          eventString(shape, event);
        }
        stringBuilder.append("</rect>\n\n");
      }
      
      if (shape.getType() == ShapeType.ELLIPSE || shape.getType() == ShapeType.OVAL) {
        stringBuilder.append(ellipseString(shape));
        List<Event> listOfEvent = new ArrayList<>(model.getShapeMap().get(shape));
        for (Event event : listOfEvent) {
          eventString(shape, event);
        }
        stringBuilder.append("</ellipse>\n\n");
      }
      
    }
    stringBuilder.append("</svg>");
    
  }
  
  /**
   * Return a string with SVG format representing a rectangle.
   * @param shape the rectangle shape.
   * @return SVG format string.
   */
  private String rectangleString(Shape shape) {
    int locationX = (int) (shape.getLocation().getX() - model.getCanvasX());
    int locationY = (int) (shape.getLocation().getY() - model.getCanvasY());
    return "<rect id=\"" + shape.getName() + "\" x=\"" + locationX + "\" y=\""
            + locationY + "\" width=\"" + shape.getWidth() + "\" height=\""
            + shape.getHeight() + "\" fill=\"rgb" + shape.getColor() + "\" visibility="
            + "\"visible\" >\n\n";
  }
  
  /**
   * Return a SVG format string representing an ellipse.
   * @param shape the ellipse shape.
   * @return SVG format string.
   */
  private String ellipseString(Shape shape) {
    int locationX = (int) (shape.getLocation().getX() - model.getCanvasX());
    int locationY = (int) (shape.getLocation().getY() - model.getCanvasY());
    return "<ellipse id=\"" + shape.getName() + "\" cx=\"" + locationX
            + "\" cy=\"" + locationY + "\" rx=\"" + shape.getWidth() / 2 + "\" ry=\""
            + shape.getHeight() / 2 + "\" fill=\"rgb" + shape.getColor() + "\" visibility="
            + "\"visible\" >\n\n";
  }
  
  /**
   * Add shape events to stringBuilder.
   * @param shape shape of event.
   * @param event event.
   */
  private void eventString(Shape shape, Event event) {
    if (event.getEventType() == EventType.MOVE) {
      stringBuilder.append(moveString(shape, (MoveShape) event));
    }
    if (event.getEventType() == EventType.SCALE) {
      stringBuilder.append(scaleString(shape, (ScaleShape) event));
    }
    if (event.getEventType() == EventType.RECOLOR) {
      stringBuilder.append(recolorString(shape, (RecolorShape) event));
    }
  }
  
  /**
   * Return a SVG format string representing a move event.
   * @param s shape of event.
   * @param e move event.
   * @return SVG format string.
   */
  private String moveString(Shape s, MoveShape e) {
    float duration = (e.getEndTick() - e.getBeginTick()) / (float) speed;
    int fromX = (int) (e.getFrom().getX() - model.getCanvasX());
    int fromY = (int) (e.getFrom().getY() - model.getCanvasY());
    int toX = (int) (e.getTo().getX() - model.getCanvasX());
    int toY = (int) (e.getTo().getY() - model.getCanvasY());
    
    if (s.getType() == ShapeType.RECTANGLE) {
      return "<animate attributeType=\"xml\" begin=\"" + (e.getBeginTick() * 100 / speed) + "ms\""
              + " dur=\"" + (duration * 100) + "ms\" attributeName=\"x\" from=\""
              + fromX + "\" to=\"" + toX + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + (e.getBeginTick() * 100) / speed 
              + "ms\""
              + " dur=\"" + (duration * 100) + "ms\" attributeName=\"y\" from=\""
              + fromY + "\" to=\"" + toY + "\" fill=\"freeze\" />\n";
    }
    if (s.getType() == ShapeType.ELLIPSE || s.getType() == ShapeType.OVAL) {
      return "<animate attributeType=\"xml\" begin=\"" + e.getBeginTick() * 100 / speed + "ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"cx\" from=\""
              + fromX + "\" to=\"" + toX + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getBeginTick() * 100 / speed + "ms\""
              + " dur=\"" + duration * 100 + "ms\" attributeName=\"cy\" from=\""
              + fromY + "\" to=\"" + toY + "\" fill=\"freeze\" />\n";
    }
    return "";
  }
  
  /**
   * Return SVG string representing a scale event.
   * @param s shape of event.
   * @param e scale event.
   * @return a SVG string.
   */
  private String scaleString(Shape s, ScaleShape e) {
    float duration = (e.getEndTick() - e.getBeginTick()) / speed;
    if (s.getType() == ShapeType.RECTANGLE) {
      return "<animate attributeType=\"xml\" begin=\"" + e.getBeginTick() * 100 / speed + "ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"height\" from=\""
              + e.fromHeightString() + "\" to=\"" + e.toHeightString() + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getBeginTick() * 100 / speed + "ms\""
              + " dur=\"" + duration * 100 + "ms\" attributeName=\"width\" from=\""
              + e.fromWidthString() + "\" to=\"" + e.toWidthString() + "\" fill=\"freeze\" />\n";
    }
    if (s.getType() == ShapeType.ELLIPSE || s.getType() == ShapeType.OVAL) {
      return "<animate attributeType=\"xml\" begin=\"" + e.getBeginTick() * 100 / speed + "ms\" "
              + "dur\"=" + duration * 100 + "ms\" attributeName=\"height\" from=\""
              + e.fromHeightString() + "\" to=\"" + e.toHeightString() + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getBeginTick() * 100 / speed + "ms\""
              + " dur=\"" + duration * 100 + "ms\" attributeName=\"width\" from=\""
              + e.fromWidthString() + "\" to=\"" + e.toWidthString() + "\" fill=\"freeze\" />\n";
    }
    return "";
  }
  
  /**
   * Return SVG format string representing recolor event.
   * @param s shape of event.
   * @param event recolor event.
   * @return SVG format string.
   */
  private String recolorString(Shape s, RecolorShape event) {
    float duration = (event.getEndTick() - event.getBeginTick()) / speed;
    return "<animate attributeType=\"xml\" begin=\"" + event.getBeginTick() * 100 / speed + "ms\""
            + " dur=\"" + duration * 100 + "ms\" attributeName=\"fill\" from=\"rgb"
            + event.fromColorString() + "\" to=\"rgb" + event.toColorString() 
            + "\" fill=\"freeze\" />\n";
  }

  @Override
  public void createView() {
    buildSVGString();
    out.print(stringBuilder.toString());
    out.close();
    System.exit(0);

  }

  @Override
  public String getViewState() {
    buildSVGString();
    return stringBuilder.toString();
  }

  @Override
  public AnimationModel getModel() {
    return this.model;
  }

  @Override
  public void setModel(AnimationModel model) {
    this.model = model;

  }

  @Override
  public void setStartButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

  @Override
  public void setStopButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

  @Override
  public void setRestartButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

  @Override
  public void setFasterButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

  @Override
  public void setSlowerButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

  @Override
  public void setLoopButtonListener(ItemListener itemEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

  @Override
  public void drawShapes(int tick) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  @Override
  public void setTick(int tick) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

  @Override
  public JCheckBox getLoopCheckBox() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  @Override
  public void enableLoop() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

  @Override
  public void disableLoop() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

  @Override
  public void setSpeed(int speed) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

  @Override
  public int getSpeed() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  @Override
  public void play() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

  @Override
  public void printView() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

  @Override
  public void clearModel() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
    
  }

}
