The Easy Animator
-	This program is built to help creating simple but effective 2D animations from shapes. It 
draws an animation according to how it is described in text, without being tied to how 
the description was produced. 

EasyAnimator
-	The EasyAnimator class holds main, where the programs are run. It takes in arguments 
from text file and create animations.

Part 1 – Model
Shape
-	The Shape interface includes methods that all kinds of shape objects should support. 
This interface was designed to represent shapes, which will be used later in the 
animator.

AbstractShape
-	The AbstractShape class is an abstract class that implements the Shape interface. It is 
created to help reducing repeated codes and make the design cleaner.

Rectangle
-	The Rectangle class extends AbstractShape class. It constructs a rectangle shape. The 
first constructor takes in a name string, dimension values (width and height), color 
values (red, green, and blue), and location values (x and y). The second constructor 
takes in only a name string.

Oval (or Ellipse)
-	The Oval class extends AbstractShape class. It constructs an oval (or ellipse) object. The 
first constructor takes in a name string, dimension values (width and height), color 
values (red, green, and blue), and location values (x and y). The second constructor 
takes in only a name string.

Event
-	The Event interface includes methods that all kinds of event objects should support. This 
interface was designed to represent events, which will be used later in animator.

AbstractEvent
-	The AbstractEvent class is an abstract class that implements the Event interface. It helps 
reusing common codes that all types of event objects share.

MoveShape
-	The MoveShape event extends AbstractEvent class. It constructs a moving event on a 
shape. The constructor takes in a shape, coordinates for the original location (x and y), 
and coordinates for the new location (x and y).

ScaleShape
-	The ScaleShape event extends AbstractEvent class. It constructs a scaling event on a 
shape. The constructor takes in a shape, the original dimension of the shape (width and 
height), and new dimension of the shape (width and height).

AnimationModel
-	The AnimationModel interface includes methods that should be implemented in 
AnimationModelImpl. It represents an animation. 

AnimationModelImpl
-	The AnimationModelImpl implements the AnimationModel interface. It generates an 
animation model. An animation includes two types of objects: shape and event. Each 
shape has a unique name. If the event types are the same, the animation doesn’t allow 
overlap in time.
-	addShape(Shape shape, int appearTime, int disappearTime) – add shape to animation.
-	getShape(String name) – get shape based on given shape name.
-	getShapeMap() – return map of shapes and it’s event list.
-	addEvent(Shape shape, Event event, int begin, int End) – add event to given shape.
-	getEventList(Shape shape) – return list of event of the given shape.
-	getShapeAtTick(int tick) – return list of shape at the given tick.
-	Setters and getters for bound and animation times (begin tick and end tick)

Util
Builder
-	The builder class implements the methods of AnimationBuilder interface. It constructs an 
AnimationBuilder.

Part 2 – View
View
-	The View interface includes methods that all kinds of view objects should support.
-	createView() – creates a view.

VisualView
-	The VisualView class extends JFrame and implements View interface and ActionListner 
interface. It outputs a visual display of the animation using JFrame Swing. This view 
reads the given text file and produce animation based on the file.
-	createView() – create visual view.
-	drawShapes(int tick) – draw shapes at given tick.

TextView
-	The TextView class implements View interface. It produces a textual description of the 
animation.
-	createView() – create textual view.
-	buildTextString() – convert the given shapes and their events as Strings and add to 
StringBuilder, which will be output as text file.
-	getViewState() – return the StringBuilder, showing the current state of the view.

SVGView
-	The SVGView class implements View interface. It produces a textual description of the 
animation that is compliant with SVG file format.
-	createView() – create a SVG view animation.
-	buildSVGString() – convert the given shapes and their events to SVG-format strings and 
add to StringBuilder, which will be output as text file.
-	getViewState() – return the current state of the view.
-	rectangleString() – return a SVG format string representing a rectangle.
-	ellipseString() – return a SVG format string representing a ellipse/oval.
-	eventString() – add shape events to StringBuilder.
-	moveString(Shape e, MoveShape e) – return a SVG format string representing a move 
event.
-	scaleString(Shape e, ScaleShape e) – return a SVG format string representing a scale 
event.
-	recolorString(Shape e, RecolorShape e) – return a SVG format string representing a 
recolor event.

PlaybackView
-	The PlaybackView extends the VisualView and adds new features. It outputs a visual 
display of the animation and includes new buttons to control the animation.
-	Start button – to begin or resume animation
-	Stop button – to pause the animation
-	Restart button – to rewind the animation and start from beginning
-	Speed ++ button – to increase the speed of animation
-	Speed – button – to decrease the speed of animation
-	Loop check box – to enable or disable looping animation.

AnimationPanel
-	The AnimationPanel class extends JPanel. It is a container that stores group of 
components.
-	paintComponent(Graphics g) – it helps drawing the rectangle and ovals (for visual and 
playback view)

ViewFactory
-	The ViewFactory class is a factory of views, with a single static method that takes in a 
String name for a view (text, visual, svg, or playback) and constructs an instance of the 
appropriate concrete view.

Part 3 – Controller
AnimationCommand
-	The AnimationCommand interface is called when an action is performed on the 
PlaybackView. Based on the action performed, it calls different command classes to 
handle the action.
-	StartAnimation (start button)
-	StopAnimation (stop button)
-	RestartAnimation (restart button)
-	FasterAnimation (speed ++ button)
-	SlowerAnimation (speed -- button)
-	LoopAnimation (loop check box)

AnimationController
-	The AnimationController class construct an animation. It takes in a readable object and 
decide which view to output, and send it to the correct view. 

Helper classes
Color
-	The Color class is used to represent a RGB color. It takes in three color values (red, green, 
and blue).
Point2D
-	The Point2D class is used to represent a location coordinate. It takes in two coordinate 
values (x and y).

Enums
ShapeType
-	The ShapeType enum stores a set of shape types. It includes rectangle, ellipse, and oval. 
Ellipse and oval are treated the same in this EasyAnimator project.
EventType
-	The EventType enum stores a set of event types. It includes moving shape, scaling shape, 
and changing color of a shape.
Actions
-	The Actions enum stores a set of actions for EasyAnimator. It includes start, stop (pause), 
restart, slower, faster, and loop.
