

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.event.ActionEvent;

import org.junit.Test;

import controller.AnimatorController;
import view.View;

/**
 * JUnit test for AnimatorController.
 * @author whitneycai
 *
 */
public class AnimatorControllerTest {
  private AnimatorController controller;
  
 
  
  /**
   * Test constructors with smalldemo.txt
   */
  @Test
  public void testConstructor() {
    controller = new AnimatorController(new String[] {"-in smalldemo.txt -view playback -out" 
            + "test.txt -speed 1"});
    controller.start();
    String smalldemo = "Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255,0,0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Name: C\n"
            + "Type: ellipse\n"
            + "Center: (440.0,70.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,255)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
            + "Shape C moves from (440.0,70.0) to (440.0,250.0) from t=20 to t=50\n"
            + "Shape C moves from (440.0,250.0) to (440.0,370.0) from t=50 to t=70\n"
            + "Shape C changes color from (0,0,255) to (0,170,85) from t=50 to t=70\n"
            + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 " 
            + "from t=51 to t=70\n"
            + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n"
            + "Shape C changes color from (0,170,85) to (0,255,0) from t=70 to t=80\n";
    assertEquals(smalldemo, controller.getModel().toString());
  }
  
  /**
   * Test when cannot locate the file name.
   */
  @Test(expected = NullPointerException.class)
  public void testNoFile() {
    controller = new AnimatorController(new String[]{"-in none -view text -out test.txt "
            + "-speed 1"});
    controller.start();
  }
  
  /**
   * Test with invalid view type.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidView() {
    controller = new AnimatorController(new String[]{"-in smalldemo.txt -view someView"});
    controller.start();
  }
  
  @Test
  public void actionPerformedTest() {
    controller = new AnimatorController(new String[] {"-in smalldemo.txt -view playback -out" 
        + "test.txt -speed 1"});
    controller.start();
    View view = controller.getView();
    // start
    ActionEvent start = new ActionEvent(view, 1, "START");
    controller.actionPerformed(start);
    assertTrue(view.getTimer().isRunning());
    // stop
    ActionEvent stop = new ActionEvent(view, 1, "STOP");
    controller.actionPerformed(stop);
    assertFalse(view.getTimer().isRunning());
    // speed up and down
    assertEquals(1, view.getSpeed());
    ActionEvent fast = new ActionEvent(view, 1, "FASTER");
    controller.actionPerformed(fast);
    assertEquals(2, view.getSpeed());
    ActionEvent slow = new ActionEvent(view, 1, "SLOWER");
    controller.actionPerformed(slow);
    assertEquals(1, view.getSpeed());
    // try to make speed 0, but should stay at 1
    controller.actionPerformed(slow);
    assertEquals(1, view.getSpeed());
    // speed changes while running
    controller.actionPerformed(start);
    assertEquals(1, view.getSpeed());
    controller.actionPerformed(fast);
    assertEquals(2, view.getSpeed());
    controller.actionPerformed(slow);
    assertEquals(1, view.getSpeed());
    controller.actionPerformed(stop);
    // restart
    assertFalse(view.getTimer().isRunning());
    ActionEvent restart = new ActionEvent(view, 1, "RESTART");
    controller.actionPerformed(restart);
    assertTrue(view.getTimer().isRunning());
  }
  
  
  
}
