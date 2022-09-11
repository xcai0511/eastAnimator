package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import model.AnimationModel;
import model.Shape;
import model.ShapeType;

/**
 * This class represents AnimationPanel. It extends JPanel.
 * It stores and draw shapes on the panel.
 * @author whitneycai
 *
 */
public class AnimationPanel extends JPanel {
  
  private int tick;
  private AnimationModel model;
  
  /**
   * Construct an animation panel.
   * @param model current animation model.
   * @param tick current tick.
   */
  public AnimationPanel(AnimationModel model, int tick) {
    super();
    this.model = model;
    this.tick = tick;
    this.setBackground(Color.WHITE);
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    
    List<Shape> listOfShapes = model.getShapeAtTick(tick);
    
    for (Shape s : listOfShapes) {
      
      int x = (int) s.getLocation().getX();
      int y = (int) s.getLocation().getY();
      int w = (int) s.getWidth();
      int h = (int) s.getHeight();
      setGraphicColor(s, g2D);
      if (s.getType() == ShapeType.RECTANGLE) {
        // g2D.drawRect(x, y, w, h);
        g2D.fillRect(x, y, w, h);
      } else if (s.getType() == ShapeType.OVAL || s.getType() == ShapeType.ELLIPSE) {
        // g2D.drawOval(x, y, w, h);
        g2D.fillOval(x, y, w, h);
      }
    }
    
  }
  
  /**
   * Set shape's color.
   * @param s shape.
   * @param graphic graphic.
   */
  private void setGraphicColor(Shape s, Graphics2D graphic) {
    int currentR = s.getColor().getRed();
    int currentG = s.getColor().getGreen();
    int currentB = s.getColor().getBlue();
    graphic.setColor(new Color(currentR, currentG, currentB));
  }
  
  /**
   * Set animation model.
   * @param model model of animation.
   */
  public void setModel(AnimationModel model) {
    this.model = model;
  }
  
  
  /**
   * Set the current tick.
   * @param tick tick value.
   */
  public void setTick(int tick) {
    this.tick = tick;
  }
  
  /**
   * Return current tick value.
   * @return current tick value.
   */
  public int getTick() {
    return this.tick;
  }

}
