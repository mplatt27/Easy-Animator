package cs5004.animator;

import java.io.FileNotFoundException;
import cs5004.controller.AnimatorControllerImpl;

/**
 * The EasyAnimator class holds the main method, and acts as a mini controller.
 */
public final class EasyAnimator {

  /**
   * This is the main function that starts the program. It takes in a set of parameters from the
   * command line and parses them sets them as variables.
   *
   * @param args "-in" (name of txt file), "-view" (visual, text, or svg), (optional): "-speed"
   *             (number of ticks per second) & "-out" (either System.out, out.svg, out.txt, or
   *             empty).
   * @throws FileNotFoundException if FileReader cannot find the passed in file.
   */
  public static void main(String[] args) throws FileNotFoundException {

    // create variables to hold command line arguments
    String fileName = "";
    String viewType = "";
    String fileOutput = "";
    String speed = "";

    // parse command line arguments and save
    int i;
    for (i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        fileName = args[i + 1];
      }
      if (args[i].equals("-view")) {
        viewType = args[i + 1];
      }
      if (args[i].equals("-out")) {
        fileOutput = args[i + 1];
      }
      if (args[i].equals("-speed")) {
        speed = args[i + 1];
      }
    }

    // save defaults if nothing passed in
    if (fileOutput.equals("")) {
      fileOutput = "System.out";
    }
    if (speed.equals("")) {
      speed = "1";
    }

    AnimatorControllerImpl controller = new AnimatorControllerImpl(fileName,
            viewType, fileOutput, speed);
    controller.displayView();
  }
}