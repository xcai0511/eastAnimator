package util;

import model.AnimationModel;
import model.Event;
import model.MoveShape;
import model.Oval;
import model.RecolorShape;
import model.Rectangle;
import model.ScaleShape;
import model.Shape;
import model.ShapeType;

/**
 * Public class representing a builder.
 * It implements AnimationBuilder interface.
 * @author whitneycai
 *
 */
public final class Builder implements AnimationBuilder<AnimationModel> {
  
  private final AnimationModel model;
  
  /**
   * Construct a AnimationBuilderImpl with given model.
   * @param model model of animation.
   */
  public Builder(AnimationModel model) {
    this.model = model;
  }

  @Override
  public AnimationModel build() {
    return this.model;
  }

  @Override
  public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) 
      throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and Height must be positive.");
    }
    model.setCanvas(x, y, width, height);
    return this;
  }

  @Override
  public AnimationBuilder<AnimationModel> declareShape(String name, String type) 
      throws IllegalArgumentException {
    if (type.equalsIgnoreCase(ShapeType.ELLIPSE.toString())
        || type.equalsIgnoreCase(ShapeType.OVAL.toString())) {
      Shape s = new Oval(name);
      model.addShape(s);
    } else if (type.equalsIgnoreCase(ShapeType.RECTANGLE.toString())) {
      Shape s = new Rectangle(name);
      model.addShape(s);
    } else {
      throw new IllegalArgumentException("Cannot add to model.");
    }
    return this;
  }

  @Override
  public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, 
      int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, 
      int r2, int g2, int b2) {
    // set up the initial status of the shape.
    Shape s = model.getShape(name);
    if (s.getAppearTime() == -1) {
      s.setAppearTime(t1);
    }
    if (s.getLocation() == null) {
      s.setLocation(x1, y1);
    }
    if (s.getWidth() == 0) {
      s.setWidth(w1);
    }
    if (s.getHeight() == 0) {
      s.setHeight(h1);
    }
    if (s.getColor() == null) {
      s.setColor(r1, g1, b1);
    }
    // if the motion ends after the end time of model, we extend the model end time.
    if (t2 > model.getEndTick()) {
      model.setEndTick(t2);
    }
    boolean noChange = true;
    // move shape event
    if (x1 != x2 || y1 != y2) {
      noChange = false;
      s.setDisappearTime(t2);
      Event e = new MoveShape(s, x1, y1, x2, y2);
      model.addEvent(s, e, t1, t2);
    }
    // scale shape event
    if (w1 != w2 || h1 != h2) {
      noChange = false;
      s.setDisappearTime(t2);
      Event e = new ScaleShape(s, w1, h1, w2, h2);
      model.addEvent(s, e, t1, t2);
      
    }
    // recolor shape event
    if (r1 != r2 || g1 != g2 || b1 != b2) {
      noChange = false;
      s.setDisappearTime(t2);
      Event e = new RecolorShape(s, r1, g1, b1, r2, g2, b2);
      model.addEvent(s, e, t1, t2);
    }
    if (noChange) {
      s.setDisappearTime(t2);
    }
    
    return this;
  }

}
