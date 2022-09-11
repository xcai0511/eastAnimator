package view;

import java.io.IOException;

import model.AnimationModel;

/**
 * This class represents a view factory.
 * It takes data from user and sent to the correct view.
 * @author whitneycai
 *
 */
public class ViewFactory {
  
  private final String viewType;
  private final AnimationModel model;
  private final String outputName;
  private final int speed;
  
  /**
   * Construct a ViewFactory object.
   * @param viewType type of view.
   * @param model model of view.
   * @param outputName name of output file.
   * @param speed speed of animation.
   */
  public ViewFactory(String viewType, AnimationModel model, String outputName, int speed) {
    this.viewType = viewType;
    this.model = model;
    this.outputName = outputName;
    this.speed = speed;
  }
  
  /**
   * Return the correct view.
   * @return the correct view.
   * @throws IOException is view type is not supported.
   */
  public View create() throws IOException {
    if (viewType.equalsIgnoreCase("svg")) {
      return new SVGView(model, this.outputName, speed);
    } else if (viewType.equalsIgnoreCase("text")) {
      return new TextView(model, this.outputName);
    } else if (viewType.equalsIgnoreCase("visual")) {
      return new VisualView(model, speed);
    } else if (viewType.equalsIgnoreCase("playback")) {
      return new PlaybackView(model, speed);
    } else {
      throw new IllegalArgumentException("View type does not exist.");
    }
  }

}
