package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.AnimationModel;
import view.View;

/**
 * This faster animation class increase the speed of the animation.
 * @author whitneycai
 *
 */
public class FasterAnimation implements AnimationCommand, ActionListener {
  
  private int speed;
  private View view;
  
  /**
   * Construct the FasterAnimation object.
   * @param view view of animation.
   */
  public FasterAnimation(View view) {
    this.speed = view.getSpeed();
    this.view = view;
  }

  @Override
  public void start(AnimationModel model, View view) throws IOException {
    updateSpeed(view);
    setTimerDelay(view);

  }
  
  /**
   * Update speed.
   * @param view view of animation.
   */
  private void updateSpeed(View view) {
    view.setSpeed(speed + 1);
  }
  
  private void setTimerDelay(View view) {
    view.getTimer().setDelay(1000 / speed);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("Speed up button clicked.");
    view.setSpeed(speed);
  }

}
