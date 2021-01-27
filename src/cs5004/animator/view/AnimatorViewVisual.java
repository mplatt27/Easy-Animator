package cs5004.animator.view;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cs5004.model.AnimatorModelImpl;
import cs5004.model.Shape;

/**
 * This class represents the AnimatorViewVisual which extends JFrame and implements
 * the AnimatorView. This view displays the animation using a GUI.
 */
public class AnimatorViewVisual extends JFrame implements AnimatorView {
  private TestPane tp;

  /**
   * Constructs an AnimatorViewVisual object with the given model.
   *
   * @param model an instance of the model.
   */
  public AnimatorViewVisual(AnimatorModelImpl model) {
    super("My Animation");

    setSize(model.getWindowWidth(), model.getWindowHeight());
    setLocation(model.getWindowX(), model.getWindowY());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.tp = new TestPane();
    tp.setPreferredSize(new Dimension(model.getWindowWidth(), model.getWindowHeight()));
    add(tp);
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
  public String stringOutput() {
    return null;
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
   *  This method calls another setCurrentFrame() method on the TestPane to draw
   * the animation at the current tick.
   *
   * @param los the list of shapes and their state at the current tick.
   */
  public void setCurrentFrame(List<Shape> los) {
    tp.setCurrentFrame(los);
  }

  public void refresh() {
    repaint();
  }


  /**
   * This class represents the TestPane which extends JPanel. All animation drawing is done on
   * this TestPane.
   */
  public static class TestPane extends JPanel {
    private List<Shape> los;

    public TestPane() {
       // Constructs an empty TestPane to be drawn on.
    }

    /**
     * This method takes in a List<@Shape> which will set the current list of shapes at a
     * give tick.
     *
     * @param los the list of shapes and their state at the current tick.
     */
    void setCurrentFrame(List<Shape> los) {
      this.los = los;
    }

    /**
     * TestPane overrides this method to put a Graphics object on the current panel. This
     * method loops through the current list of shapes and paints those shapes as they are at the
     * given tick on the panel.
     *
     * @param g the Graphics object on the current panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;

      if (los == null) {
        return;
      }

      // loop through shapes at the current frame and draw them on the panel
      for (Shape s : los) {

        if (s.getType().equals("rectangle")) {

          cs5004.model.Rectangle currentShape = (cs5004.model.Rectangle) s;
          int pointX = (int) currentShape.getPoint().getX();
          int pointY = (int) currentShape.getPoint().getY();
          int width = (int) currentShape.getWidth();
          int height = (int) currentShape.getHeight();

          g2.setColor(new Color(currentShape.getColor().getColor1(),
                  currentShape.getColor().getColor2(),
                  currentShape.getColor().getColor3()));
          g2.fillRect(pointX, pointY, width, height);

        } else if (s.getType().equals("oval") || s.getType().equals("ellipse")) {
          cs5004.model.Oval currentShape = (cs5004.model.Oval) s;
          int pointX = (int) currentShape.getPoint().getX();
          int pointY = (int) currentShape.getPoint().getY();
          int r1 = (int) currentShape.getRadius1();
          int r2 = (int) currentShape.getRadius2();

          g2.setColor(new Color(currentShape.getColor().getColor1(),
                  currentShape.getColor().getColor2(),
                  currentShape.getColor().getColor3()));
          g2.fillOval(pointX, pointY, r1, r2);
        }
      }
    }

  }

}

