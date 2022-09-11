package view;


import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.Timer;

import model.AnimationModel;

/**
 * This interface contains methods that all kinds of views should support.
 * @author whitneycai
 *
 */
public interface View {
  
  /**
   * Create a view.
   */
  void createView();
  
  /**
   * Return a string representing the current view state.
   * @return string representing the view state.
   */
  String getViewState();
  
  /**
   * Return model of the view.
   * @return model of the view.
   */
  AnimationModel getModel();
  
  /**
   * Set model of the view.
   * @param model model of the view.
   */
  void setModel(AnimationModel model);
  
  // ------ for PlaybackView --------
  /**
   * Set up listener for start button.
   * @param actionEvent click start button.
   */
  void setStartButtonListener(ActionListener actionEvent);
  
  /**
   * Set up listener for pause button.
   * @param actionEvent click pause button.
   */
  void setStopButtonListener(ActionListener actionEvent);
  
  /**
   * Set up listener for restart button.
   * Rewind to beginning and start the animation again.
   * @param actionEvent click restart button.
   */
  void setRestartButtonListener(ActionListener actionEvent);
  
  /**
   * Set up listener for fast (increase speed) button.
   * @param actionEvent click fast button.
   */
  void setFasterButtonListener(ActionListener actionEvent);
  
  /**
   * Set up listener for slow (decrease speed) button.
   * @param actionEvent click slow button.
   */
  void setSlowerButtonListener(ActionListener actionEvent);
  
  /**
   * Set up listener for loop check box.
   * @param itemEvent check loop box.
   */
  void setLoopButtonListener(ItemListener itemEvent);
  
  /**
   * Draw shapes at the given tick.
   * @param tick tick to start drawing.
   */
  void drawShapes(int tick);
  
  /**
   * Return timer for the view.
   * @return timer for playback view.
   */
  Timer getTimer();
  
  /**
   * Set tick.
   * @param tick tick.
   */
  void setTick(int tick);
  
  /**
   * Return look check box.
   * @return loop check box.
   */
  JCheckBox getLoopCheckBox();
  
  /**
   * Enable looping. Make the animation to automatically restart once it reaches the end.
   */
  void enableLoop();
  
  /**
   * Disable looping. Stop animation once it reaches the end.
   */
  void disableLoop();
  
  /**
   * Set speed for playback.
   * @param speed speed for playback.
   */
  void setSpeed(int speed);
  
  /**
   * Return speed of animation.
   * @return speed of animation.
   */
  int getSpeed();
  
  /**
   * Start the timer and play animation.
   */
  void play();
  
  /**
   * Print the view.
   */
  void printView();
  
  /**
   * Remove model from the view.
   */
  void clearModel();

}
