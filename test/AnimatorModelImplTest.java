import org.junit.Before;
import org.junit.Test;


import cs5004.model.AnimatorModel;
import cs5004.model.AnimatorModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test for the AnimatorModelImpl class.
 */
public class AnimatorModelImplTest {
  private AnimatorModel test1;

  @Before
  public void setUp() {
    test1 = new AnimatorModelImpl();
  }

  @Test
  public void testConstructorEmptyAtSetUp() {
    assertEquals("", test1.declarativeAnimation());
  }

  @Test
  public void addShapeRectangle() {
    test1.addShape(3.0, 4.0, 5.0, "Rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);

    assertEquals("Name: R1\nType: rectangle\nMin corner: (200.0,200.0), " +
            "Width: 50.0, Height: 60.0, Color: (3.0,4.0,5.0)\n" +
            "Appears at t=10\nDisappears at t=100", test1.declarativeAnimation());
  }

  @Test
  public void addShapeOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    assertEquals("Name: O1\nType: oval\nCenter: (10.0,10.0), " +
            "x radius: 40.0, y radius: 20.0, Color: (1.0,2.0,1.0)\n" +
            "Appears at t=0\nDisappears at t=50", test1.declarativeAnimation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeColorBelow0Rectangle() {
    test1.addShape(-1.0, 2.0, 1.0, "rectangle", "O1", 10,
            10, 40.0, 20.0, 0, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeColorBelow0Oval() {
    test1.addShape(-1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeColorAbove255Rectangle() {
    test1.addShape(256.0, 2.0, 1.0, "rectangle", "O1", 10,
            10, 40.0, 20.0, 0, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeColorAbove255Oval() {
    test1.addShape(1.0, 256.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddUnsupportedShape() {
    test1.addShape(1.0, 2.0, 1.0, "circle", "O1", 10,
            10, 40.0, 20.0, 0, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNegStartTimeRectangle() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "O1", 10,
            10, 40.0, 20.0, -4, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNegStartTimeOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, -4, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNegEndTimeRectangle() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "O1", 10,
            10, 40.0, 20.0, 4, -50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNegEndTimeOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 4, -50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeEndLessThanStartTimel() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 50, 30);
  }

  @Test
  public void AddMultipleShapes() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "R1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    assertEquals("Name: R1\nType: rectangle\nMin corner: (10.0,10.0), Width: 40.0, " +
                    "Height: 20.0, Color: (1.0,2.0,1.0)\n" +
                    "Appears at t=0\nDisappears at t=50\n\n" +
                    "Name: O1\nType: oval\nCenter: (10.0,10.0), x radius: 40.0, y radius: 20.0, " +
                    "Color: (1.0,2.0,1.0)\nAppears at t=0\nDisappears at t=50",
            test1.declarativeAnimation());

  }

  @Test(expected = IllegalArgumentException.class)
  public void addShapeWithSameName() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "R1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "R1", 30,
            15, 40.0, 25.0, 0, 70);
  }

  @Test
  public void moveShapeRectangle() {
    test1.addShape(3.0, 4.0, 5.0, "Rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.moveShape("R1", 200, 200, 300, 300,
            20, 30);

    assertEquals("Name: R1\nType: rectangle\nMin corner: (200.0,200.0), " +
            "Width: 50.0, Height: 60.0, Color: (3.0,4.0,5.0)\n" +
            "Appears at t=10\nDisappears at t=100\n\nShape R1 moves from (200.0,200.0) " +
            "to (300.0,300.0) from t=20 to t=30", test1.declarativeAnimation());
  }

  @Test
  public void moveShapeOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.moveShape("O1", 10.0, 10.0, 30.0, 50.0,
            10, 20);
    assertEquals("Name: O1\nType: oval\nCenter: (10.0,10.0), " +
            "x radius: 40.0, y radius: 20.0, Color: (1.0,2.0,1.0)\n" +
            "Appears at t=0\nDisappears at t=50\n\nShape O1 moves from (10.0,10.0) to " +
            "(30.0,50.0) from t=10 to t=20", test1.declarativeAnimation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShapeNotInMap() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.moveShape("O2", 10.0, 10.0, 30.0, 50.0,
            10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShapeIncorrectStartPosition() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.moveShape("O2", 50.0, 10.0, 30.0, 50.0,
            10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShapeNotWithinShapeTimeFrameRectangle() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "R1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.moveShape("R1", 50.0, 10.0, 30.0, 50.0,
            60, 70);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShapeNotWithinShapeTimeFrameOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.moveShape("R1", 50.0, 10.0, 30.0, 50.0,
            60, 70);
  }

  @Test
  public void moveMultiplesTimesRectangle() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "R1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.moveShape("R1", 10.0, 10.0, 30.0, 50.0,
            20, 30);
    test1.moveShape("R1", 30.0, 50.0, 40.0, 35.0,
            31, 40);
    assertEquals("Name: R1\nType: rectangle\nMin corner: (10.0,10.0), " +
                    "Width: 40.0, Height: 20.0, Color: (1.0,2.0,1.0)\n" +
                    "Appears at t=0\nDisappears at t=50\n\nShape R1 moves from (10.0,10.0) to " +
                    "(30.0,50.0) from t=20 to t=30\nShape R1 moves from (30.0,50.0) " +
                    "to (40.0,35.0) from t=31 to t=40",
            test1.declarativeAnimation());
  }

  @Test
  public void moveMultiplesTimesOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.moveShape("O1", 10.0, 10.0, 30.0, 50.0,
            20, 30);
    test1.moveShape("O1", 30.0, 50.0, 40.0, 35.0,
            31, 40);
    assertEquals("Name: O1\nType: oval\nCenter: (10.0,10.0), " +
                    "x radius: 40.0, y radius: 20.0, Color: (1.0,2.0,1.0)\n" +
                    "Appears at t=0\nDisappears at t=50\n\nShape O1 moves from (10.0,10.0) to " +
                    "(30.0,50.0) from t=20 to t=30\nShape O1 moves from (30.0,50.0) to " +
                    "(40.0,35.0) from t=31 to t=40",
            test1.declarativeAnimation());
  }

  @Test
  public void moveOnMultipleShapes() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.addShape(3.0, 4.0, 5.0, "Rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.moveShape("O1", 10.0, 10.0, 30.0, 50.0,
            10, 20);
    test1.moveShape("R1", 200, 200, 300, 300,
            10, 20);

    assertEquals("Name: O1\nType: oval\nCenter: (10.0,10.0), " +
                    "x radius: 40.0, y radius: 20.0, Color: (1.0,2.0,1.0)\n" +
                    "Appears at t=0\nDisappears at t=50\n\nName: R1\nType: rectangle\nMin corner:" +
                    " (200.0,200.0), " +
                    "Width: 50.0, Height: 60.0, Color: (3.0,4.0,5.0)\nAppears at t=10\n" +
                    "Disappears at t=100\n\n" +
                    "Shape O1 moves from (10.0,10.0) to " +
                    "(30.0,50.0) from t=10 to t=20\nShape R1 moves " +
                    "from (200.0,200.0) to (300.0,300.0) from t=10 to t=20",
            test1.declarativeAnimation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShapeNegStartimeRectangle() {
    test1.addShape(3.0, 4.0, 5.0, "Rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.moveShape("R1", 200, 200, 300, 300,
            -20, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShapeNegEndimeRectangle() {
    test1.addShape(3.0, 4.0, 5.0, "Rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.moveShape("R1", 200, 200, 300, 300,
            20, -30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShapeNegStarttimeOval() {
    test1.addShape(3.0, 4.0, 5.0, "oval",
            "O1", 200, 200, 50.0, 60.0, 10, 100);
    test1.moveShape("O1", 200, 200, 300, 300,
            -20, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShapeNegEndtimeOval() {
    test1.addShape(3.0, 4.0, 5.0, "oval",
            "O1", 200, 200, 50.0, 60.0, 10, 100);
    test1.moveShape("O1", 200, 200, 300, 300,
            20, -30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShapeAtSameTimeAlreadyMoving1() {
    test1.addShape(3.0, 4.0, 5.0, "oval",
            "O1", 200, 200, 50.0, 60.0, 10, 100);
    test1.moveShape("O1", 200, 200, 300, 300,
            20, 30);
    test1.moveShape("O1", 200, 200, 300, 300,
            25, 35);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShapeAtSameTimeAlreadyMoving2() {
    test1.addShape(3.0, 4.0, 5.0, "oval",
            "O1", 200, 200, 50.0, 60.0, 10, 100);
    test1.moveShape("O1", 200, 200, 300, 300,
            20, 30);
    test1.moveShape("O1", 200, 200, 300, 300,
            15, 25);
  }

  @Test
  public void changeColorRectangle() {
    test1.addShape(3.0, 4.0, 5.0, "Rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeColor("R1", 5.0, 6.0, 7.0,
            50, 60);

    assertEquals("Name: R1\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, " +
            "Height: 60.0, Color: (3.0,4.0,5.0)\n" + "Appears at t=10\nDisappears at " +
            "t=100\n\n" + "Shape R1 changes color from (3.0,4.0,5.0) to (5.0,6.0,7.0) from " +
            "t=50 " + "to t=60", test1.declarativeAnimation());
  }

  @Test
  public void changeColorOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeColor("O1", 6.0, 30.0, 64.0,
            30, 40);
    assertEquals("Name: O1\nType: oval\nCenter: (10.0,10.0), " +
            "x radius: 40.0, y radius: 20.0, Color: (1.0,2.0,1.0)\n" +
            "Appears at t=0\nDisappears at t=50\n\nShape O1 changes color from (1.0,2.0,1.0) to " +
            "(6.0,30.0,64.0) from t=30 to t=40", test1.declarativeAnimation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorOvalNonExistantShapeOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeColor("O2", 6.0, 30.0, 64.0,
            30, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorNotWithinShapesTimeFrameOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeColor("O1", 6.0, 30.0, 64.0,
            60, 70);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorNotWithinShapesTimeFrameRec() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "R1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeColor("R1", 6.0, 30.0, 64.0,
            60, 70);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorBelow0Rec() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "R1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeColor("R1", -1.0, 30.0, 64.0,
            20, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorAbove255Rec() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "R1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeColor("R1", 256.0, 30.0, 64.0,
            20, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorBelow0Oval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeColor("O1", -1.0, 30.0, 64.0,
            20, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorAbove255Oval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeColor("O1", 256.0, 30.0, 64.0,
            20, 30);
  }

  @Test
  public void changeColorMultipleTimesRectangle() {
    test1.addShape(3.0, 4.0, 5.0, "Rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeColor("R1", 5.0, 6.0, 7.0,
            50, 60);
    test1.changeColor("R1", 9.0, 5.0, 7.0,
            70, 80);

    assertEquals("Name: R1\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, " +
            "Height: 60.0, Color: (3.0,4.0,5.0)\n" + "Appears at t=10\nDisappears at " +
            "t=100\n\n" + "Shape R1 changes color from (3.0,4.0,5.0) to (5.0,6.0,7.0) from " +
            "t=50 " + "to t=60\nShape R1 changes color from (5.0,6.0,7.0) to (9.0,5.0,7.0) from " +
            "t=70 to t=80", test1.declarativeAnimation());
  }

  @Test
  public void changeColorMultipleTimesOval() {
    test1.addShape(3.0, 4.0, 5.0, "Oval",
            "O1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeColor("O1", 5.0, 6.0, 7.0,
            50, 60);
    test1.changeColor("O1", 9.0, 5.0, 7.0,
            70, 80);

    assertEquals("Name: O1\nType: oval\nCenter: (200.0,200.0), x radius: 50.0, " +
            "y radius: 60.0, Color: (3.0,4.0,5.0)\n" + "Appears at t=10\nDisappears at " +
            "t=100\n\n" + "Shape O1 changes color from (3.0,4.0,5.0) to (5.0,6.0,7.0) from " +
            "t=50 " + "to t=60\nShape O1 changes color from (5.0,6.0,7.0) to (9.0,5.0,7.0) from " +
            "t=70 to t=80", test1.declarativeAnimation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorAlreadyChanging1() {
    test1.addShape(3.0, 4.0, 5.0, "Oval",
            "O1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeColor("O1", 5.0, 6.0, 7.0,
            50, 60);
    test1.changeColor("O1", 9.0, 5.0, 7.0,
            55, 80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorAlreadyChanging2() {
    test1.addShape(3.0, 4.0, 5.0, "Oval",
            "O1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeColor("O1", 5.0, 6.0, 7.0,
            50, 60);
    test1.changeColor("O1", 9.0, 5.0, 7.0,
            40, 55);
  }


  @Test
  public void changeColorMultipleShapes() {
    test1.addShape(3.0, 4.0, 5.0, "Oval",
            "O1", 200, 200, 50.0, 60.0, 10, 100);
    test1.addShape(3.0, 4.0, 5.0, "Rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeColor("O1", 5.0, 6.0, 7.0,
            50, 60);
    test1.changeColor("R1", 5.0, 6.0, 7.0,
            40, 60);

    assertEquals("Name: O1\nType: oval\nCenter: (200.0,200.0)," +
            " x radius: 50.0, y radius: 60.0, Color: (3.0,4.0,5.0)\nAppears at t=10\n" +
            "Disappears at t=100\n\nName: R1\nType: rectangle\nMin corner: (200.0,200.0), " +
            "Width: 50.0, " +
            "Height: 60.0, Color: (3.0,4.0,5.0)\n" + "Appears at t=10\nDisappears at " +
            "t=100\n\n" + "Shape O1 changes color from (3.0,4.0,5.0) to (5.0,6.0,7.0) " +
            "from t=50 to t=60" +
            "\nShape R1 changes color from (3.0,4.0,5.0) to (5.0,6.0,7.0) from " +
            "t=40 " + "to t=60", test1.declarativeAnimation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorNegStartTimeOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeColor("O1", 254.0, 30.0, 64.0,
            -20, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorNegEndTimeOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeColor("O1", 254.0, 30.0, 64.0,
            20, -30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorNegStartTimeRec() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "R1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeColor("R1", 254.0, 30.0, 64.0,
            -20, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorNegEndTimeRec() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "R1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeColor("R1", 254.0, 30.0, 64.0,
            20, -30);
  }

  @org.junit.Test
  public void changeSizeRectangle() {
    test1.addShape(3.0, 4.0, 5.0, "Rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeSize("R1", 90, 100, 70, 80);
    assertEquals("Name: R1\nType: rectangle\nMin corner: (200.0,200.0), " +
            "Width: 50.0, Height: 60.0, Color: (3.0,4.0,5.0)\n" +
            "Appears at t=10\nDisappears at t=100\n\nShape R1 scales from Width: 50.0, " +
            "Height: 60.0 to" +
            " Width: 90.0, Height: 100.0 from t=70 to t=80", test1.declarativeAnimation());
  }

  @Test
  public void changeSizeOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeSize("O1", 30.0, 55.5, 30, 40);

    assertEquals("Name: O1\nType: oval\nCenter: (10.0,10.0), " +
            "x radius: 40.0, y radius: 20.0, Color: (1.0,2.0,1.0)\n" +
            "Appears at t=0\nDisappears at t=50\n\nShape O1 scales from x radius: 40.0, " +
            "y radius: 20.0 to" +
            " x radius: 30.0, y radius: 55.5 from t=30 to t=40", test1.declarativeAnimation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void ChangeSizeNonExistantShape() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeSize("O2", 30.0, 55.5, 30, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ChangeSizeNotWithinShapeTimeFrameOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeSize("O1", 30.0, 55.5, 60, 70);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ChangeSizeNotWithinShapeTimeFrameRec() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "R1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeSize("R1", 30.0, 55.5, 60, 70);
  }

  @Test
  public void ChangeSizeMultipleTimesOneShape() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "R1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeSize("R1", 30.0, 55.5, 30, 40);
    test1.changeSize("R1", 50.0, 60.0, 41, 45);
    assertEquals("Name: R1\nType: rectangle\nMin corner: (10.0,10.0), Width: 40.0, " +
            "Height: 20.0, Color: (1.0,2.0,1.0)\n" +
            "Appears at t=0\nDisappears at t=50\n\nShape R1 scales from Width: 40.0, Height: " +
            "20.0 to Width: 30.0, Height: 55.5" +
            " from t=30 to t=40\nShape R1 scales from Width: 30.0, Height: 55.5 to Width: 50.0," +
            " Height: 60.0" +
            " from t=41 to t=45", test1.declarativeAnimation());
  }

  @Test
  public void ChangeSizeOnMultipleShapes() {
    test1.addShape(1.0, 2.0, 1.0, "rectangle", "R1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.changeSize("R1", 30.0, 55.5, 30, 40);
    test1.changeSize("O1", 30.0, 55.5, 30, 40);
    assertEquals("Name: R1\nType: rectangle\nMin corner: (10.0,10.0), Width: 40.0, " +
            "Height: 20.0, Color: (1.0,2.0,1.0)\n" +
            "Appears at t=0\nDisappears at t=50\n\n" +
            "Name: O1\nType: oval\nCenter: (10.0,10.0), x radius: 40.0, y radius: 20.0, " +
            "Color: (1.0,2.0,1.0)\nAppears at t=0\nDisappears at t=50\n\n" +
            "Shape R1 scales from Width: 40.0, Height: " +
            "20.0 to Width: 30.0, Height: 55.5" +
            " from t=30 to t=40\nShape O1 scales from x radius: 40.0, y radius: 20.0 to " +
            "x radius: " +
            "30.0, y radius: 55.5 from t=30 to t=40", test1.declarativeAnimation());

  }

  @Test(expected = IllegalArgumentException.class)
  public void ChangeSizeNegStartTimeRec() {
    test1.addShape(3.0, 4.0, 5.0, "Rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeSize("R1", 90, 100, -10, 80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ChangeSizeNegEndTimeRec() {
    test1.addShape(3.0, 4.0, 5.0, "Rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeSize("R1", 90, 100, 10, -80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ChangeSizeNegStartTimeOval() {
    test1.addShape(3.0, 4.0, 5.0, "Oval",
            "O1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeSize("O1", 90, 100, -10, 80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ChangeSizeNegEndTimeOval() {
    test1.addShape(3.0, 4.0, 5.0, "oval",
            "O1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeSize("O1", 90, 100, 10, -80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ChangeSizeNegWidthOval() {
    test1.addShape(3.0, 4.0, 5.0, "oval",
            "O1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeSize("O1", -90, 100, 10, 80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ChangeSizeNegHeightOval() {
    test1.addShape(3.0, 4.0, 5.0, "oval",
            "O1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeSize("O1", 90, -100, 10, 80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ChangeSizeNegWidthRec() {
    test1.addShape(3.0, 4.0, 5.0, "rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeSize("R1", -90, 100, 10, 80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ChangeSizeNegHeightRec() {
    test1.addShape(3.0, 4.0, 5.0, "rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeSize("R1", 90, -100, 10, 80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ChangeSizeZeroHeightRec() {
    test1.addShape(3.0, 4.0, 5.0, "rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeSize("R1", 90, 0, 10, 80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ChangeSizeZeroWidthRec() {
    test1.addShape(3.0, 4.0, 5.0, "rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeSize("R1", 0, 0, 10, 80);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeSizeAlreadyChangingSize1() {
    test1.addShape(3.0, 4.0, 5.0, "rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeSize("R1", 3.0, 10.0, 10, 80);
    test1.changeSize("R1", 4.0, 6.0, 15, 90);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeSizeAlreadyChangingSize2() {
    test1.addShape(3.0, 4.0, 5.0, "rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.changeSize("R1", 3.0, 10.0, 10, 80);
    test1.changeSize("R1", 4.0, 6.0, 8, 70);
  }

  @org.junit.Test
  public void declarativeAnimationRectangle() {
    test1.addShape(3.0, 4.0, 5.0, "Rectangle",
            "R1", 200, 200, 50.0, 60.0, 10, 100);
    test1.moveShape("R1", 200, 200, 300, 300,
            20, 30);
    test1.changeColor("R1", 5.0, 6.0, 7.0,
            50, 60);
    test1.changeSize("R1", 90, 100, 70, 80);
    assertEquals("Name: R1\nType: rectangle\nMin corner: (200.0,200.0), Width: " +
            "50.0, Height: 60.0, Color: (3.0,4.0,5.0)\n" +
            "Appears at t=10\nDisappears at t=100\n\nShape R1 moves from (200.0,200.0) to " +
            "(300.0,300.0) from t=20 to t=30\n" +
            "Shape R1 changes color from (3.0,4.0,5.0) to (5.0,6.0,7.0) from t=50 to " +
            "t=60\nShape R1 scales from Width: 50.0, Height: 60.0 to Width: 90.0, Height: 100.0" +
            " from t=70 to t=80", test1.declarativeAnimation());
  }

  @Test
  public void declarativeAnimationOval() {
    test1.addShape(1.0, 2.0, 1.0, "oval", "O1", 10,
            10, 40.0, 20.0, 0, 50);
    test1.moveShape("O1", 10.0, 10.0, 30.0, 50.0,
            10, 20);
    test1.changeColor("O1", 6.0, 30.0, 64.0,
            30, 40);
    test1.changeSize("O1", 30.0, 55.5, 30, 40);

    assertEquals("Name: O1\nType: oval\nCenter: (10.0,10.0), " +
            "x radius: 40.0, y radius: 20.0, Color: (1.0,2.0,1.0)\n" +
            "Appears at t=0\nDisappears at t=50\n\nShape O1 moves from (10.0,10.0) to " +
            "(30.0,50.0) from t=10 to t=20\nShape O1 changes color from (1.0,2.0,1.0) to " +
            "(6.0,30.0,64.0) from t=30 to t=40\nShape O1 scales from x radius: 40.0, " +
            "y radius: 20.0 to" +
            " x radius: 30.0, y radius: 55.5 from t=30 to t=40", test1.declarativeAnimation());
  }

}