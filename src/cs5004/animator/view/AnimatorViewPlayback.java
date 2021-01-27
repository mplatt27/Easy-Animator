package cs5004.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.BoxLayout;


import cs5004.model.AnimatorModelImpl;
import cs5004.model.Shape;

/**
 * This class represents the AnimatorViewPlayback which extends JFrame and has a TestPane object,
 * like the visual view, that holds the pane that plays the animation. The JFrame has several
 * buttons, the allow the user to start, pause, resume, or restart an animation. There are also
 * toggle buttons for playback speed and enabling/disabling the looping feature. Additionally, this
 * class holds a String called currentAction, which refers to the action the user has indicated in
 * their latest button push, a boolean flag called loopingFlag, which indicates if the user has
 * enabled (true) or disabled (false) looping, and a String called speedAction, which indicates if
 * the user has decided to play the animation at 3x speed 1/3 speed, or the initial speed indicated
 * in the command line arguments.
 */
public class AnimatorViewPlayback extends JFrame implements AnimatorView {
  private AnimatorViewVisual.TestPane testPane;
  private String currentAction;
  private boolean loopingFlag;
  private String speedAction;

  /**
   * Constructs an AnimatorViewPlayback object with the given model, and sets the current action and
   * speed action as default empty strings, and the looping flag to false.
   *
   * @param model the built model.
   */
  public AnimatorViewPlayback(AnimatorModelImpl model) {
    super("Easy Animator");

    this.currentAction = "";
    this.loopingFlag = false;
    this.speedAction = "";

    setSize(1000, 800);
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create animation panel
    testPane = new AnimatorViewVisual.TestPane();
    testPane.setBounds(model.getWindowX(), model.getWindowY(),
            model.getWindowWidth(), model.getWindowHeight());
    add(testPane);


    // Create main buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

    ButtonGroup rGroup1 = new ButtonGroup();
    JButton startAnimation = new JButton("Start");
    JButton restartAnimation = new JButton("Restart");
    JButton pauseAnimation = new JButton("Pause");
    JButton resumeAnimation = new JButton("Resume");
    rGroup1.add(startAnimation);
    rGroup1.add(restartAnimation);
    rGroup1.add(pauseAnimation);
    rGroup1.add(resumeAnimation);

    startAnimation.setActionCommand("Start Animation");
    restartAnimation.setActionCommand("Restart Animation");
    pauseAnimation.setActionCommand("Pause Animation");
    resumeAnimation.setActionCommand("Resume Animation");
    startAnimation.addActionListener(new ButtonListener());
    restartAnimation.addActionListener(new ButtonListener());
    pauseAnimation.addActionListener(new ButtonListener());
    resumeAnimation.addActionListener(new ButtonListener());

    buttonPanel.add(startAnimation);
    buttonPanel.add(restartAnimation);
    buttonPanel.add(pauseAnimation);
    buttonPanel.add(resumeAnimation);

    // create looping buttons
    JPanel loopingPanel = new JPanel();
    loopingPanel.setLayout(new BoxLayout(loopingPanel, BoxLayout.Y_AXIS));
    JLabel loopingLabel = new JLabel("Looping settings:");
    ButtonGroup rGroup2 = new ButtonGroup();
    JToggleButton enableLooping = new JToggleButton("Looping enabled");
    JToggleButton disableLooping = new JToggleButton("Looping disabled");
    rGroup2.add(enableLooping);
    rGroup2.add(disableLooping);
    loopingPanel.add(loopingLabel);
    disableLooping.setSelected(true);
    enableLooping.setActionCommand("Enable Looping");
    disableLooping.setActionCommand("Disable Looping");
    enableLooping.addActionListener(new LoopingListener());
    disableLooping.addActionListener(new LoopingListener());
    loopingPanel.add(enableLooping);
    loopingPanel.add(disableLooping);

    // create speed buttons
    JPanel speedPanel = new JPanel();
    speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.Y_AXIS));
    JLabel speedLabel = new JLabel("Playback speed:");
    ButtonGroup rGroup3 = new ButtonGroup();
    JToggleButton increaseSpeed = new JToggleButton("3.00x");
    JToggleButton resetSpeed = new JToggleButton("Normal");
    JToggleButton decreaseSpeed = new JToggleButton("0.33x");
    rGroup3.add(increaseSpeed);
    rGroup3.add(resetSpeed);
    rGroup3.add(decreaseSpeed);
    speedPanel.add(speedLabel);
    resetSpeed.setSelected(true);
    increaseSpeed.setActionCommand("Increase Speed");
    decreaseSpeed.setActionCommand("Decrease Speed");
    resetSpeed.setActionCommand("Reset Speed");
    increaseSpeed.addActionListener(new SpeedListener());
    decreaseSpeed.addActionListener(new SpeedListener());
    resetSpeed.addActionListener(new SpeedListener());
    speedPanel.add(increaseSpeed);
    speedPanel.add(resetSpeed);
    speedPanel.add(decreaseSpeed);

    testPane.add(buttonPanel);
    testPane.add(loopingPanel);
    testPane.add(speedPanel);

    setVisible(true);

  }


  @Override
  public void printOutput() {
    return;
  }

  @Override
  public void fileOutput() {
    return;
  }

  @Override
  public void setCurrentFrame(List<Shape> los) {
    testPane.setCurrentFrame(los);
  }

  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public String stringOutput() {
    return null;
  }

  @Override
  public void setAction(String string) {
    this.currentAction = string;
  }

  @Override
  public String getAction() {
    return this.currentAction;
  }

  @Override
  public void setLoopingFlag(boolean flag) {
    this.loopingFlag = flag;
  }

  @Override
  public boolean getLoopingFlag() {
    return loopingFlag;
  }

  @Override
  public void setSpeedAction(String action) {
    this.speedAction = action;
  }

  @Override
  public String getSpeedAction() {
    return this.speedAction;
  }

  /**
   * A class that implements ActionListener, and is passed into the view's core buttons: Start,
   * Pause, Resume, and Restart. The overwritten method actionPerformed listens for button clicks
   * and executes the appropriate action based on those clicks (i.e., uses setAction to set the
   * appropriate action).
   */
  class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "Start Animation":
          setAction("start");
          break;
        case "Pause Animation":
          setAction("pause");
          break;
        case "Restart Animation":
          setAction("restart");
          setAction("start");
          break;
        case "Resume Animation":
          setAction("resume");
          break;
        default:
          break;
      }
    }
  }

  /**
   * A class that implements ActionListener, and is passed into the view's looping toggle buttons:
   * enable or disable looping. The overwritten method actionPerformed listens for button clicks and
   * executes the appropriate action based on those clicks (i.e., sets the looping flag).
   */
  class LoopingListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "Enable Looping":
          setLoopingFlag(true);
          break;
        case "Disable Looping":
          setLoopingFlag(false);
          break;
        default:
          break;
      }
    }
  }

  /**
   * A class that implements ActionListener, and is passed into the view's playback speed toggle
   * buttons: 3x speed, 1/3 speed, or reset to initial speed. The overwritten method actionPerformed
   * listens for button clicks and executes the appropriate action based on those clicks (i.e., sets
   * the speedAction).
   */
  class SpeedListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "Increase Speed":
          setSpeedAction("increase");
          break;
        case "Decrease Speed":
          setSpeedAction("decrease");
          break;
        case "Reset Speed":
          setSpeedAction("reset");
          break;
        default:
          break;
      }
    }
  }


}
