import controller.AnimatorController;

/**
 * This class contains the main function of EasyAnimator.
 * @author whitneycai
 *
 */
public class EasyAnimator {
  
  /**
   * The main class that runs the animation.
   * @param args command line in file.
   */
  public static void main(String[] args) {
    AnimatorController controller = new AnimatorController(args);
    controller.start();
  }

}
