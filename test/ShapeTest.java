

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.Oval;
import model.Rectangle;
import model.Shape;
import model.ShapeType;

/**
 * JUnit test for shapes.
 * @author whitneycai
 *
 */
public class ShapeTest {
  
  private Shape r1;
  private Shape r2;
  private Shape o1;
  private Shape o2;
  
  /**
   * Set up for tests.
   */
  @Before
  public void setUp() {
    r1 = new Rectangle("r1", 50, 100, 1, 0, 0, 200, 200);
    r2 = new Rectangle("r2");
    o1 = new Oval("o1", 120, 60, 0, 0, 1, 500, 100);
    o2 = new Oval("o2");
  }
  

  /**
   * JUnit test for first constructor and toString method.
   */
  @Test
  public void testConstructor() {
    Shape r1 = new Rectangle("r1", 50, 100, 1, 0, 0, 200, 200);
    assertEquals("Name: r1\n"
                  + "Type: rectangle\n"
                  + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, "
                  + "Color: (1,0,0)\n"
                  + "Appears at t=-1\n"
                  + "Disappears at t=-1\n", r1.toString());
    Shape o1 = new Oval("o1", 120, 60, 0, 0, 1, 500, 100);
    assertEquals("Name: o1\n"
                  + "Type: ellipse\n"
                  + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                  + "Color: (0,0,1)\n"
                  + "Appears at t=-1\n"
                  + "Disappears at t=-1\n", o1.toString());
  }
  
  /**
   * Test constructor with invalid inputs.
   */
  @Test
  public void testInvalidConstructor() {
    // invalid width or height
    try {
      new Rectangle("r", -1, 100, 1, 0, 0, 200, 200);
    } catch (Exception e) {
      assertEquals("Width and height should be positive.", e.getMessage());
    }
    try {
      new Rectangle("r", 100, 0, 1, 0, 0, 200, 200);
    } catch (Exception e) {
      assertEquals("Width and height should be positive.", e.getMessage());
    }
    try {
      new Oval("r", 0, 100, 1, 0, 0, 200, 200);
    } catch (Exception e) {
      assertEquals("Width and height should be positive.", e.getMessage());
    }
    try {
      new Oval("r", 100, -1, 1, 0, 0, 200, 200);
    } catch (Exception e) {
      assertEquals("Width and height should be positive.", e.getMessage());
    }
    // invalid name
    try {
      new Rectangle("", 50, 100, 1, 0, 0, 200, 200);
    } catch (Exception e) {
      assertEquals("Name cannot be null.", e.getMessage());
    }
    try {
      new Rectangle(null, 50, 100, 1, 0, 0, 200, 200);
    } catch (Exception e) {
      assertEquals("Name cannot be null.", e.getMessage());
    }
  }
  
  /**
   * JUnit test for second constructor.
   */
  @Test
  public void testSecondConstructor() {
    try {
      new Rectangle("r");
      new Oval("o");
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
  
  @Test
  public void testSecondInvalidConstructor() {
    try {
      new Rectangle(null);
    } catch (Exception e) {
      assertEquals("Name cannot be null.", e.getMessage());
    }
    try {
      new Rectangle("");
    } catch (Exception e) {
      assertEquals("Name cannot be null.", e.getMessage());
    }
  }
  
  /**
   * Test getName method.
   */
  @Test
  public void testGetName() {
    assertEquals("r1", r1.getName());
    assertEquals("r2", r2.getName());
    assertEquals("o1", o1.getName());
    assertEquals("o2", o2.getName());
  }
  
  /**
   * Test setName method.
   */
  @Test
  public void testSetName() {
    assertEquals("r1", r1.getName());
    r1.setName("rec1");
    assertEquals("rec1", r1.getName());
    
    assertEquals("r2", r2.getName());
    r2.setName("rec2");
    assertEquals("rec2", r2.getName());
    
    assertEquals("o1", o1.getName());
    o1.setName("oval1");
    assertEquals("oval1", o1.getName());
    
    assertEquals("o2", o2.getName());
    o2.setName("oval2");
    assertEquals("oval2", o2.getName());
  }
  
  /**
   * Test setName method with invalid input.
   */
  @Test
  public void testInvalidSetName() {
    assertEquals("r1", r1.getName());
    try {
      r1.setName(null);
    } catch (Exception e) {
      assertEquals("Name cannot be null.", e.getMessage());
    }
    assertEquals("o1", o1.getName());
    try {
      r1.setName("");
    } catch (Exception e) {
      assertEquals("Name cannot be null.", e.getMessage());
    }
    
  }
  
  /**
   * Test getType method.
   */
  @Test
  public void testGetType() {
    assertEquals(ShapeType.RECTANGLE, r1.getType());
    assertEquals(ShapeType.RECTANGLE, r2.getType());
    assertEquals(ShapeType.OVAL, o1.getType());
    assertEquals(ShapeType.OVAL, o2.getType());
  }
  
  /**
   * Test getColor method.
   */
  @Test
  public void testGetColor() {
    assertEquals("(1,0,0)", r1.getColor().toString());
    assertEquals("red", r1.getColor().toColor());
    assertEquals("(0,0,1)", o1.getColor().toString());
    assertEquals("blue", o1.getColor().toColor());
  }
  
  /**
   * Test setColor method.
   */
  @Test
  public void testSetColor() {
    assertEquals("(1,0,0)", r1.getColor().toString());
    r1.setColor(1, 1, 1);
    assertEquals("(1,1,1)", r1.getColor().toString());
    
    assertEquals(null, r2.getColor());
    r2.setColor(0, 1, 0);
    assertEquals("(0,1,0)", r2.getColor().toString());
    
    assertEquals("(0,0,1)", o1.getColor().toString());
    o1.setColor(2, 2, 2);
    assertEquals("(2,2,2)", o1.getColor().toString());
    
    assertEquals(null, o2.getColor());
    o2.setColor(0, 0, 1);
    assertEquals("(0,0,1)", o2.getColor().toString());
  }
  
  /**
   * Test setColor with invalid input.
   */
  @Test
  public void testInvalidSetColor() {
    assertEquals("(1,0,0)", r1.getColor().toString());
    try {
      r1.setColor(-1, 0, 0);
    } catch (Exception e) {
      assertEquals("RGB color values must be a number between 0 and 255.", e.getMessage());
    }
    try {
      r1.setColor(1, -1, 0);
    } catch (Exception e) {
      assertEquals("RGB color values must be a number between 0 and 255.", e.getMessage());
    }
    try {
      r1.setColor(1, 0, -1);
    } catch (Exception e) {
      assertEquals("RGB color values must be a number between 0 and 255.", e.getMessage());
    }
  }
  
  /**
   * Test getLocation method.
   */
  @Test
  public void testGetLocation() {
    assertEquals("(200.0,200.0)", r1.getLocation().toString());
    assertEquals("(500.0,100.0)", o1.getLocation().toString());
  }
  
  /**
   * Test setLocation method.
   */
  @Test
  public void testSetLocation() {
    assertEquals("(200.0,200.0)", r1.getLocation().toString());
    r1.setLocation(100, 100);
    assertEquals("(100.0,100.0)", r1.getLocation().toString());
       
    assertEquals("(500.0,100.0)", o1.getLocation().toString());
    o1.setLocation(200, 200);
    assertEquals("(200.0,200.0)", o1.getLocation().toString());
  }
  
  /**
   * Test getWidth method.
   */
  @Test
  public void testGetWidth() {
    assertEquals(50, r1.getWidth(), 0.01);
    assertEquals(120, o1.getWidth(), 0.01);
  }
  
  /**
   * Test setWidth method.
   */
  @Test
  public void testSetWidth() {
    assertEquals(50, r1.getWidth(), 0.01);
    r1.setWidth(60);
    assertEquals(60, r1.getWidth(), 0.01);
    
    r2.setWidth(50);
    assertEquals(50, r2.getWidth(), 0.01);
    
    assertEquals(120, o1.getWidth(), 0.01);
    o1.setWidth(100);
    assertEquals(100, o1.getWidth(), 0.01);
    
    o2.setWidth(120);
    assertEquals(120, o2.getWidth(), 0.01);
  }
  
  /**
   * Test setWidth method with invalid input.
   */
  @Test
  public void testInvalidSetWidth() {
    try {
      r1.setWidth(0);
    } catch (Exception e) {
      assertEquals("Width must be positive.", e.getMessage());
    }
    try {
      o1.setWidth(-10);
    } catch (Exception e) {
      assertEquals("Width must be positive.", e.getMessage());
    }
  }
  
  /**
   * Test setHeight method.
   */
  @Test
  public void testSetHeight() {
    assertEquals(100, r1.getHeight(), 0.01);
    r1.setHeight(20);
    assertEquals(20, r1.getHeight(), 0.01);
    
    r2.setHeight(100);
    assertEquals(100, r2.getHeight(), 0.01);
    
    assertEquals(60, o1.getHeight(), 0.01);
    o1.setHeight(20);
    assertEquals(20, o1.getHeight(), 0.01);
    
    o2.setHeight(60);
    assertEquals(60, o2.getHeight(), 0.01);
  }
  
  /**
   * Test setHeight method with invalid input.
   */
  @Test
  public void testInvalidSetHeight() {
    try {
      r1.setHeight(0);
    } catch (Exception e) {
      assertEquals("Height must be positive.", e.getMessage());
    }
    try {
      o1.setHeight(-10);
    } catch (Exception e) {
      assertEquals("Height must be positive.", e.getMessage());
    }
  }
  
  /**
   * Test setAppearTime and getAppearTime method.
   */
  @Test
  public void testAppearTime() {
    // default is -1
    assertEquals(-1, r1.getAppearTime());
    r1.setAppearTime(0);
    assertEquals(0, r1.getAppearTime());
    
    assertEquals(-1, r2.getAppearTime());
    r2.setAppearTime(2);
    assertEquals(2, r2.getAppearTime());
    
    assertEquals(-1, o1.getAppearTime());
    o1.setAppearTime(10);
    assertEquals(10, o1.getAppearTime());
    
    assertEquals(-1, o2.getAppearTime());
    o2.setAppearTime(20);
    assertEquals(20, o2.getAppearTime());
  }
  
  /**
   * test setAppearTime method with invalid input.
   */
  @Test
  public void testInvalidSetAppear() {
    try {
      r1.setAppearTime(-1);
    } catch (Exception e) {
      assertEquals("Appear time cannot be negative.", e.getMessage());
    }
    
    try {
      o1.setAppearTime(-10);
    } catch (Exception e) {
      assertEquals("Appear time cannot be negative.", e.getMessage());
    }
  }
  
  /**
   * Test setDisappearTime and getDisappearTime method.
   */
  @Test
  public void testDisappearTime() {
    assertEquals(-1, r1.getDisappearTime());
    r1.setDisappearTime(0);
    assertEquals(0, r1.getDisappearTime());
    
    assertEquals(-1, r2.getDisappearTime());
    r2.setDisappearTime(5);
    assertEquals(5, r2.getDisappearTime());
    
    assertEquals(-1, o1.getDisappearTime());
    o1.setDisappearTime(12);
    assertEquals(12, o1.getDisappearTime());
    
    assertEquals(-1, o2.getDisappearTime());
    o2.setDisappearTime(100);
    assertEquals(100, o2.getDisappearTime());
  }
  
  /**
   * Test setDisapperTime with invalid input.
   */
  @Test
  public void testInvalidSetDisappear() {
    try {
      r1.setDisappearTime(-1);
    } catch (Exception e) {
      assertEquals("Disappear time cannot be negative.", e.getMessage());
    }
    
    try {
      r2.setDisappearTime(-10);
    } catch (Exception e) {
      assertEquals("Disappear time cannot be negative.", e.getMessage());
    }
  }
  
  /**
   * test timeToString method.
   */
  @Test
  public void testTimeToString() {
    r1.setAppearTime(0);
    r1.setDisappearTime(10);
    assertEquals("r1 appears at t=0 and disappears at t=10", r1.timeToString());
    
    o1.setAppearTime(2);
    o1.setDisappearTime(18);
    assertEquals("o1 appears at t=2 and disappears at t=18", o1.timeToString());
  }
  
  /**
   * Test createString method.
   */
  @Test
  public void testCreateString() {
    assertEquals("Create red rectangle r1 with corner at (200.0,200.0), width 50.0 and " 
            + "height 100.0", r1.createString());
    assertEquals("Create blue ellipse o1 with center at (500.0,100.0), radius 60.0 and 30.0", 
            o1.createString());
  }

  
  

}
