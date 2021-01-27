package cs5004.model;

/**
 * This class represents an RGB color by its 3 double values.
 */

public class Color {
  private double value1;
  private double value2;
  private double value3;

  /**
   * Initializes a Color object by its given 3 values.
   *
   * @param value1 the first number of the RGB sequence.
   * @param value2 the second number of the RGB sequence
   * @param value3 the third number of the RGB sequence
   * @throws IllegalArgumentException if any of the numbers given are below zero or greater than
   *                                  255.
   */
  public Color(double value1, double value2, double value3) throws IllegalArgumentException {
    if (value1 < 0 || value1 > 255 || value2 < 0 || value2 > 255 || value3 < 0 || value3 > 255) {
      throw new IllegalArgumentException("invalid color choice");
    }
    this.value1 = value1;
    this.value2 = value2;
    this.value3 = value3;
  }

  public int getColor1() {
    return (int) this.value1;
  }

  public int getColor2() {
    return (int) this.value2;
  }

  public int getColor3() {
    return (int) this.value3;
  }

  @Override
  public String toString() {
    return "(" + value1 + "," + value2 + "," + value3 + ")";
  }
}
