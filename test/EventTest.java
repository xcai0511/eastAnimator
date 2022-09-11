
import model.RecolorShape;
import model.Event;
import model.EventType;
import model.Shape;
import model.MoveShape;
import model.Oval;
import model.Rectangle;
import model.ScaleShape;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for event interface.
 * @author whitneycai
 *
 */
public class EventTest {
  private Shape rectangle;
  private Shape oval;
  
  @Before
  public void setUp() {
    this.oval = new Oval("oval", 120, 60, 0, 0, 1, 500, 100);
    this.rectangle = new Rectangle("rectangle", 50, 100, 1, 0, 0, 200, 200);
  }
  

  /**
   * Test constructor and toString method.
   */
  @Test
  public void testConstructor() {
    // move
    Event moveRec = new MoveShape(rectangle, 200, 200, 300, 300);
    moveRec.setBeginTick(10);
    moveRec.setEndTick(50);
    assertEquals("Shape rectangle moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50", 
        moveRec.toString());
    
    Event moveOval = new MoveShape(oval, 500, 100, 500, 400);
    moveOval.setBeginTick(20);
    moveOval.setEndTick(70);
    assertEquals("Shape oval moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70",
        moveOval.toString());
    
    // scale
    Event scaleRec = new ScaleShape(rectangle, 50, 100, 100, 50);
    scaleRec.setBeginTick(51);
    scaleRec.setEndTick(70);
    assertEquals("Shape rectangle scales from Width: 50.0, Height: 100.0 to Width: 100.0, " 
                  + "Height: 50.0 from t=51 to t=70", scaleRec.toString());
    
    Event scaleOval = new ScaleShape(oval, 120, 60, 60, 120);
    scaleOval.setBeginTick(20);
    scaleOval.setEndTick(100);
    assertEquals("Shape oval scales from Width: 120.0, Height: 60.0 to Width: 60.0, " 
                  + "Height: 120.0 from t=20 to t=100", scaleOval.toString());
    
    // recolor
    Event recolorRec = new RecolorShape(rectangle, 1, 0, 0, 0, 0, 1);
    recolorRec.setBeginTick(0);
    recolorRec.setEndTick(10);
    assertEquals("Shape rectangle changes color from (1,0,0) to (0,0,1) from t=0 to t=10", 
        recolorRec.toString());
    
    Event recolorOval = new RecolorShape(oval, 0, 0, 1, 10, 10, 10);
    recolorOval.setBeginTick(22);
    recolorOval.setEndTick(44);
    assertEquals("Shape oval changes color from (0,0,1) to (10,10,10) from t=22 to t=44", 
        recolorOval.toString());
    
  } 
  
  /**
   * Test constructor with invalid inputs.
   */
  @Test
  public void testInvalidConstructor() {
    // move shape
    try {
      new MoveShape(null, 0, 0, 1, 1);
    } catch (Exception e) {
      assertEquals("Invalid shape.", e.getMessage());
    }
    try {
      new MoveShape(rectangle, 200, 200, 200, 200);
    } catch (Exception e) {
      assertEquals("Cannot move to same position.", e.getMessage());
    }
    
    // scale shape
    try {
      new ScaleShape(rectangle, 50, 100, 50, 100);
    } catch (Exception e) {
      assertEquals("Cannot scale to same dimensions.", e.getMessage());
    }
    try {
      new ScaleShape(oval, -1, 60, 60, 120);
    } catch (Exception e) {
      assertEquals("Invalid dimensions.", e.getMessage());
    }
    try {
      new ScaleShape(oval, 120, -60, 60, 120);
    } catch (Exception e) {
      assertEquals("Invalid dimensions.", e.getMessage());
    }
    try {
      new ScaleShape(oval, 120, 60, -60, 120);
    } catch (Exception e) {
      assertEquals("Invalid dimensions.", e.getMessage());
    }
    try {
      new ScaleShape(oval, 120, 60, 60, -120);
    } catch (Exception e) {
      assertEquals("Invalid dimensions.", e.getMessage());
    }
    
    // recolor shape
    try {
      new RecolorShape(rectangle, 1, 0, 0, 1, 0, 0);
    } catch (Exception e) {
      assertEquals("Cannot change to the same color.", e.getMessage());
    }
    
  }
  
  /**
   * Test getEventType method.
   */
  @Test
  public void testGetEventType() {
    Event moveR = new MoveShape(rectangle, 200, 200, 300, 300);
    assertEquals(EventType.MOVE, moveR.getEventType());
    Event moveO = new MoveShape(oval, 500, 100, 500, 400);
    assertEquals(EventType.MOVE, moveO.getEventType());
    Event scaleR = new ScaleShape(rectangle, 50, 100, 100, 50);
    assertEquals(EventType.SCALE, scaleR.getEventType());
    Event scaleO = new ScaleShape(oval, 120, 60, 60, 120);
    assertEquals(EventType.SCALE, scaleO.getEventType());
    Event recolorR = new RecolorShape(rectangle, 1, 0, 0, 0, 0, 1);
    assertEquals(EventType.RECOLOR, recolorR.getEventType());
    Event recolorO = new RecolorShape(oval, 0, 0, 1, 10, 10, 10);
    assertEquals(EventType.RECOLOR, recolorO.getEventType());
  }
  
  /**
   * Test setBeginTick and getBeginTick method.
   */
  @Test
  public void testBeginTick() {
    Event moveR = new MoveShape(rectangle, 200, 200, 300, 300);
    for (int i = 0; i < 10; i++) {
      moveR.setBeginTick(i);
      assertEquals(i, moveR.getBeginTick());
    }
    Event scaleO = new ScaleShape(oval, 120, 60, 60, 120);
    for (int i = 10; i < 100; i++) {
      scaleO.setBeginTick(i);
      assertEquals(i, scaleO.getBeginTick());
    }
    Event recolorR = new RecolorShape(rectangle, 1, 0, 0, 0, 0, 1);
    for (int i = 0; i < 100; i++) {
      recolorR.setBeginTick(i);
      assertEquals(i, recolorR.getBeginTick());
    }
  }
  
  /**
   * Test setBeginTick with invalid input.
   */
  @Test
  public void testInvalidSetBeginTick() {
    try {
      Event move = new MoveShape(rectangle, 200, 200, 300, 300);
      move.setBeginTick(-1);
    } catch (Exception e) {
      assertEquals("Event begin tick cannot be negative.", e.getMessage());
    }
  }
  
  /**
   * Test setEndTick with invalid input.
   */
  @Test
  public void testInvalidSetEndTick() {
    try {
      Event move = new MoveShape(rectangle, 200, 200, 300, 300);
      move.setBeginTick(0);
      move.setEndTick(0);
    } catch (Exception e) {
      assertEquals("Event end tick cannot be negative or less than begin tick.", e.getMessage());
    }
    try {
      Event move = new MoveShape(rectangle, 200, 200, 300, 300);
      move.setBeginTick(10);
      move.setEndTick(9);
    } catch (Exception e) {
      assertEquals("Event end tick cannot be negative or less than begin tick.", e.getMessage());
    }
    try {
      Event move = new MoveShape(rectangle, 200, 200, 300, 300);
      move.setEndTick(-1);
    } catch (Exception e) {
      assertEquals("Event end tick cannot be negative or less than begin tick.", e.getMessage());
    }
  }

}
