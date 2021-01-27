***************************************************************************************************
*   CS 5004 Assignment 10                                                                         *
*   Easy Animator: Animation in Motion                                                            *
*   Melanie Platt and L.F                                                                  *
*   April 13, 2020                                                                                *
***************************************************************************************************
To run: Download jar file (in resources) and use the following command line arguments:
-in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go" -speed "integer-ticks-per-second"

-in (input) and -view are mandatory, -out will default to System.out and speed 1 tick per second

Overall description: This Easy Animator project utilizes the MCV project structure. We have designed
a model whose functionality is to store data for various 2D shapes and their motions in an animation,
such as movement on the screen, changing size, and changing color. We have designed four different
views including:

(1)   The visual view --> Displays the animation using a GUI; plays automatically once at start up.
(2)   The textual view --> Either prints out the animation as descriptive text or saves that text
as output in a txt file.
(3)   The SVG view --> Either prints out the svg code for the animation, or outputs an svg file
that plays the animation.
(4)   The playback view --> Displays the animation using a GUI and has functionality to start,
pause, resume, or restart the animation. Can also adjust playback speed and the animations ability
to loop continuously.

Finally, we have designed a controller to run the model and display the data as a view. The
controller takes in parsed (command line) input from the main function about what type of view
the user wants, what input file, what speed, and what output file they would like to use in the
animation. It then creates the model and the appropriate view, subsequently running the view using
the view methods. Below is a more detailed description of this MVC design.

*Note about testing*
The text and svg views are tested by feeding in a txt file that has the desired String output in it
(either svg code or textual description). This is then compared with the actual string that the
view produces (svg code or textual description). There is a test for each of the text files
(buildings, hanoi, etc.) that were provided with this assignment.

***************************************************************************************************
Part 1: The model
Model Interface: AnimatorModel
The interface provides all of the functionality data for the model. We have written various methods
for adding 2D shapes and their motions to the animation, such as moving on the screen, changing.
colors, or changing size. Each shape also has a start and end tick in the animation. The model also
allows the user to retrieve all shapes and their current state at any given tick. This functionality
was added during assignment 2, as it was needed for the visual view, which required knowing the state
of each shape at every frame (or tick of the Timer; see view description). The model also checks
for invalid input types, such as invalid x and y points, widths, heights, and colors, for shapes.
The model has the capability of preparing a string of all instructions in the animation.

Model Implementation: AnimatorModelImpl
In our first version of the model, we designed it to store all shapes and their names in a HashMap,
with the unique String names as keys. This map updated the state of the shape as motion or change
was made on the shape. We also had an ArrayList of all instructions as in order of their appearance,
stored as Strings. In our second version  (for assignment 8), we added more attributes to the model:

- The x, y, width, and height of the animation window
- The start and end tick of the animation (end tick is used in the Timer; see Controller).
- A LinkedHashMap called introShapes, which stores the data coming from text files, fed into the
AnimationBuilder to be used in building the model. This maps the name of the shape with an ArrayList
that stores each of that shape's instructions in order that they occur. This was initially meant to
be temporary storage, which was to be fed into the HashMap and ArrayList from above once the Builder
completed the model.However, we found that the LinkedHashMap was useful in allowing us to retrieve
data in our other views, so it has become a key data storage component of the model.
- A List<String> called order, which stores the names of all shapes in the order they are added to
the animation. In creating our LinkedHashMap, introShapes, above, we intially used a simple Hashmap,
which did not maintain order of the shapes as they were added to the animation. For this reason, we
had a second ArrayList that held the shape names in order. We later moved to the LinkedHashMap to
maintain order that way, but this order list could still be useful. It stores less data than the
LinkedHashMap, so if a future view only needed shape order, this ArrayList could be used.

The methods are described below:

void addShape(double color1, double color2, double color3, String type, String name, double x,
double y, double a, double b, int startTime, int endTime)

    - The addShape() method is called when a user wants to create a 2D shape in their animation.
	This method takes in the color of the shape (represented by three rgb numbers; see below), a
	String that represents the type of shape, a String that represents a unique name for the shape,
	and x and y coordinate for the location of the shape, two values (a and b) that differ
	depending on the type of shape (for rectangle this is width and height, for oval this is x
	radius and y radius), and finally a start and end time for when the shape is to appear and
	disappear on the screen. This method throws an IllegalArgumentException when invalid arguments
	are passed in. Finally, this method appends the instruction to a list of instructions held
	by the model. 

void moveShape(String name, double fromX, double fromY, double toX, double toY,
int startTime, int endTime)

    - The moveShape() method is called when a user wants to move an existing shape at a given time,
    to a new given location. This method takes in the name of the shape that the user wants to
    move, its current location, its new desired location, and the time that the user wants the
    movement to take place. This method throws an IllegalArgumentException if any invalid
    parameters are passed in. Finally, this method appends the instruction to a list of
    instructions held by the model.

void changeColor(String name, double toColor1, double toColor2, double toColor3,
int startTime, int endTime)
    
    - The changeColor() method is called when the user wants to change the color of an existing
    shape. This method takes in the name of the Shape that the user wants to edit, the color that
    they want to change it to as 3 ints, and the start and end time that they want this to occur.
    Finally, this method appends the instruction to a list of instructions held by the model.

void changeSize(String name, double toValue1, double toValue2, int startTime, int endTime)
    
    - The changeSize() method is called when the user wants to change the size of an existing
    Shape. This method takes in the name of the Shape that the user wants to edit, two values that
    the user wants to change the size too (which will be processed as width and height if the
    Shape is a rectangle, or x radius and y radius if the Shape is an oval, and lastly the start
    and end time that the user wants the size change to occur. Finally, this method appends
	the instruction to a list of instructions held by the model.

String declarativeAnimation();

    - This method returns a text description of each instruction that is included in the
    animation that the user creates.

private boolean conflictCheck();

    - Checks if there are any conflicting actions already happening on the shape. For instance, if a
    shape is already moving from time 10 to 20, move cannot be called on that shape again within
    that time frame. Returns true if there is a conflict, false otherwise.

List<String> getOrder();

    - returns the list of shape names as Strings in the order they are added to the animation

int getWindowX();

    - getter for x value of the animation window

int getWindowY();

    - getter for y value of the animation window

int getWindowWidth();

    - getter for width value of the animation window

int getWindowHeight();

    - getter for height value of the animation window

Map<String, ArrayList<String>> getIntroShapes();

    - getter for the introShapes LinkedHashMap that stores data to use in building the model

int getEndTick();
 
    - getter for the last tick in the animation

void setEndTick();

    - setter for the animation tick once it is found

List<Shape> tweening(int tick);

    - Loops through all shapes and their instructions in the model, and returns a list of 
Shape object at their current state at a given tick (current state includes the Shape's type,
x,y, width, height, color, start time, and end time in the animation). This method was added
for the visual view, which then renders the animation as it looks at any given tick.

double tickFormula(double value1, double value2, int startTime, int endTime, double tick);

    - Private method that computes "tweening" for any given value at a given tick. That is, this method
    returns the current value that a shape is at, at any given tick in between the time of the action.
    (Example: If a Shape is moving from x=10 to x=20 from t=0 to t=10, this formula computes the the
    x value at any given tick in between 0 and 10).

List<Shape> orderLOS(List<Shape> los);

   - Private method that sorts the list of shapes at a given tick by their start time in the animation.
This ended up not being used in our final version of this assignment, but we have kept in in case it
can be useful in a future version.

Supporting Classes to the Model:
The model currently supports two shapes through the use of a Shape interface. This
interface was adopted from the Shape interface used in Lecture 3 and holds each of the methods
that a Shape can perform. This is implemented by the Rectangle and Oval class. In future versions,
we plan to make more shapes available. We recognize that this may require some refactoring (i.e.,
a triangle does not have a height/width or x radius/y radius). Many of the Shape methods are also
abstracted in AbstractShape.

Finally, each of the Shapes holds a color and x,y coordinate point that the shape sits at, which
are represented by their own Color class and Point2D class, respectively. The Color class holds
three ints that describe the color's RGB numbers. The Point2D class has an x and y coordinate.

The methods for these supporting classes listed below. More details can be found in the
Javadoc comments for each class.

Shape Interface:
int getStartTime()
int getEndTime()
Point2D getPoint()
String getType()
Color getColor()
double distanceFromOrigin()
double area()
double perimeter()
Shape resize()
void changePoint()
void changeColor()
int compareTo(Shape shape);

Color class:
String toString()
int getValue1()
int getValue2()
int getValue3()

Point2D class:
double disToOrigin()
double getX()
double getY()
String toString()

The Builder class:
The Builder class is a static final class inside the model implementation. This class implements
the AnimationBuilder interface and AnimationReader class. The purpose of these classes are to read
a txt file with animation instructions and build a model. The AnimationReader class parses through
a txt file and gives that information to the Builder. This Builder than stores that data and uses it
to build an instance of the model. The methods of the Builder are as follows:

AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height)

    - Sets the x, y, width, height of the animation in the model being built

AnimationBuilder<AnimatorModel> declareShape(String name, String type)

    - Adds the shapes as they are fed into the Builder into storage in a
LinkedHashMap with the shape name and an empty array of its instructions. Also adds the shame name
to a list which will hold the order the shapes are fed into the Builder (again, more useful before
we switched to a LinkedHashMap).

AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                     int h1, int r1, int g1, int b1, int t2, int x2,
                                                     int y2, int w2, int h2, int r2, int g2, int b2)

    - Adds an instruction to the temporary LinkedHashMap but finding the shape name value, and
appending the instruction to the corresponding ArrayList holding all instructions for that
shape.

AnimatorModel build();

Takes the order list and LinkedHashMap of shapes/instructions and creates a full model
using the data. 

***************************************************************************************************
Part 2: The view
This project supports four different views: textual, SVG, visual, and playback.

View interface: AnimatorView
The view holds all of the functionality of all three few types. Some methods may not be
relevant to all views, so they are given default return values. The methods are as follows:

printOutput();
fileOutput();
setCurrentFrame();
refresh();
stringOutput();
getLoopingFlag();
setLoopingFlag(boolean flag);
setAction(String string);
getAction();
setSpeedAction(String action);
getSpeedAction();

View implementation:

AnimatorViewTextual:
This view takes in an instance of the model and the speed of the animation. Although
the speed is not relevant so much to the textual view, a user may want to know what
speed the animation is run at by looking at the text in order to better visualize it.
The relevant methods are below:

void stringOutput();

   - This method retrieves a String containing the full animation instructions from the model.  

void printOutput();

   - This method prints the string containing the full animation instructions. 

void fileOutput();

   - This method creates a txt file including the full text of the animation instructions. 


AnimatorViewSVG:
This view takes in an instance of the model and the speed that the animation will
run at. It then implements a method to transfer each instruction into SVG code
and either print that out to the user or output it as an svg file. The relevant
methods are below:

String stringOutput()

    - This method loops through each shape in the model and then loops through
each of that shapes instructions. While doing this, it converts those instructions
into a String of SVG code that will represent the instruction. Once finished, it 
returns the entire SVG code as a String which can be printed out, or output
to a file later if needed.

void printOutput();

   - This method prints the string containing the SVG animation code. 

void fileOutput();

    - This method outputs the String returned from stringOutput() in an svg file.

AnimatorViewVisual:
This view is extends JFrame and is initialized with a JFrame given the
x, y, width, and height values for the animation. It also holds a TestPane object,
which is a class we created that extends JPanel. All animation drawing is done on
this TestPane. The relevant methods are listed below:

void setCurrentFrame(List<Shape> los);

    - This method calls another setCurrentFrame() method on the TestPane to draw
the animation at the current tick (see below in TestPane description).

void refresh();

    - This method calls refresh on the frame. 

TestPane supporting class:
TestPane is a static class inside the AnimatorViewVisual class that extends
JPanel. This class has an empty constructor and holds a List<Shape> representing
all shapes at a given tick. The methods are listed below:

void setCurrentFrame(List<Shape> los);

    - This method takes in a List<Shape> which will set the current list of shapes at a
give tick.

void paintComponent(Graphics g);

    - TestPane overrides this method to put a Graphics object on the current panel. This 
method loops through the current list of shapes and paints those shapes as they are at the
given tick on the panel.

AnimatorViewPlayback:
This view extends JFrame and has a TestPane object, like the visual view, that holds the pane
that plays the animation. The JFrame has several buttons, the allow the user to start, pause, resume,
or restart an animation. There are also toggle buttons for playback speed and enabling/disabling
the looping feature. Additionally, this class holds a String called currentAction, which refers
to the action the user has indicated in their latest button push, a boolean flag called loopingFlag,
which indicates if the user has enabled (true) or disabled (false) looping, and a String called
speedAction, which indicates if the user has decided to play the animation at 3x speed 1/3 speed,
or the initial speed indicated in the command line arguments. The playback view implements the
same methods as the visual view, in addition to the methods below:

getLoopingFlag();

    - Gets the boolean value that the looping flag is currently set to (true if looping is enabled,
    false otherwise). The flag is initialized in the view as false.

setLoopingFlag(boolean flag);

    - Sets the looping flag as true if the user has enabled looping, false otherwise.

setAction(String string);

    - Sets the appropriate String that represents the action the user has indicated (either start,
    pause, resume, or restart).

getAction();

    - Gets the String value representing the action the user has indicated (either start,
    pause, resume, or restart).

setSpeedAction(String action);

    - Sets the playback speed that the user has indicated (either regular speed that was passed in
    as a command line argument, 3x that speed or 1/3 times that speed.

getSpeedAction();

    - Gets the playback speed that the user has indicated (either regular speed that was passed in
    as a command line argument, 3x that speed or 1/3 times that speed.

Supporting classes:
ButtonListener -- A class that implements ActionListener, and is passed into the view's core buttons:
Start, Pause, Resume, and Restart. The overwritten method actionPerformed listens for button
clicks and executes the appropriate action based on those clicks (i.e., uses setAction to set the
appropriate action).

LoopingListener -- A class that implements ActionListener, and is passed into the view's looping
toggle buttons: enable or disable looping. The overwritten method actionPerformed listens for button
clicks and executes the appropriate action based on those clicks (i.e., sets the looping flag).

SpeedListener -- A class that implements ActionListener, and is passed into the view's playback speed
toggle buttons: 3x speed, 1/3 speed, or reset to initial speed. The overwritten method
actionPerformed listens for button clicks and executes the appropriate action based on those clicks
(i.e., sets the speedAction).

***************************************************************************************************
The EasyAnimator class and the Controller:
The EasyAnimator class holds the main method, parses the command line input, and passes that to the
Controller to run the animation.

The EasyAnimator class:
public static void main(String[] args)

    - This is the main function that starts the program. It takes in a set of parameters
from the command line:

-in (name of txt file)
-out (either System.out, out.svg, out.txt, or empty)
-speed (number of ticks per second)
-view (visual, text, or svg)

These parameters can come in any order, but must have the designated keyword before them.
The txt file name and view type are required, the speed and out values are optional. If
these are left out, the default speed is 1 and out is System.out.

The main method then parses these command line arguments and sets them as variables. It creates an
instance of the controller, passing the variables as arguments. It then calls the Controller's
displayView() method (see below) which executes the appropriate animation.

The Controller:
The purpose of the controller is to user the model's data and execute the appropriate animation
that the user has indicated. This means calling the appropriate view methods to print or save output
to a file (for text and svg view), or running the animation as a GUI for the visual and playback
views. The Controller creates a Timer that manges the playing of the animation itself.
In the playback view, the Controller also continuously calls for the state of the view's attributes
(buttons, looping flags, etc.), and calls the appropriate methods to perform tasks.

Flow of events in the Controller:

(1)  Based on the fileName parameter for the input file, a txt file is passed into the AnimationReader,
parsed, and returned as a fully built model.
(2) Based on the viewType parameter, the appropriate view is created.
(3) An action is taken to run the view:

    Textual view --> The Controller either calls printOutput() to print the text of the animation,
    or fileOutput() create and save a file with the text of the animation in a txt file.

    SVG view --> The Controller either calls printOutput() to print the svg code of the animation,
    or fileOutput() create and save a file with the svg code of the animation in a svg file.

    Visual view --> A Timer object is created that will determine the pace of the
    animation, and tell the program when to render each frame. Passed into this Timer is an
    instance of the MyAnimationFrameHandler class. This is a public class in the
    EasyAnimator that extends ActionListener. This class holds the current tick, and an
    instance of the model and view. In the constructor, the AnimatorViewVisual instance
    is created, the model that was created in main is passed in, and the current tick
    is initialized at 0. This class overrides the actionPerformed() method, which controls
    what happens at each tick of the Timer:

        void actionPerformed(ActionEvent actionEvent);

        - Method that dictates what happens at each tick of the Timer. First, the
        setCurrentFrame() method is called in the view; passed into this is the list of shapes
        at the current tick that we are on (retrieved by calling the tweening method on the model,
        passing in the current tick). This method consequently calls setCurrentFrame() on the
        panel in the view, which gives the list of shapes to the panel and draws them (using the
        paintComponent() method. Next, the refresh() method is called on the view, which gets
        the frame ready for the next tick. Finally, the current tick is incremented by 1.

    Once the Timer is started, the entire animation is played from start to finish.

    Playback view --> The playback view also begins by creating a timer, but the timer is not
    started right away. Instead the controller uses a while loop to continuously get the action
    that the user has indicated by pressing a button. If the user has pressed start, the Controller
    tells the timer to start; if the user has pressed pause, the Controller tells the timer to
    pause. Likewise, the restart and resume buttons work the same way. Inside the actionPerformed()
    method of the timer, there is a check for the playback view's looping flag. If looping has been
    enabled, then the timer will not stop once the animation reaches the last tick, but set the
    current tick back to 0 and restart from the beginning. Finally, the while loop in the Controller
    also checks for the playback speed settings. If user has increased or decreased the speed,
    the Controller will reset that speed within the timer. Once the user exits out of the JFrame
    GUI window, the while loop breaks and the program ends.





