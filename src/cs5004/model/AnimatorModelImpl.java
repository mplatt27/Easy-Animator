package cs5004.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cs5004.animator.util.AnimationBuilder;

/**
 * This class represents the AnimatorModelImpl which implements the Animator Model.
 */

public class AnimatorModelImpl implements AnimatorModel {
  private Map<String, Shape> shapeMap;
  private List<Appendable> instructions;
  private Map<String, ArrayList<String>> introShapes;
  private List<String> order;
  private int windowX;
  private int windowY;
  private int windowWidth;
  private int windowHeight;
  private int startTick;
  private int endTick;

  /**
   * Initializes the shapeMap to a HashMap and the instructions to an ArrayList.
   */
  public AnimatorModelImpl() {
    this.shapeMap = new HashMap<>();
    this.instructions = new ArrayList<>();
    this.introShapes = new LinkedHashMap<>();
    this.order = new ArrayList<>();
  }

  @Override
  public List<String> getOrder() {
    return this.order;
  }

  @Override
  public int getWindowX() {
    return windowX;
  }

  @Override
  public int getWindowY() {
    return windowY;
  }

  @Override
  public int getWindowWidth() {
    return windowWidth;
  }

  @Override
  public int getWindowHeight() {
    return windowHeight;
  }

  @Override
  public Map<String, ArrayList<String>> getIntroShapes() {
    return this.introShapes;
  }

  @Override
  public int getEndTick() {
    return this.endTick;
  }

  @Override
  public void setEndTick(int tick) {
    this.endTick = tick;
  }

  @Override
  public void addShape(double color1, double color2, double color3,
                       String type, String name, double x, double y, double a, double b,
                       int startTime, int endTime) throws IllegalArgumentException {

    if (shapeMap.containsKey(name)) {
      throw new IllegalArgumentException("You already have a shape with that name");
    }

    StringBuilder temp = new StringBuilder();
    Color newColor = new Color(color1, color2, color3);
    if (type.equalsIgnoreCase("rectangle")) {
      Shape r = new Rectangle(x, y, a, b, name, startTime, endTime, newColor);
      shapeMap.put(name, r);
      temp.append(r.toString());
      temp.append("\n");
    } else if (type.equalsIgnoreCase("oval") || type.equalsIgnoreCase("ellipse")) {
      Shape o = new Oval(x, y, a, b, name, startTime, endTime, newColor);
      shapeMap.put(name, o);
      temp.append(o.toString());
      temp.append("\n");
    } else {
      throw new IllegalArgumentException("We don't support that shape. " +
              "Please choose rectangle or oval!");
    }
    temp.append("\n");
    instructions.add(temp);
  }

  @Override
  public void moveShape(String name, double fromX, double fromY, double toX, double toY,
                        int startTime, int endTime) throws IllegalArgumentException {

    if (conflictCheck("moves", name, startTime, endTime)) {
      throw new IllegalArgumentException("That shape is already moving at that time");
    }

    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Start and end time cannot be negative");
    }

    if (startTime > endTime) {
      throw new IllegalArgumentException("Start time must be before end time");
    }

    Shape target = shapeMap.get(name);
    if (target == null) {
      throw new IllegalArgumentException("Shape doesn't exist!");
    }

    if (fromX != target.getPoint().getX() || fromY != target.getPoint().getY()) {
      throw new IllegalArgumentException("Shape is not in that starting position!");
    }

    if (startTime < target.getStartTime() || (endTime > target.getEndTime())) {
      throw new IllegalArgumentException("Shape is not present at that time!");
    }

    StringBuilder temp = new StringBuilder();
    temp.append("Shape ").append(name).append(" moves from ")
            .append(target.getPoint().toString());

    target.changePoint(toX, toY);
    shapeMap.replace(name, target);

    temp.append(" to ").append(target.getPoint().toString())
            .append(String.format(" from t=%d to t=%d", startTime, endTime));
    temp.append("\n");
    instructions.add(temp);
  }

  @Override
  public void changeColor(String name, double toColor1, double toColor2, double toColor3,
                          int startTime, int endTime) throws IllegalArgumentException {

    if (conflictCheck("changes", name, startTime, endTime)) {
      throw new IllegalArgumentException("That shape is already changing color at that time");
    }

    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Start and end time cannot be negative");
    }

    if (startTime > endTime) {
      throw new IllegalArgumentException("Start time must be before end time");
    }

    Shape target = shapeMap.get(name);
    if (target == null) {
      throw new IllegalArgumentException("Shape doesn't exist!");
    }

    if (startTime < target.getStartTime() || (endTime > target.getEndTime())) {
      throw new IllegalArgumentException("Shape is not present at that time!");
    }

    StringBuilder temp = new StringBuilder();
    temp.append("Shape ").append(name).append(" changes color from ")
            .append(target.getColor().toString()).append(" to ");

    Color changedColor = new Color(toColor1, toColor2, toColor3);
    target.changeColor(changedColor);
    shapeMap.replace(name, target);

    temp.append(target.getColor().toString()).append(String.format(" from t=%d to t=%d",
            startTime, endTime));
    temp.append("\n");
    instructions.add(temp);
  }

  @Override
  public void changeSize(String name, double toValue1, double toValue2, int startTime,
                         int endTime) throws IllegalArgumentException {

    if (conflictCheck("scales", name, startTime, endTime)) {
      throw new IllegalArgumentException("That shape is already resizing at that time");
    }

    if (toValue1 <= 0 || toValue2 <= 0) {

      throw new IllegalArgumentException("Resizing values must be greater than 0!");
    }

    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Start and end time cannot be negative");
    }

    if (startTime > endTime) {
      throw new IllegalArgumentException("Start time must be before end time");
    }

    Shape target = shapeMap.get(name);
    if (target == null) {
      throw new IllegalArgumentException("Shape doesn't exist!");
    }

    if (startTime < target.getStartTime() || (endTime > target.getEndTime())) {
      throw new IllegalArgumentException("Shape is not present at that time!");
    }

    StringBuilder temp = new StringBuilder();
    if (target.getType().equalsIgnoreCase("Rectangle")) {
      temp.append("Shape ").append(name).append(String.format(" scales from Width: %.1f, " +
                      "Height: %.1f to Width: %.1f, Height: %.1f", ((Rectangle) target).getWidth(),
              ((Rectangle) target).getHeight(), toValue1, toValue2))
              .append(String.format(" from t=%d to t=%d", startTime, endTime)).append("\n");
    } else if (target.getType().equalsIgnoreCase("Oval")) {
      temp.append("Shape ").append(name).append(String.format(" scales from x radius: " +
                      "%.1f, y radius: %.1f to x radius: %.1f, y radius: %.1f",
              ((Oval) target).getRadius1(), ((Oval) target).getRadius2(), toValue1, toValue2))
              .append(String.format(" from t=%d to t=%d", startTime, endTime)).append("\n");
    }

    Shape newShape = target.resize(toValue1, toValue2);
    shapeMap.replace(name, newShape);
    instructions.add(temp);
  }

  /**
   * Checks if there are any conflicting actions already happening on the shape. For instance, if a
   * shape is already moving from time 10 to 20, move cannot be called on that shape again within
   * that time frame.
   *
   * @param action    the action that we are performing (move, changing color, or resizing).
   * @param name      the name of the shape.
   * @param startTime the start time for the function to execute.
   * @param endTime   the end time for the function to execute.
   * @return true if there is a conflict, false otherwise.
   */
  private boolean conflictCheck(String action, String name, int startTime, int endTime) {
    for (Appendable instruction : instructions) {
      if (instruction.toString().contains(action) && instruction.toString().contains(name)) {
        String[] parsedInstruction = instruction.toString().split(" ");
        Integer[] times = new Integer[2];

        int k = 0;
        for (String s : parsedInstruction) {
          if (s.contains("t=")) {
            String[] isolate = s.split("=");
            times[k] = Integer.parseInt(isolate[1].trim());
            k++;
          }
        }

        if (times[0] < startTime && startTime < times[1]) {
          return true;
        }

        if (endTime > times[0] && endTime < times[1]) {
          return true;
        }

      }
    }
    return false;
  }

  @Override
  public String declarativeAnimation() {

    StringBuilder temp = new StringBuilder();
    for (Appendable instruction : instructions) {
      temp.append(instruction.toString());
    }

    return temp.toString().trim();
  }

  @Override
  public List<Shape> tweening(int tick) {
    List<Shape> los = new ArrayList<>();

    Iterator iterator = introShapes.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry current = (Map.Entry) iterator.next();
      String name = (String) current.getKey();
      ArrayList array = (ArrayList) current.getValue();
      String type = array.get(0).toString();
      String attributes = array.get(1).toString();
      String[] attributeArray = attributes.split(" ");

      String lastAttributeString = array.get(array.size() - 1).toString();
      String[] lastAttributeArray = lastAttributeString.split(" ");

      int shapeEndTime = Integer.parseInt(lastAttributeArray[8]);
      int shapeStartTime = Integer.parseInt(attributeArray[0]);

      if (shapeStartTime <= tick && shapeEndTime >= tick) {
        int p;
        for (p = 0; p < array.size(); p++) {
          if (p > 0) {
            String[] currentArray = array.get(p).toString().split(" ");
            if (Integer.parseInt(currentArray[0]) <= tick &&
                    Integer.parseInt(currentArray[8]) >= tick) {
              double r1 = Double.parseDouble(currentArray[5]);
              double g1 = Double.parseDouble(currentArray[6]);
              double b1 = Double.parseDouble(currentArray[7]);
              double r2 = Double.parseDouble(currentArray[13]);
              double g2 = Double.parseDouble(currentArray[14]);
              double b2 = Double.parseDouble(currentArray[15]);
              double w1 = Double.parseDouble(currentArray[3]);
              double h1 = Double.parseDouble(currentArray[4]);
              double w2 = Double.parseDouble(currentArray[11]);
              double h2 = Double.parseDouble(currentArray[12]);
              double x1 = Double.parseDouble(currentArray[1]);
              double y1 = Double.parseDouble(currentArray[2]);
              double x2 = Double.parseDouble(currentArray[9]);
              double y2 = Double.parseDouble(currentArray[10]);
              int startTime = Integer.parseInt(currentArray[0]);
              int endTime = Integer.parseInt(currentArray[8]);


              double tickPositionX = tickFormula(x1, x2, startTime, endTime, tick);
              double tickPositionY = tickFormula(y1, y2, startTime, endTime, tick);

              double tickWidth = tickFormula(w1, w2, startTime, endTime, tick);
              double tickHeight = tickFormula(h1, h2, startTime, endTime, tick);

              double tickColor1 = Math.round(tickFormula(r1, r2, startTime, endTime, tick));
              double tickColor2 = Math.round(tickFormula(g1, g2, startTime, endTime, tick));
              double tickColor3 = Math.round(tickFormula(b1, b2, startTime, endTime, tick));

              if (type.equals("rectangle")) {
                los.add(new Rectangle(tickPositionX, tickPositionY, tickWidth,
                        tickHeight, name, shapeStartTime, shapeEndTime,
                        new Color(tickColor1, tickColor2, tickColor3)));
              }
              if (type.equals("oval") || type.equals("ellipse")) {
                los.add(new Oval(tickPositionX, tickPositionY, tickWidth,
                        tickHeight, name, shapeStartTime, shapeEndTime,
                        new Color(tickColor1, tickColor2, tickColor3)));
              }
            }
          }
        }
      }
    }

    return orderLOS(los);
  }

  /**
   * Computes the tweening formula on a given set of two values. Gets us the current value at the
   * given tick between a given start and end time.
   *
   * @param value1    the start value.
   * @param value2    the end value.
   * @param startTime the start time of the action.
   * @param endTime   the end time of the action.
   * @param tick      the tick in question between the start and end time. We want the value at this
   *                  tick.
   * @return the value of the object at the given tick.
   */
  private double tickFormula(double value1, double value2, int startTime,
                             int endTime, double tick) {
    return value1 * ((endTime - tick) / (endTime - startTime)) +
            value2 * ((tick - startTime) / (endTime - startTime));
  }

  /**
   * Sorts a list of shapes at a given tick by their start and end time.
   *
   * @param los a list of shapes and their status at a given tick.
   * @return the list sorted by their start time in the animation.
   */
  private List<Shape> orderLOS(List<Shape> los) {
    Collections.sort(los);
    return los;
  }

  /**
   * The Builder class is a static final class inside the model implementation. This class is used
   * in conjunction with the AnimationBuilder interface and AnimationReader class. The purpose of
   * these classes are to read a txt file with animation instructions and build a model. The
   * AnimationReader class parses through a txt file and gives that information to the Builder
   * (which implements the AnimationBuilder interface). This Builder than stores that data and uses
   * it to build an instance of the model.
   */
  public static final class Builder implements AnimationBuilder<AnimatorModel> {
    private AnimatorModelImpl animatorModel;

    /**
     * Constructs a Builder object by initializing a new model object.
     */
    public Builder() {
      animatorModel = new AnimatorModelImpl();
    }

    @Override
    public AnimatorModel build() {

      // appending shape declaration
      for (int k = 0; k < animatorModel.order.size(); k++) {
        String currName = animatorModel.order.get(k);
        ArrayList<String> currInstructArray = animatorModel.introShapes.get(currName);

        String type = currInstructArray.get(0);
        String attributes = currInstructArray.get(1);
        String[] attributeArray = attributes.split(" ");

        String lastAttributeString = currInstructArray.get(currInstructArray.size() - 1);
        String[] lastAttributeArray = lastAttributeString.split(" ");
        int lastTime = Integer.parseInt(lastAttributeArray[8]);

        // determine if this is the last tick in the whole animation
        if (lastTime > animatorModel.endTick) {
          animatorModel.setEndTick(lastTime);
        }

        animatorModel.addShape(Double.parseDouble(attributeArray[5]),
                Double.parseDouble(attributeArray[6]), Double.parseDouble(attributeArray[7]),
                type, currName,
                Double.parseDouble(attributeArray[1]), Double.parseDouble(attributeArray[2]),
                Double.parseDouble(attributeArray[3]), Double.parseDouble(attributeArray[4]),
                Integer.parseInt(attributeArray[0]), lastTime);
      }

      // append all instructions
      for (int l = 0; l < animatorModel.order.size(); l++) {
        String currName = animatorModel.order.get(l);
        ArrayList<String> currInstructArray = animatorModel.introShapes.get(currName);

        int j;
        for (j = 0; j < currInstructArray.size(); j++) {

          if (j > 1) {
            String motionAttributes = currInstructArray.get(j);
            String[] motionAttributesArray = motionAttributes.split(" ");
            double r1 = Double.parseDouble(motionAttributesArray[5]);
            double g1 = Double.parseDouble(motionAttributesArray[6]);
            double b1 = Double.parseDouble(motionAttributesArray[7]);
            double r2 = Double.parseDouble(motionAttributesArray[13]);
            double g2 = Double.parseDouble(motionAttributesArray[14]);
            double b2 = Double.parseDouble(motionAttributesArray[15]);
            double w1 = Double.parseDouble(motionAttributesArray[3]);
            double h1 = Double.parseDouble(motionAttributesArray[4]);
            double w2 = Double.parseDouble(motionAttributesArray[11]);
            double h2 = Double.parseDouble(motionAttributesArray[12]);
            double x1 = Double.parseDouble(motionAttributesArray[1]);
            double y1 = Double.parseDouble(motionAttributesArray[2]);
            double x2 = Double.parseDouble(motionAttributesArray[9]);
            double y2 = Double.parseDouble(motionAttributesArray[10]);
            int startTime = Integer.parseInt(motionAttributesArray[0]);
            int endTime = Integer.parseInt(motionAttributesArray[8]);

            if (x1 != x2 || y1 != y2) {
              animatorModel.moveShape(currName, x1, y1, x2, y2, startTime, endTime);
            } else if (w1 != w2 || h1 != h2) {
              animatorModel.changeSize(currName, w2, y2, startTime, endTime);
            } else if (r1 != r2 || g1 != g2 || b1 != b2) {
              animatorModel.changeColor(currName, r2, g2, b2, startTime, endTime);
            }
          }
        }

      }

      return animatorModel;
    }

    @Override
    public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
      animatorModel.windowX = x;
      animatorModel.windowY = y;
      animatorModel.windowWidth = width;
      animatorModel.windowHeight = height;
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type) {
      if (animatorModel.getIntroShapes().containsKey(name)) {
        return this;
      } else {
        animatorModel.getIntroShapes().put(name, new ArrayList<>());
        animatorModel.getIntroShapes().get(name).add(type);
        animatorModel.order.add(name);
      }
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                     int h1, int r1, int g1, int b1, int t2, int x2,
                                                     int y2, int w2, int h2, int
                                                             r2, int g2, int b2) {

      String data = String.format("%d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d",
              t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
      animatorModel.getIntroShapes().get(name).add(data);

      return this;
    }
  }

}

