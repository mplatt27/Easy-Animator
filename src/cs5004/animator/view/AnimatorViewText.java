package cs5004.animator.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cs5004.model.AnimatorModelImpl;
import cs5004.model.Shape;

/**
 * This class represents the AnimatorViewText which implements the AnimatorView. This view presents
 * the animation as descriptive text.
 */
public class AnimatorViewText implements AnimatorView {
  private AnimatorModelImpl model;
  private int speed;
  private String fileOutputName;

  /**
   * Constructs an AnimatorViewText with the given model, speed, and fileOutput name.
   *
   * @param model          an instance of the model.
   * @param speed          the speed at which the animation will run.
   * @param fileOutputName the file that the textual code will be written to.
   */
  public AnimatorViewText(AnimatorModelImpl model, int speed, String fileOutputName) {
    this.model = model;
    // this.speed = speed;
    this.fileOutputName = fileOutputName;
  }

  @Override
  public void printOutput() {
    String output = stringOutput();
    System.out.println(output);
  }

  @Override
  public String stringOutput() {
    return model.declarativeAnimation();
  }

  @Override
  public boolean getLoopingFlag() {
    return false;
  }

  @Override
  public void setLoopingFlag(boolean flag) {
    return;
  }

  @Override
  public void setAction(String string) {
    return;
  }

  @Override
  public String getAction() {
    return null;
  }

  @Override
  public void setSpeedAction(String action) {
    return;
  }

  @Override
  public String getSpeedAction() {
    return null;
  }

  @Override
  public void fileOutput() {
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(fileOutputName, true));
      out.write(stringOutput());
      out.close();
    } catch (IOException e) {
      System.out.println("There was an error writing your file.");
    }
  }

  @Override
  public void setCurrentFrame(List<Shape> los) {
    return;
  }

  @Override
  public void refresh() {
    return;
  }

}
