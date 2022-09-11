

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import controller.AnimatorController;
import model.AnimationModel;
import model.AnimationModelImpl;
import util.AnimationBuilder;
import util.AnimationReader;
import util.Builder;
import view.View;
import view.ViewFactory;

/**
 * JUnit test for TextView class.
 * @author whitneycai
 *
 */
public class TextViewTest {
  private AnimationModel model;
  private Readable file;
  private AnimationBuilder<AnimationModel> build;
  
  /**
   * Set up for test.
   */
  @Before
  public void setUp() {
    this.model = new AnimationModelImpl();
  }

  /**
   * Test if the text view works properly.
   * @throws IOException if text view can't work properly.
   */
  @Test
  public void testText() throws IOException {
    String inputName = "smalldemo.txt";
    JFrame frame = AnimatorController.newFrame();
    this.file = AnimatorController.checkInputFile(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("text", model, "System.out", 1);
    View textView = newView.create();
    assertEquals("Create red rectangle R with corner at (200.0,200.0), width 50.0 and "
        + "height 100.0\n"
        + "Create blue ellipse C with center at (440.0,70.0), " +
        "radius 60.0 and 30.0\n"
        + "\n"
        + "R appears at t=1 and disappears at t=100\n"
        + "C appears at t=6 and disappears at t=100\n"
        + "\n"
        + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
        + "Shape C moves from (440.0,70.0) to (440.0,250.0) from t=20 to t=50\n"
        + "Shape C moves from (440.0,250.0) to (440.0,370.0) from t=50 to t=70\n"
        + "Shape C changes color from (0,0,255) to (0,170,85) from t=50 to t=70\n"
        + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: " 
        + "100.0 from t=51 to t=70\n"
        + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n"
        + "Shape C changes color from (0,170,85) to (0,255,0) from t=70 to t=80\n", 
        textView.getViewState());
   
  }
  
  /**
   * Test cannot find file.
   */
  @Test
  public void testNoFile() {
    String inputName = "";
    JFrame frame = AnimatorController.newFrame();
    this.file = AnimatorController.checkInputFile(inputName, frame);
    assertNull(this.file);
  }
  
  /**
   * Test empty file.
   * @throws IOException if text view can't work properly.
   */
  @Test
  public void empty() throws IOException {
    String inputName = "empty.txt";
    JFrame frame = AnimatorController.newFrame();
    this.file = AnimatorController.checkInputFile(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("text", model, "System.out", 1);
    View textView = newView.create();
    assertEquals("\n\n", textView.getViewState());
  }

}
