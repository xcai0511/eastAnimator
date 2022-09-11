package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.AnimationModel;
import view.View;

/**
 * This class represents a slower animation command.
 * It decreases the animation speed.
 * @author whitneycai
 *
 */
public class SlowerAnimation implements AnimationCommand, ActionListener {
  
  private int speed;
  private View view;
  
  /**
   * Construct a SlowerAnimation object.
   * @param view view of animation.
   */
  public SlowerAnimation(View view) {
    this.speed = view.getSpeed();
    this.view = view;
    
    if (speed <= 1) {
      this.speed = 1;
    }
  }

  @Override
  public void start(AnimationModel model, View view) throws IOException {
    view.setSpeed(speed - 1);
    if (view.getSpeed() <= 1) {
      view.setSpeed(1);
    }
    view.getTimer().setDelay(1000 / speed);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    view.setSpeed(speed);
    
  }

}
