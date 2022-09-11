

import static org.junit.Assert.assertEquals;

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
 * JUnit test for SVG view.
 * @author whitneycai
 *
 */
public class SVGViewTest {
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
   * Test SVG view.
   * @throws IOException if doesn't work properly.
   */
  @Test
  public void testSVG() throws IOException {
    String inputName = "smalldemo.txt";
    JFrame frame = AnimatorController.newFrame();
    this.file = AnimatorController.checkInputFile(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("svg", model, "System.out", 1);
    View svgView = newView.create();
    assertEquals("<svg width=\"360\" height=\"360\" version=\"1.1\" " 
                + "xmlns=\"http://www.w3.org/2000/svg\">\n"
                + "\n"
                + "<rect id=\"R\" x=\"0\" y=\"130\" width=\"50.0\" height=\"100.0\" "
                + "fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
                + "\n"
                + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000.0ms\" "
                + "attributeName=\"x\" from=\"0\" to=\"100\" fill=\"freeze\" />\n" 
                + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000.0ms\" " 
                + "attributeName=\"y\" from=\"130\" to=\"230\" fill=\"freeze\" />\n" 
                + "<animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900.0ms\" " 
                + "attributeName=\"height\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n" 
                + "<animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900.0ms\" " 
                + "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n" 
                + "<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000.0ms\" " 
                + "attributeName=\"x\" from=\"100\" to=\"0\" fill=\"freeze\" />\n" 
                + "<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000.0ms\" " 
                + "attributeName=\"y\" from=\"230\" to=\"130\" fill=\"freeze\" />\n" 
                + "</rect>\n" 
                + "\n" 
                + "<ellipse id=\"C\" cx=\"240\" cy=\"0\" rx=\"60.0\" ry=\"30.0\" " 
                + "fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n" 
                + "\n" 
                + "<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000.0ms\" " 
                + "attributeName=\"cx\" from=\"240\" to=\"240\" fill=\"freeze\" />\n" 
                + "<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000.0ms\" " 
                + "attributeName=\"cy\" from=\"0\" to=\"180\" fill=\"freeze\" />\n" 
                + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000.0ms\" " 
                + "attributeName=\"cx\" from=\"240\" to=\"240\" fill=\"freeze\" />\n" 
                + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000.0ms\" " 
                + "attributeName=\"cy\" from=\"180\" to=\"300\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000.0ms\" " 
                + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" " 
                + "fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"1000.0ms\" " 
                + "attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" " 
                + "fill=\"freeze\" />\n" 
                + "</ellipse>\n" 
                + "\n" 
                + "</svg>",
        svgView.getViewState());
  }
  
  /**
   * Test svg view with empty text.
   * @throws IOException if view doesn't work properly.
   */
  @Test
  public void testEmpty() throws IOException {
    String inputName = "empty.txt";
    JFrame frame = AnimatorController.newFrame();
    this.file = AnimatorController.checkInputFile(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("svg", model, "test.svg", 1);
    View textView = newView.create();
    assertEquals("<svg width=\"0\" height=\"0\" version=\"1.1\" xmlns=\""
            + "http://www.w3.org/2000/svg\">\n\n</svg>", textView.getViewState());
  }
 

}
