package cs5004.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This interface represents the AnimatorModel and introduces the methods that will be implemented
 * in all classes that implement this interface.
 */

public interface AnimatorModel {

  /**
   * Adds a shape to the animation by initializing it by its given parameters. Appends instruction
   * to instructions array list.
   *
   * @param color1    the first number of the RGB color sequence of the shape.
   * @param color2    the second number of the RGB color sequence of the shape.
   * @param color3    the third number of the RGB color sequence of the shape.
   * @param type      the type of shape being initialized.
   * @param name      the name to be given to the shape.
   * @param x         x coordinate of the: lower-left corner of the rectangle, or the center of the
   *                  oval.
   * @param y         y coordinate of the: lower-left corner of the rectangle, or the center of the
   *                  oval.
   * @param a         the width of rectangle, or the radius1 of the oval.
   * @param b         the height of rectangle, or the radius2 of the oval.
   * @param startTime the appearance time of the shape.
   * @param endTime   the disappearance time of the shape.
   * @throws IllegalArgumentException if trying to initialize a shape other than oval or rectangle,
   *                                  or if trying to create shape with name that already exists.
   */
  void addShape(double color1, double color2, double color3, String type, String name,
                double x, double y, double a, double b, int startTime, int endTime)
          throws IllegalArgumentException;

  /**
   * Moves the given shape from one position to another within the given time frame. Appends the
   * instruction to instructions list.
   *
   * @param name      the name of the shape to be moved.
   * @param fromX     the current position of the x coordinate of the: lower-left corner of the
   *                  rectangle, or the center of the oval.
   * @param fromY     the current position of the y coordinate of the: lower-left corner of the
   *                  rectangle, or the center of the oval.
   * @param toX       the new position of the x coordinate of the: lower-left corner of the
   *                  rectangle, or the center of the oval.
   * @param toY       the new position of the y coordinate of the: lower-left corner of the
   *                  rectangle, or the center of the oval.
   * @param startTime the start time to move the shape.
   * @param endTime   the end time of moving the shape.
   * @throws IllegalArgumentException if start or end time is negative, if start time is greater
   *                                  than the end time, if trying to move a shape that doesn't
   *                                  exist, if shape is not in the starting position given, if
   *                                  shape is not present within the time frame given, if the shape
   *                                  is already moving at that time.
   */
  void moveShape(String name, double fromX, double fromY, double toX, double toY,
                 int startTime, int endTime) throws IllegalArgumentException;

  /**
   * Changes the color of the given shape to the color given. Appends instruction to the
   * instructions list.
   *
   * @param name      the name of the shape whose color is to be changed.
   * @param toColor1  the first number of the new RGB color sequence.
   * @param toColor2  the second number of the new RGB color sequence.
   * @param toColor3  the third number of the new RGB color sequence.
   * @param startTime the start time for the function to execute.
   * @param endTime   the end time for the function to execute.
   * @throws IllegalArgumentException if start or end time is negative, if start time is greater
   *                                  than the end time, if trying to change color of a shape that
   *                                  doesn't exist, if shape is not present within the time frame
   *                                  given, if shape is already changing colors during the time
   *                                  frame.
   */
  void changeColor(String name, double toColor1, double toColor2, double toColor3,
                   int startTime, int endTime) throws IllegalArgumentException;

  /**
   * Changes the size of the given shape to the new values given. Appends instruction to the
   * instructions list.
   *
   * @param name      the name of the shape whose size is to be changed.
   * @param toValue1  the new Width (rectangle) or radius1 (oval).
   * @param toValue2  the new Height (rectangle) or radius2 (oval).
   * @param startTime the start time for the function to execute.
   * @param endTime   the end time for the function to execute.
   * @throws IllegalArgumentException if start or end time is negative, if start time is greater
   *                                  than the end time, if trying to change size of a shape that
   *                                  doesn't exist, if shape is not present within the time frame
   *                                  given, if resizing values are negative or 0, if shape is
   *                                  already resizing during the time frame.
   */
  void changeSize(String name, double toValue1, double toValue2, int startTime,
                  int endTime) throws IllegalArgumentException;

  /**
   * Loops through the String Builder Appendable (instructions list) and prints all of its contents
   * as one string.
   *
   * @return a String of the contents of the String Builder Appendable.
   */
  String declarativeAnimation();

  /**
   * A getter for the Map that holds the Shape name as the key, and an array of all of its
   * attributes and motions as the value.
   *
   * @return the Map that holds the Shape name as the key, and an array of all of its attributes and
   *          motions as the value.
   */
  Map<String, ArrayList<String>> getIntroShapes();

  /**
   * A getter for the X position of the animation box.
   *
   * @return X position of the animation box.
   */
  int getWindowX();

  /**
   * A getter for the Y position of the animation box.
   *
   * @return Y position of the animation box.
   */
  int getWindowY();

  /**
   * A getter for the Width of the animation box.
   *
   * @return the Width of the animation box.
   */
  int getWindowWidth();

  /**
   * A getter for the Height of the animation box.
   *
   * @return the Height of the animation box.
   */
  int getWindowHeight();

  /**
   * A list that contains the names of all the shapes in the order that they are introduced.
   *
   * @return A list that contains the names of all the shapes in the order that they are introduced.
   */
  List<String> getOrder();

  /**
   * Takes in the current tick and finds the state of every shape that is present at that tick and
   * adds a new shape object with the Shape attributes of the shape at that specific tick.
   *
   * @param tick the specific tick.
   * @return A list of the Shapes with their state during the exact tick time.
   */
  List<Shape> tweening(int tick);

  /**
   * Used to find the last tick in the animation when building the model. Takes in a tick value and
   * sets it as the endTick value.
   *
   * @param tick potentially the last tick in the animation.
   */
  void setEndTick(int tick);

  /**
   * Returns the endTick, or the last tick in the animation.
   *
   * @return the last tick in the animation.
   */
  int getEndTick();
}
