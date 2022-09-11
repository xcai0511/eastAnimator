

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.AnimationModel;
import model.AnimationModelImpl;
import util.AnimationBuilder;
import util.Builder;

/**
 * JUnit test for Builder class (implementation of AnimationBuilder).
 * @author whitneycai
 *
 */
public class BuilderTest {
  private AnimationModel model;
  private AnimationBuilder<AnimationModel> builder;
  
  /**
   * Set up for test.
   */
  @Before
  public void setUp() {
    this.model = new AnimationModelImpl();
  }

  /**
   * Test constructor.
   */
  @Test
  public void testConstructor() {
    this.builder = new Builder(model);
    assertEquals("Shapes:\n", this.model.toString());
  }
  
  /**
   * Test for setBounds method.
   */
  @Test
  public void testSetBounds() {
    this.builder = new Builder(model);
    this.builder.setBounds(1, 2, 10, 20);
    AnimationModel model = builder.build();
    assertEquals(1, model.getCanvasX());
    assertEquals(2, model.getCanvasY());
    assertEquals(10, model.getCanvasWidth());
    assertEquals(20, model.getCanvasHeight());
    
    this.builder.setBounds(100, 200, 100, 200);
    model = builder.build();
    assertEquals(100, model.getCanvasX());
    assertEquals(200, model.getCanvasY());
    assertEquals(100, model.getCanvasWidth());
    assertEquals(200, model.getCanvasHeight());
  }
  
  /**
   * Test setBounds with invalid input.
   */
  @Test
  public void testInvalidSetBounds() {
    this.builder = new Builder(model);
    try {
      this.builder.setBounds(0, 0, -1, 1);
    } catch (Exception e) {
      assertEquals("Width and Height must be positive.", e.getMessage());
    }
    try {
      this.builder.setBounds(0, 0, 10, -1);
    } catch (Exception e) {
      assertEquals("Width and Height must be positive.", e.getMessage());
    }
    try {
      this.builder.setBounds(0, 0, 0, 1);
    } catch (Exception e) {
      assertEquals("Width and Height must be positive.", e.getMessage());
    }
    try {
      this.builder.setBounds(0, 0, 1, 0);
    } catch (Exception e) {
      assertEquals("Width and Height must be positive.", e.getMessage());
    }
  }
  
  /**
   * Test for declareShape and addMotion method.
   */
  @Test
  public void testShapeAndMotion() {
    this.builder = new Builder(model);
    builder.declareShape("R", "rectangle");
    builder.declareShape("C", "ellipse");
    builder.addMotion("R", 0, 0, 0, 10, 10, 0, 0, 0, 10, 10, 10, 10, 10, 0, 0, 0);
    builder.addMotion("C", 1, 0, 0, 20, 20, 1, 0, 0, 11, 0, 0, 20, 20, 0, 0, 1);
    AnimationModel model = builder.build();
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (0.0,0.0), Width: 10.0, Height: 10.0, Color: (0,0,0)\n"
                    + "Appears at t=0\n"
                    + "Disappears at t=10\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: ellipse\n"
                    + "Center: (0.0,0.0), X radius: 10.0, Y radius: 10.0, Color: (1,0,0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=11\n"
                    + "\n"
                    + "Shape R moves from (0.0,0.0) to (10.0,10.0) from t=0 to t=10\n"
                    + "Shape C changes color from (1,0,0) to (0,0,1) from t=1 to t=11\n", 
                    model.toString());
  }
  
  /**
   * Test declareShape method with invalid input.
   */
  @Test
  public void testInvalidShape() {
    this.builder = new Builder(model);
    try {
      builder.declareShape("R", "circle");
    } catch (Exception e) {
      assertEquals("Cannot add to model.", e.getMessage());
    }
  }

}
