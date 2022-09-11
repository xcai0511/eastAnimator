package view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JCheckBox;
import javax.swing.Timer;

import model.AnimationModel;
import model.Event;
import model.Shape;

/**
 * This class represents a text view.
 * It shows a textual description of the animation.
 * @author whitneycai
 *
 */
public class TextView implements View {
  
  private AnimationModel model;
  private final PrintStream out;
  private final StringBuilder sb;
  
  /**
   * Construct a text view.
   * @param model model of the view.
   * @param outString name of output file.
   * @throws IOException if out is not found.
   */
  public TextView(AnimationModel model, String outString) throws IOException {
    this.model = model;
    this.sb = new StringBuilder();
    
    if (outString.equals("System.out")) {
      this.out = new PrintStream(System.out);
    } else {
      this.out = new PrintStream(outString);
    }
  }
  
  /**
   * Build the text string for the view.
   */
  private void buildTextString() {
    List<Shape> shapeList = new ArrayList<>(model.getShapeMap().keySet());
    
    for (Shape shape : shapeList) {
      if (shape.getColor() == null || shape.getLocation() == null
          || shape.getHeight() == 0 || shape.getWidth() == 0) {
        throw new IllegalArgumentException("Shape is not completed yet.");
      }
      sb.append(shape.createString());
      sb.append("\n");
    }
    sb.append("\n");
    
    for (Shape shape: shapeList) {
      sb.append(shape.timeToString());
      sb.append("\n");
    }
    sb.append("\n");
    
    // sort event by their begin tick.
    Comparator<Event> sort = Comparator.comparingInt(Event::getBeginTick);
    
    List<Event> eventList = model.getShapeMap().values().stream()
            .flatMap(Collection::stream).sorted(sort).collect(Collectors.toList());
    
    for (Event e : eventList) {
      sb.append(e.toString());
      sb.append("\n");
    }
  }

  @Override
  public void createView() {
    buildTextString();
    out.print(sb.toString());
    out.close();
    System.exit(0);

  }

  @Override
  public String getViewState() {
    buildTextString();
    return sb.toString();
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
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

  @Override
  public void setStopButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

  @Override
  public void setRestartButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

  @Override
  public void setFasterButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

  @Override
  public void setSlowerButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

  @Override
  public void setLoopButtonListener(ItemListener itemEvent) {
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

  @Override
  public void drawShapes(int tick) {
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("This does not apply to text view.");
  }

  @Override
  public void setTick(int tick) {
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

  @Override
  public JCheckBox getLoopCheckBox() {
    throw new UnsupportedOperationException("This does not apply to text view.");
  }

  @Override
  public void enableLoop() {
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

  @Override
  public void disableLoop() {
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

  @Override
  public void setSpeed(int speed) {
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

  @Override
  public int getSpeed() {
    throw new UnsupportedOperationException("This does not apply to text view.");
  }

  @Override
  public void play() {
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

  @Override
  public void printView() {
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

  @Override
  public void clearModel() {
    throw new UnsupportedOperationException("This does not apply to text view.");
    
  }

}
