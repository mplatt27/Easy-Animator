package cs5004.model;

/**
 * This class represents a rectangle. It defines all the operations mandated by the Shape
 * interface.
 */
public class Rectangle extends AbstractShape {
  private double width;
  private double height;

  /**
   * Constructs a rectangle object with the given location of its lower-left corner, dimensions,
   * name, start/end time, and color.
   *
   * @param x         x coordinate of the lower-left corner of this rectangle.
   * @param y         y coordinate of the lower-left corner of this rectangle.
   * @param width     width of this rectangle.
   * @param height    height of this rectangle.
   * @param name      a unique name for this shape to identify it by.
   * @param startTime the time that this shape appears on the screen.
   * @param endTime   the time that this shape disappears from the screen.
   * @param color     the color of this shape.
   */
  public Rectangle(double x, double y, double width, double height, String name, int startTime,
                   int endTime, Color color) throws IllegalArgumentException {

    super(new Point2D(x, y), name, startTime, endTime, color);

    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and Height must be greater than 0");
    }

    this.width = width;
    this.height = height;
    this.type = "rectangle";
  }

  /**
   * Returns the width of the rectangle.
   *
   * @return the width of the rectangle.
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Returns the height of the rectangle.
   *
   * @return the height of the rectangle.
   */
  public double getHeight() {
    return this.height;
  }

  @Override
  public double area() {
    return this.width * this.height;
  }

  @Override
  public double perimeter() {
    return 2 * (this.width + this.height);
  }

  @Override
  public Shape resize(double newWidth, double newHeight) throws IllegalArgumentException {
    if (newWidth <= 0 || newHeight <= 0) {
      throw new IllegalArgumentException("Width and Height must be greater than 0");
    }
    return new Rectangle(
            this.reference.getX(),
            this.reference.getY(), newWidth,
            newHeight, this.name, this.startTime, this.endTime, this.color);
  }

  /**
   * Returns the different attribute of the rectangle as as string in the form:
   * Name:
   * Type:
   * Min Corner/Center/Etc:
   * Appears at t=
   * Disappears at t=.
   *
   * @return a String to represent the Rectangle.
   */
  public String toString() {
    return "Name: " + name + "\nType: " + this.type + "\nMin corner: "
            + reference.toString() + ", Width: " + width + ", Height: " + height + ", Color: "
            + this.color + "\nAppears at t=" + startTime + "\nDisappears at t=" + endTime;
  }
}