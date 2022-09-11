package controller;

import java.io.IOException;

import model.AnimationModel;
import view.View;

/**
 * This LoopAnimation class enable/disable looping for animation.
 * @author whitneycai
 *
 */
public class LoopAnimation implements AnimationCommand {

  @Override
  public void start(AnimationModel model, View view) throws IOException {
    if (view.getLoopCheckBox().isSelected()) {
      view.enableLoop();
    }
    if (!view.getLoopCheckBox().isSelected()) {
      view.disableLoop();
    }

  }

}
