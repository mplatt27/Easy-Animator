package cs5004.model;

/**
 * This is an abstract class that implements the Shape interface. There are several classes that
 * extend this abstract shape class such as Rectangle and Oval.
 */
public abstract class AbstractShape implements Shape, Comparable<Shape> {
  protected Point2D reference;
  protected String name;
  protected int startTime;
  protected int endTime;
  protected Color color;
  protected String type;

  /**
   * Constructor for AbstractShape.
   */
  public AbstractShape(Point2D reference, String name, int startTime, int endTime, Color color)
          throws IllegalArgumentException {
    this.reference = reference;
    this.name = name;

    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Start and end time cannot be negative");
    }

    if (startTime > endTime) {
      throw new IllegalArgumentException("Start time must be before end time");
    }

    this.startTime = startTime;
    this.endTime = endTime;
    this.color = color;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public Point2D getPoint() {
    return reference;
  }

  @Override
  public int getStartTime() {
    return startTime;
  }

  @Override
  public int getEndTime() {
    return endTime;
  }

  @Override
  public void changeColor(Color toColor) {
    this.color = toColor;
  }

  @Override
  public double distanceFromOrigin() {
    return reference.distToOrigin();
  }

  @Override
  public void changePoint(double x, double y) {
    this.reference = new Point2D(x, y);
  }

  @Override
  public int compareTo(Shape s) {
    Integer thisValue = startTime;
    Integer sValue = s.getStartTime();
    return thisValue.compareTo(sValue);
  }
}