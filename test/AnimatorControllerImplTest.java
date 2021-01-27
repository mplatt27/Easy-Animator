import org.junit.Test;

import cs5004.controller.AnimatorController;
import cs5004.controller.AnimatorControllerImpl;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test for the AnimatorControllerImpl class.
 */
public class AnimatorControllerImplTest {
  private AnimatorController test1;

  @Test
  public void visualControllerTest() {
    test1 = new AnimatorControllerImpl("buildings.txt", "visual", "", "200");
    assertEquals(2, test1.displayView());
  }

  @Test
  public void visualControllerTest2() {
    test1 = new AnimatorControllerImpl("hanoi.txt", "visual", "", "200");
    assertEquals(2, test1.displayView());
  }

  @Test
  public void textControllerTest() {
    test1 = new AnimatorControllerImpl("buildings.txt", "text", "System.out", "200");
    assertEquals(0, test1.displayView());
  }

  @Test
  public void textControllerTest2() {
    test1 = new AnimatorControllerImpl("hanoi.txt", "text", "System.out", "200");
    assertEquals(0, test1.displayView());
  }

  @Test
  public void svgControllerTest() {
    test1 = new AnimatorControllerImpl("buildings.txt", "svg", "System.out", "200");
    assertEquals(1, test1.displayView());
  }

  @Test
  public void svgControllerTest2() {
    test1 = new AnimatorControllerImpl("hanoi.txt", "svg", "System.out", "200");
    assertEquals(1, test1.displayView());
  }
}