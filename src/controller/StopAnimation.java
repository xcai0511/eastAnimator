package controller;

import java.io.IOException;

import model.AnimationModel;
import view.View;

/**
 * This class represents stop animation command.
 * @author whitneycai
 *
 */
public class StopAnimation implements AnimationCommand {

  @Override
  public void start(AnimationModel model, View view) throws IOException {
    view.getTimer().stop();
  }

}
