package controller;

import java.io.IOException;

import model.AnimationModel;
import view.View;

/**
 * This start animation class starts the animation. It can also be used as "resume".
 * @author whitneycai
 *
 */
public class StartAnimation implements AnimationCommand {

  @Override
  public void start(AnimationModel model, View view) throws IOException {
    view.play();

  }

}
