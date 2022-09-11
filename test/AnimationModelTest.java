
import model.AnimationModelImpl;
import model.RecolorShape;
import model.AnimationModel;
import model.Event;
import model.Shape;
import model.MoveShape;
import model.Oval;
import model.Rectangle;
import model.ScaleShape;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for AnimationModel interface.
 * @author whitneycai
 *
 */
public class AnimationModelTest {
  
  private AnimationModel testAnimation;
  
  private Shape r;
  private Shape c;
  
  private Event move1;
  private Event move2;
  private Event move3;
  
  private Event scale1;
  private Event scale2;
  
  private Event recolor1;
  
  /**
   * Set up for test.
   */
  @Before
  public void setUp() {
    testAnimation = new AnimationModelImpl();
    
    r = new Rectangle("R", 50, 100, 1, 0, 0, 200, 200);
    c = new Oval("C", 120, 60, 0, 0, 1, 500, 100);
  }
  
  /**
   * Test addShape and toString method.
   */
  @Test
  public void testAddShape() {
    testAnimation.addShape(r, 1, 100);
    assertEquals("Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n", testAnimation.toString());
    
    testAnimation.addShape(c, 6, 100);
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Name: C\n"
            + "Type: ellipse\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n"
            + "\n", testAnimation.toString());
  }
  
  /**
   * Test addShape with invalid input.
   */
  @Test
  public void testInvalidAddShape() {
    try {
      testAnimation.addShape(c, 1, 100);
      testAnimation.addShape(c, 2, 100);
    } catch (Exception e) {
      assertEquals("A shape with this name already exist.", e.getMessage());
    }
    try {
      testAnimation.addShape(r, 100, 100);
    } catch (Exception e) {
      assertEquals("Invalid disappear time", e.getMessage());
    }
    try {
      testAnimation.addShape(r, -1, 1);
    } catch (Exception e) {
      assertEquals("Invalid appear time", e.getMessage());
    }
    try {
      testAnimation.addShape(r, 100, 10);
    } catch (Exception e) {
      assertEquals("Invalid disappear time", e.getMessage());
    }
    try {
      testAnimation.addShape(null, 1, 100);
    } catch (Exception e) {
      assertEquals("Shape cannot be null", e.getMessage());
    }
  }
  
  /**
   * Test addEvent method for move.
   */
  @Test
  public void testAddMove() {
    testAnimation.addShape(r, 1, 100);
    testAnimation.addShape(c, 6, 100);

    // Instantiate the moves
    move1 = new MoveShape(r, 200.0, 200.0, 300.0, 300.0);
    move2 = new MoveShape(c, 500.0, 100.0, 500.0, 400.0);
    move3 = new MoveShape(r, 300.0, 300.0, 200.0, 200.0);

    testAnimation.addEvent(r, move1, 10, 50);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: ellipse\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n", 
         testAnimation.toString());
    
    testAnimation.addEvent(c, move2, 20, 70);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: ellipse\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n", 
          testAnimation.toString());
    
    testAnimation.addEvent(r, move3, 70, 100);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: ellipse\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
                    + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n",
          testAnimation.toString());
  }
  
  /**
   * Test addEvent method for scale.
   */
  @Test
  public void testAddScale() {
    testAnimation.addShape(r, 1, 100);
    // Instantiate the size change
    scale1 = new ScaleShape(r, 50.0, 100.0, 25.0, 100.0);
    scale2 = new ScaleShape(r, 25.0, 100.0, 25.0, 13.0);
    testAnimation.addEvent(r, scale1, 51, 70);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
                    + "Height: 100.0 from t=51 to t=70\n",
          testAnimation.toString());
    
    testAnimation.addEvent(r, scale2, 71, 73);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
                    + "Height: 100.0 from t=51 to t=70\n"
                    + "Shape R scales from Width: 25.0, Height: 100.0 to Width: 25.0, "
                    + "Height: 13.0 from t=71 to t=73\n",
          testAnimation.toString());
  }
  
  /**
   * Test addEvent method for recolor.
   */
  @Test
  public void testAddRecolor() {
    testAnimation.addShape(c, 6, 100);
    // Instantiate the color change
    recolor1 = new RecolorShape(c, 0, 0, 1, 0, 1, 0);
    Event recolor2 = new RecolorShape(c, 0, 1, 0, 25, 1, 0);
    testAnimation.addEvent(c, recolor1, 50, 80);
    assertEquals("Shapes:\n"
                    + "Name: C\n"
                    + "Type: ellipse\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape C changes color from (0,0,1) to (0,1,0) from t=50 to t=80\n",
          testAnimation.toString());
    
    testAnimation.addEvent(c, recolor2, 81, 90);
    assertEquals("Shapes:\n"
                    + "Name: C\n"
                    + "Type: ellipse\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape C changes color from (0,0,1) to (0,1,0) from t=50 to t=80\n"
                    + "Shape C changes color from (0,1,0) to (25,1,0) from t=81 to t=90\n",
          testAnimation.toString());
  }
  
  /**
   * Test addEvent with invalid inputs.
   */
  @Test
  public void testInvalidAddEvent() {
    try {
      testAnimation.addShape(c, 1, 100);
      move1 = new MoveShape(c, 500.0, 100.0, 300.0, 300.0);
      testAnimation.addEvent(null, move1, 20, 45);
    } catch (Exception e) {
      assertEquals("Shape cannot be null.", e.getMessage());
    }
    try {
      testAnimation.addEvent(c, null, 29, 85);
    } catch (Exception e) {
      assertEquals("Event cannot be null.", e.getMessage());
    }
    try {
      testAnimation.addShape(r, 1, 100);
      scale1 = new ScaleShape(r, 50.0, 100.0, 25.0, 100.0);
      testAnimation.addEvent(r, scale1, 90, 2);
    } catch (Exception e) {
      assertEquals("Invalid end tick.", e.getMessage());
    }
    try {
      recolor1 = new RecolorShape(r, 0, 0, 1, 0, 1, 0);
      testAnimation.addEvent(r, recolor1, 20, 45);
    } catch (Exception e) {
      assertEquals("Invalid end tick.", e.getMessage());
    }
    try {
      recolor1 = new RecolorShape(r, 0, 0, 1, 0, 1, 0);
      testAnimation.addEvent(r, recolor1, 20, 1000);
    } catch (Exception e) {
      assertEquals("Invalid end tick." 
                    + " The event should end before the shape disappears.", e.getMessage());
    }
    try {
      scale1 = new ScaleShape(r, 50.0, 100.0, 25.0, 100.0);
      scale2 = new ScaleShape(r, 25.0, 100.0, 70.0, 1000.0);
      testAnimation.addEvent(r, scale1, 90, 100);
      testAnimation.addEvent(r, scale2, 89, 95);
    } catch (Exception e) {
      assertEquals("Invalid end tick.", e.getMessage());
    }
  }
  
  /**
   * test getShapeAtTick method.
   */
  @Test
  public void testGetShapeAtTick() {
    // testAnimation = new AnimationModelImpl();
    assertEquals("[]", testAnimation.getShapeAtTick(1).toString());
    testAnimation.addShape(r, 1, 100);
    testAnimation.addShape(c, 6, 100);

    move1 = new MoveShape(r, 200.0, 200.0, 300.0, 300.0);
    move2 = new MoveShape(c, 500.0, 100.0, 500.0, 400.0);
    move3 = new MoveShape(r, 300.0, 300.0, 200.0, 200.0);

    scale1 = new ScaleShape(r, 50.0, 100.0, 25.0, 100.0);

    recolor1 = new RecolorShape(c, 0, 0, 1, 0, 1, 0);

    testAnimation.addEvent(r, move1, 10, 50);
    testAnimation.addEvent(c, move2, 20, 70);
    testAnimation.addEvent(c, recolor1, 50, 80);
    testAnimation.addEvent(r, scale1, 51, 70);
    testAnimation.addEvent(r, move3, 70, 100);

    // one shape
    assertEquals("[Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + "]", testAnimation.getShapeAtTick(1).toString());

    // move
    assertEquals("[Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (202.5,202.5), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + ", Name: C\n"
            + "Type: ellipse\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n"
            + "]", testAnimation.getShapeAtTick(11).toString());

    // scale change
    assertEquals("[Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (300.0,300.0), Width: 31.578947368421055, "
            + "Height: 100.0, Color: (1,0,0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + ", Name: C\n"
            + "Type: ellipse\n"
            + "Center: (500.0,370.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n"
            + "]", testAnimation.getShapeAtTick(65).toString());

    //color change
    assertEquals("[Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (300.0,300.0), Width: 43.421052631578945, Height: 100.0, " 
            + "Color: (1,0,0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + ", Name: C\n"
            + "Type: ellipse\n"
            + "Center: (500.0,316.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n"
            + "]", testAnimation.getShapeAtTick(56).toString());
    assertEquals("[]", testAnimation.getShapeAtTick(500).toString());
  }
  
  /**
   * Test getShapeAtTick method with invalid input.
   */
  @Test
  public void testInvalidShapeAtTick() {
    try {
      testAnimation.getShapeAtTick(-1);
    } catch (Exception e) {
      assertEquals("Tick cannot be negative.", e.getMessage());
    }
  }

}
