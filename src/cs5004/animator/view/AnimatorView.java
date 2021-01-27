package cs5004.animator.view;

import java.util.List;

import cs5004.model.Shape;

/**
 * This interface represents the AnimatorView and introduces methods that will be applied in all
 * classes that implement this interface. Some methods may not be relevant to all views, so they
 * will be given default return values.
 */
public interface AnimatorView {

  /**
   * This method prints the string containing the SVG animation code.
   */
  void printOutput();

  /**
   * This method outputs the String returned from stringOutput() in an svg file.
   */
  void fileOutput();

  /**
   * This method calls another setCurrentFrame() method on the TestPane to draw the animation for
   * the visual view at the current tick (see below in TestPane description).
   *
   * @param los the list of shapes and their state at the current tick.
   */
  void setCurrentFrame(List<Shape> los);

  /**
   * This method calls repaint on the frame for the visual view.
   */
  void refresh();

  /**
   * This method loops through each shape in the model and then loops through each of that shapes
   * instructions. While doing this, it converts those instructions into a String of SVG code that
   * will represent the instruction. Once finished, it returns the entire SVG code as a String which
   * can be printed out, or output to a file later if needed.
   *
   * @return the entire SVG code as a String which can be printed out, or output to a file later if
   *          needed.
   */
  String stringOutput();

  /**
   * Gets the boolean value that the looping flag is currently set to (true if looping is enabled,
   * false otherwise). The flag is initialized in the view as false.
   *
   * @return true if looping is enabled, false otherwise.
   */
  boolean getLoopingFlag();

  /**
   * Sets the looping flag as true if the user has enabled looping, false otherwise.
   *
   * @param flag true if user has enabled looping, false otherwise.
   */
  void setLoopingFlag(boolean flag);

  /**
   * Sets the appropriate String that represents the action the user has indicated (either start,
   * pause, resume, or restart).
   *
   * @param string the action the user has indicated (either start, pause, resume, or restart).
   */
  void setAction(String string);

  /**
   * Gets the String value representing the action the user has indicated (either start, pause,
   * resume, or restart).
   *
   * @return String of either: start, pause, resume, or restart depending on what the action is.
   */
  String getAction();

  /**
   * Sets the playback speed that the user has indicated (either regular speed that was passed in as
   * a command line argument, 3x that speed or 1/3 times that speed.
   *
   * @param action either "increase", "decrease", or "reset" to determine the speed to play the
   *               animation.
   */
  void setSpeedAction(String action);

  /**
   * Gets the playback speed that the user has indicated (either regular speed that was passed in as
   * a command line argument, 3x that speed or 1/3 times that speed.
   *
   * @return String of either "increase", "decrease", or "reset" depending on what the speed action
   *          is set to.
   */
  String getSpeedAction();
}
