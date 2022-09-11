package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import model.AnimationModel;

/**
 * This class represents a visual view.
 * This view allows users to draw and play the animation inside a window.
 * @author whitneycai
 *
 */
public class VisualView extends JFrame implements View, ActionListener {
  
  protected AnimationModel model;
  protected int speed;
  protected AnimationPanel animationPanel;
  protected int tick;
  protected Timer timer;
  
  /**
   * Construct a VisualView with given model and speed.
   * @param model animation model for the visual view.
   * @param speed speed of animation.
   */
  public VisualView(AnimationModel model, int speed) {
    super();
    this.model = model;
    this.speed = speed;
    this.animationPanel = new AnimationPanel(model, 0);
    this.timer = new Timer(1000 / speed, this);
    timer.setInitialDelay(100);
    timer.start();
  }

  @Override
  public void createView() {
    newPanel(animationPanel);
    scrollPanel(animationPanel);
    this.pack();
    this.setVisible(true);
    drawShapes(0);

  }
  
  /**
   * create panel.
   * @param panel animation panel.
   */
  protected void newPanel(AnimationPanel animationPanel) {
    animationPanel.setPreferredSize(new Dimension(
        model.getCanvasWidth(), model.getCanvasWidth()));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(animationPanel);
  }
  
  /**
   * Create a scroll pane.
   * @param animationPanel animation panel.
   */
  protected void scrollPanel(AnimationPanel panel) {
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.add(scrollPane);
  }
  
  @Override
  public void drawShapes(int tick) {
    int endTick = model.getEndTick();
    for (int t = tick; t <= endTick; t++) {
      // current time in milliseconds
      long startTime = System.currentTimeMillis();
      animationPanel.setTick(t);
      animationPanel.repaint();
      double endTime = System.currentTimeMillis();
      double duration = endTime - startTime;
      int delay = 1000 / speed;
      double remainTime = delay - duration;
      try {
        Thread.sleep((long)remainTime);
      } catch (Exception e) {
        // do nothing
      }
      
    }
  }
  
  @Override
  public String getViewState() {
    throw new UnsupportedOperationException("Visual view does not support this operation.");
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
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
    
  }

  @Override
  public void setStopButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
    
  }

  @Override
  public void setRestartButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
    
  }

  @Override
  public void setFasterButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
    
  }

  @Override
  public void setSlowerButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
    
  }

  @Override
  public void setLoopButtonListener(ItemListener itemEvent) {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
    
  }

  @Override
  public Timer getTimer() {
    return timer;
  }

  @Override
  public void setTick(int tick) {
    this.tick = tick;
    
  }

  @Override
  public JCheckBox getLoopCheckBox() {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
  }

  @Override
  public void enableLoop() {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
    
  }

  @Override
  public void disableLoop() {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
    
  }

  @Override
  public void setSpeed(int speed) {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
    
  }

  @Override
  public int getSpeed() {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
  }

  @Override
  public void play() {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
    
  }

  @Override
  public void printView() {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
    
  }

  @Override
  public void clearModel() {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
    
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // do nothing
    
  }

}
