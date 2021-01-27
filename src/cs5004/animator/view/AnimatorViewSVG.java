package cs5004.animator.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs5004.model.AnimatorModelImpl;
import cs5004.model.Shape;

/**
 * This class represents the AnimatorViewSVG which implements the AnimatorView. This view presents
 * the animation using SVG code.
 */
public class AnimatorViewSVG implements AnimatorView {
  private AnimatorModelImpl model;
  private int speed; // ticks per second
  private String fileOutputName;

  /**
   * Constructs an AnimatorViewSVG object by the given model, speed, and fileOutputName.
   *
   * @param model          an instance of the model
   * @param speed          the speed at which the animation will run.
   * @param fileOutputName the file that the SVG code will be written to.
   */
  public AnimatorViewSVG(AnimatorModelImpl model, int speed, String fileOutputName) {
    this.model = model;
    this.speed = speed;
    this.fileOutputName = fileOutputName;

  }

  @Override
  public void printOutput() {
    System.out.println(stringOutput());
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

  @Override
  public String stringOutput() {
    StringBuilder output = new StringBuilder();
    output.append(String.format("<svg width=\"%d\" height=\"%d\" version=\"1.1\"  " +
                    "xmlns=\"http://www.w3.org/2000/svg\">\n",
            model.getWindowWidth(), model.getWindowHeight()));

    for (int k = 0; k < model.getOrder().size(); k++) {
      String currName = model.getOrder().get(k);
      ArrayList<String> currInstructArray = model.getIntroShapes().get(currName);

      String[] initialValues = currInstructArray.get(1).split(" ");
      if (currInstructArray.get(0).equals("rectangle")) {

        output.append(String.format("<rect id=\"%s\" x=\"%s\" y=\"%s\" width=\"%s\" " +
                        "height=\"%s\" " +
                        "fill=\"rgb(%s, %s, %s)\" visibility=\"visible\">\n",
                currName, initialValues[1], initialValues[2], initialValues[3], initialValues[4],
                initialValues[5], initialValues[6], initialValues[7]));

        svgHelper(currInstructArray, output);
        output.append("</rect>\n");

      }

      if (currInstructArray.get(0).equals("ellipse")) {

        output.append(String.format("<ellipse id=\"%s\" cx=\"%s\" cy=\"%s\" rx=\"%s\" " +
                        "ry=\"%s\" fill=\"rgb(%s, %s, %s)\" visibility=\"visible\">\n",
                currName, initialValues[1], initialValues[2], initialValues[3], initialValues[4],
                initialValues[5], initialValues[6], initialValues[7]));

        svgHelper(currInstructArray, output);
        output.append("</ellipse>\n");
      }
    }

    output.append("\n</svg>");
    return output.toString();

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

  /**
   * Helper method to parse through the motions and set it to the correct svg format.
   *
   * @param array  an array of the broken up instruction string.
   * @param output A String builder where all of the SVG code is appended to in the correct format.
   */
  private void svgHelper(ArrayList<String> array, StringBuilder output) {

    for (int i = 0; i < array.size(); i++) {
      if (i > 1) {
        String[] currentArray = array.get(i).split(" ");

        String r1 = currentArray[5];
        String g1 = currentArray[6];
        String b1 = currentArray[7];
        String r2 = currentArray[13];
        String g2 = currentArray[14];
        String b2 = currentArray[15];
        String w1 = currentArray[3];
        String h1 = currentArray[4];
        String w2 = currentArray[11];
        String h2 = currentArray[12];
        String x1 = currentArray[1];
        String y1 = currentArray[2];
        String x2 = currentArray[9];
        String y2 = currentArray[10];
        Double startTime = Double.parseDouble(currentArray[0]) * (1000 / speed);
        Double endTime = Double.parseDouble(currentArray[8]) * (1000 / speed);
        double duration = endTime - startTime;

        if (!x1.equals(x2)) {
          if (array.get(0).equals("rectangle")) {
            output.append(String.format("\t<animate attributeName=\"x\" attributeType=\"xml\" " +
                            "begin=\"%.2fms\" " +
                            "dur=\"%.2fms\" fill=\"freeze\" from=\"%s\" to=\"%s\" />\n",
                    startTime, duration, x1, x2));
          }
          if (array.get(0).equals("ellipse")) {
            output.append(String.format("\t<animate attributeName=\"cx\" attributeType=\"xml\" " +
                            "begin=\"%.2fms\" " +
                            "dur=\"%.2fms\" fill=\"freeze\" from=\"%s\" to=\"%s\" />\n",
                    startTime, duration, x1, x2));
          }
        }

        if (!y1.equals(y2)) {
          if (array.get(0).equals("rectangle")) {
            output.append(String.format("\t<animate attributeName=\"y\" attributeType=\"xml\" " +
                            "begin=\"%.2fms\" " +
                            "dur=\"%.2fms\" fill=\"freeze\" from=\"%s\" to=\"%s\" />\n",
                    startTime, duration, y1, y2));
          }
          if (array.get(0).equals("ellipse")) {
            output.append(String.format("\t<animate attributeName=\"cy\" attributeType=\"xml\" " +
                            "begin=\"%.2fms\" " +
                            "dur=\"%.2fms\" fill=\"freeze\" from=\"%s\" to=\"%s\" />\n",
                    startTime, duration, y1, y2));
          }
        }

        if (!w1.equals(w2)) {
          if (array.get(0).equals("rectangle")) {
            output.append(String.format("\t<animate attributeName=\"width\" " +
                            "attributeType=\"xml\" " +
                            "begin=\"%.2fms\" " +
                            "dur=\"%.2fms\" fill=\"freeze\" from=\"%s\" to=\"%s\" />\n",
                    startTime, duration, w1, w2));
          }
          if (array.get(0).equals("ellipse")) {
            output.append(String.format("\t<animate attributeName=\"rx\" attributeType=\"xml\" " +
                            "begin=\"%.2fms\" " +
                            "dur=\"%.2fms\" fill=\"freeze\" from=\"%s\" to=\"%s\" />\n",
                    startTime, duration, w1, w2));
          }
        }

        if (!h1.equals(h2)) {
          if (array.get(0).equals("rectangle")) {
            output.append(String.format("\t<animate attributeName=\"height\" " +
                            "attributeType=\"xml\"" +
                            " begin=\"%.2fms\" " +
                            "dur=\"%.2fms\" fill=\"freeze\" from=\"%s\" to=\"%s\" />\n",
                    startTime, duration, h1, h2));
          }
          if (array.get(0).equals("ellipse")) {
            output.append(String.format("\t<animate attributeName=\"ry\" " +
                            "attributeType=\"xml\" begin=\"%.2fms\" " +
                            "dur=\"%.2fms\" fill=\"freeze\" from=\"%s\" to=\"%s\" />\n",
                    startTime, duration, h1, h2));
          }
        }

        if (!r1.equals(r2) || !g1.equals(g2) || !b1.equals(b2)) {
          output.append(String.format("\t<animate attributeName=\"fill\" attributeType=\"CSS\" " +
                          "from=\"rgb(%s, %s, %s)\" to=\"rgb(%s, %s, %s)\" begin=\"%.2fms\" " +
                          "dur=\"%.2fms\" fill=\"freeze\" />\n", r1, g1, b1, r2, g2, b2,
                  startTime, duration));
        }
      }
    }
  }

}
