package cs5004.model;

/**
 * This class represents an oval. It offers all the operations mandated by the Shape interface.
 */
public class Oval extends AbstractShape {
  private double radius1;
  private double radius2;

  /**
   * Construct an oval object using the given center, x radius, y radius, name, start/end time, and
   * color.
   *
   * @param x         x coordinate of the center of this oval.
   * @param y         y coordinate of the center of this oval.
   * @param radius1   the x radius of this oval.
   * @param radius2   the y radius of this oval.
   * @param name      a unique name to identify this oval by.
   * @param startTime the time that this oval appears on the screen.
   * @param endTime   the time that this oval disappears from the screen.
   * @param color     the color of this oval.
   */
  public Oval(double x, double y, double radius1, double radius2, String name, int startTime,
              int endTime, Color color) throws IllegalArgumentException {

    super(new Point2D(x, y), name, startTime, endTime, color);

    if (radius1 <= 0 || radius2 <= 0) {
      throw new IllegalArgumentException("X Radius and Y radius must be greater than 0");
    }
    this.radius1 = radius1;
    this.radius2 = radius2;
    this.type = "oval";
  }

  /**
   * Construct an oval object with the given x radius, y radius, name, start/end time, and color. It
   * is centered at (0,0).
   *
   * @param radius1   the x radius of this oval.
   * @param radius2   the y radius of this oval.
   * @param name      a unique name to identify this oval by.
   * @param startTime the time that this oval appears on the screen.
   * @param endTime   the time that this oval disappears from the screen.
   * @param color     the color of this oval.
   */
  public Oval(double radius1, double radius2, String name, int startTime,
              int endTime, Color color) throws IllegalArgumentException {

    super(new Point2D(0, 0), name, startTime, endTime, color);

    if (radius1 <= 0 || radius2 <= 0) {
      throw new IllegalArgumentException("X Radius and Y radius must be greater than 0");
    }
    this.radius1 = radius1;
    this.radius2 = radius2;
    this.type = "oval";
  }

  /**
   * Returns the x radius of the oval.
   *
   * @return the x radius of the oval.
   */
  public double getRadius1() {
    return this.radius1;
  }

  /**
   * Returns the y radius of the oval.
   *
   * @return the y radius of the oval.
   */
  public double getRadius2() {
    return this.radius2;
  }

  @Override
  public double area() {
    return Math.PI * radius1 * radius2;
  }

  @Override
  public double perimeter() {
    return 2 * Math.PI * Math.sqrt((Math.pow(radius1, 2) + Math.pow(radius2, 2)) / 2);
  }

  @Override
  public Shape resize(double newRadius1, double newRadius2) {
    if (newRadius1 <= 0 || newRadius2 <= 0) {
      throw new IllegalArgumentException("X Radius and Y Radius must be greater than 0");
    }

    return new Oval(reference.getX(), reference.getY(),
            newRadius1, newRadius2, name, startTime, endTime, color);
  }

  /**
   * Returns the different attribute of the oval as as string in the form:
   * Name:
   * Type:
   * Min Corner/Center/Etc:
   * Appears at t=
   * Disappears at t=.
   *
   * @return a String to represent the Oval.
   */
  public String toString() {
    return "Name: " + name + "\nType: " + this.type + "\nCenter: " + reference.toString()
            + ", x radius: " + radius1 + ", y radius: " + radius2 + ", Color: " + color
            + "\nAppears at t=" + startTime + "\nDisappears at t=" + endTime;
  }
}