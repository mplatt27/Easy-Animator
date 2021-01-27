package cs5004.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.Timer;

import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.AnimatorView;
import cs5004.animator.view.AnimatorViewPlayback;
import cs5004.animator.view.AnimatorViewSVG;
import cs5004.animator.view.AnimatorViewText;
import cs5004.animator.view.AnimatorViewVisual;
import cs5004.model.AnimatorModelImpl;

/**
 * This class represents the AnimatorControllerImpl which implements AnimatorController. The purpose
 * of the controller is to use the model's data and execute the appropriate animation that the user
 * has indicated. This means calling the appropriate view methods to print or save output to a file
 * (for text and svg view), or running the animation as a GUI for the visual and playback views. The
 * Controller creates a Timer that manges the playing of the animation itself. In the playback view,
 * the Controller also continuously calls for the state of the view's attributes (buttons, looping
 * flags, etc.), and calls the appropriate methods to perform tasks.
 */
public class AnimatorControllerImpl implements AnimatorController {
  private String viewType;
  private String fileOutput;
  private int speed;
  private AnimatorModelImpl model;
  private Timer t;
  private String dummy;

  /**
   * Constructs an AnimatorControllerImpl object by the given fileName, viewType, fileOutput,
   * speed.
   *
   * @param fileName   the file to be read from.
   * @param viewType   the view type to play the animation.
   * @param fileOutput the file to output the animation to.
   * @param speed      the speed to play the animation.
   */
  public AnimatorControllerImpl(String fileName, String viewType, String fileOutput, String speed) {
    this.viewType = viewType;
    this.fileOutput = fileOutput;
    this.speed = Integer.parseInt(speed);

    // create model from txt file passed in
    FileReader inputFile = null;
    try {
      inputFile = new FileReader(fileName);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Invalid file!");
    }

    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    this.model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
  }


  @Override
  public int displayView() {
    if (viewType.equals("text")) {
      AnimatorView textView;
      if (fileOutput.equalsIgnoreCase("System.out")) {
        textView = new AnimatorViewText(model, speed, "");
        textView.printOutput();
      } else {
        textView = new AnimatorViewText(model, speed, fileOutput);
        textView.fileOutput();
      }
      return 0;
    }

    // execute SVG view (either to print out SVG code or output to file)
    if (viewType.equals("svg")) {
      AnimatorView svgView;
      if (fileOutput.equals("System.out")) {
        svgView = new AnimatorViewSVG(model, speed, "");
        svgView.printOutput();
      } else {
        svgView = new AnimatorViewSVG(model, speed, fileOutput);
        svgView.fileOutput();
      }
      return 1;
    }

    // execute visual view (standard or playback
    if (viewType.equals("visual") || viewType.equalsIgnoreCase("playback")) {


      if (viewType.equals("visual")) {
        t = new Timer(speed, new MyAnimationFrameHandler(model));
        t.start();
        return 2;
      }

      if (viewType.equals("playback")) {
        AnimatorViewPlayback viewPlayback = new AnimatorViewPlayback(model);
        MyAnimationFrameHandler handler = new MyAnimationFrameHandler(model, viewPlayback);
        t = new Timer(speed, handler);

        while (true) {
          String action = viewPlayback.getAction();

          switch (action) {
            case "start":
              if (handler.getCurrentTick() == 0) {
                t.start();
              }
              break;
            case "pause":
              t.stop();
              break;
            case "restart":
              t.stop();
              handler.setCurrentTick(0);
              break;
            case "resume":
              if (handler.getCurrentTick() > 0) {
                t.start();
              }
              break;
            default:
              dummy = "";
          }

          String speedAction = viewPlayback.getSpeedAction();
          switch (speedAction) {
            case "increase":
              t.setDelay(speed / 3);
              break;
            case "decrease":
              t.setDelay(speed * 3);
              break;
            case "reset":
              t.setDelay(speed);
              break;
            default:
              break;
          }
        }
      }
    }
    return -1;
  }

  /**
   * This is a public static class in the EasyAnimator that implements ActionListener. This class
   * holds the current tick, and an instance of the model and view.
   */
  public class MyAnimationFrameHandler implements ActionListener {
    private int currentTick;
    private AnimatorView view;
    private AnimatorModelImpl model;

    MyAnimationFrameHandler(AnimatorModelImpl model) {
      this.view = new AnimatorViewVisual(model);
      this.currentTick = 0;
      this.model = model;
    }

    MyAnimationFrameHandler(AnimatorModelImpl model, AnimatorViewPlayback viewPlayback) {
      this.view = viewPlayback;
      this.currentTick = 0;
      this.model = model;
    }

    /**
     * Sets the current tick to the number passed in.
     *
     * @param tick the tick to set the currentTick to.
     */
    public void setCurrentTick(int tick) {
      this.currentTick = tick;
    }

    /**
     * Gets the current tick.
     *
     * @return the currentTick.
     */
    public int getCurrentTick() {
      return this.currentTick;
    }

    /**
     * Controls what happens at each tick of the Timer. The setCurrentFrame() method is called in
     * the view; passed into this is the list of shapes at the current tick that we are on
     * (retrieved by calling the tweening method on the model, passing in the current tick). This
     * method consequently calls setCurrentFrame() on the panel in the view, which gives the list of
     * shapes to the panel and draws them (using the paintComponent() method. Next, the refresh()
     * method is called on the view, which gets the frame ready for the next tick. Finally, the
     * current tick is incremented by 1.
     *
     * @param actionEvent a semantic event that indicates that a component-defined action occurred.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      if (currentTick <= model.getEndTick()) {
        view.setCurrentFrame(model.tweening(currentTick));
        view.refresh();
        currentTick++;
      } else {
        if (view.getLoopingFlag()) {
          currentTick = 0;
          t.start();
        } else {
          t.stop();
        }
      }
    }

  }

}

