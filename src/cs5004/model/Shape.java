package cs5004.model;

/**
 * This interface contains all operations that all types of shapes should support.
 */
public interface Shape extends Comparable<Shape> {

  /**
   * Returns the time that the shape appears on the screen in an animation.
   *
   * @return the start time as int.
   */
  int getStartTime();

  /**
   * Returns the time that the shape disappears on the screen in an animation.
   *
   * @return the end time as int.
   */
  int getEndTime();

  /**
   * Returns the reference point for the shape (i.e., the center of a circle or lower left corner of
   * a rectangle.
   *
   * @return the reference point of a shape.
   */
  Point2D getPoint();

  /**
   * Returns the type of shape as a String.
   *
   * @return the type of shape as a String.
   */
  String getType();

  /**
   * Returns the Color of the shape.
   *
   * @return the Color of the shape.
   */
  Color getColor();

  /**
   * Returns the distance of this shape from the origin. The distance is measured from whatever
   * reference position a shape is (e.g. a center for a circle)
   *
   * @return the distance from the origin
   */
  double distanceFromOrigin();

  /**
   * Computes and returns the area of this shape.
   *
   * @return the area of the shape.
   */
  double area();

  /**
   * Computes and returns the perimeter of this shape.
   *
   * @return the perimeter of the shape.
   */
  double perimeter();

  /**
   * Create and return a shape of the same kind as this one, resized by the given values. Values
   * correspond to the type of shape (i.e., rectangle will be width and height in that order or an
   * oval will be the x radius and y radius in that order).
   *
   * @param newValue1 the first value to resize by (width, x radius, etc.)
   * @param newValue2 the second value to resize by (height, y radius, etc.)
   * @return the resized Shape.
   */
  Shape resize(double newValue1, double newValue2);

  /**
   * Resets the reference Point2D of the shape.
   *
   * @param x the new x value of the Point2D
   * @param y the new y value of the Point2D
   */
  void changePoint(double x, double y);

  /**
   * Resets the Color of the shape.
   *
   * @param toColor the new color of the shape.
   */
  void changeColor(Color toColor);

  /**
   * Compares a shape based on its startTime.
   *
   * @param shape a shape object to compare this to.
   * @return int representing comparison value.
   */
  int compareTo(Shape shape);
}
