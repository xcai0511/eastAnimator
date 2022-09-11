package controller;

import java.io.IOException;

import model.AnimationModel;
import view.View;

/**
 * This interface contains methods that all animation should support.
 * @author whitneycai
 *
 */
public interface AnimationCommand {
  
  /**
   * Start animation.
   * @param model animation model of animation.
   * @param view view of animation.
   * @throws IOException if animation cannot function properly.
   */
  void start(AnimationModel model, View view) throws IOException;

}
