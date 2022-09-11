package controller;

import java.io.IOException;

import model.AnimationModel;
import view.View;

/**
 * This restart animation class restarts the animation.
 * It rewinds the animation to beginning and start the animation again.
 * @author whitneycai
 *
 */
public class RestartAnimation implements AnimationCommand {

  @Override
  public void start(AnimationModel model, View view) throws IOException {
    view.setTick(0);
    view.getTimer().restart();

  }

}
