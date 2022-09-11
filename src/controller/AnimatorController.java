package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.AnimationModel;
import model.AnimationModelImpl;
import util.AnimationBuilder;
import util.Builder;
import util.AnimationReader;
import view.View;
import view.ViewFactory;

/**
 * This class represents Animation Controller. It is used to control animations.
 * @author whitneycai
 *
 */
public class AnimatorController implements ActionListener, ItemListener {

  private final String[] args;
  private AnimationModel model;
  private View view;
  
  public AnimatorController(String[] args) {
    this.args = args;
  }
  
  /**
   * Start the animation.
   */
  public void start() {
    // create model
    model = newAnimation();
    
    // create frame
    JFrame frame = newFrame();
    
    // parse command line arguments
    StringBuilder parsedString = parsedCommandLine(args);
    
    // find input file
    String inputFile = inputFile(parsedString);
    
    // validate input file
    Readable file = checkInputFile(inputFile, frame);
    
    // create new build
    AnimationBuilder<AnimationModel> build = build(model);
    
    // fill model
    AnimationReader.parseFile(file, build);
    
    // get view type
    String viewType = findViewType(parsedString);
    
    // check view type
    checkViewType(viewType, frame);
    
    // get output file name
    String outputName = getOutPutFileName(parsedString);
    
    // get Output file speed
    int outputSpeed = getOutPutSpeed(parsedString);
    
    // check speed
    checkSpeed(outputSpeed, frame);
    
    // send command line arguments to view factory
    ViewFactory factory = new ViewFactory(viewType, model, outputName, outputSpeed);
    
    // construct view
    this.view = newView(factory);
    
    // create view
    createView(this.view, viewType);
    
    // pack and exit
    frame.pack();
  }
  
  /**
   * Create model.
   * @return new model.
   */
  private static AnimationModel newAnimation() {
    return new AnimationModelImpl();
  }
  
  /**
   * Create frame.
   * @return new frame.
   */
  public static JFrame newFrame() {
    JFrame frame = new JFrame("Frame");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    return frame;
  }
  
  /**
   * Create a string builder to parse command line arguments.
   * @param args arguments.
   * @return string builder containing parsed command lines.
   */
  private static StringBuilder parsedCommandLine(String[] args) {
    StringBuilder sb = new StringBuilder();
    for (String arg : args) {
      sb.append(arg);
      sb.append(" ");
    }
    return sb;
  }
  
  /**
   * Find input file name.
   * @param sb parsed command line.
   * @return name of input file.
   */
  private static String inputFile(StringBuilder sb) {
    Scanner in = new Scanner(sb.toString());
    in.findInLine("-in");
    return in.next();
  }
  
  /**
   * Set up the input file as Readable object and check if it can be found.
   * @param inputName name of file.
   * @param frame frame of animation.
   * @return Readable file.
   */
  public static Readable checkInputFile(String inputName, JFrame frame) {
    try {
      return new FileReader(inputName);
    } catch (FileNotFoundException e) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "Input file not found", "Input file error", 
              JOptionPane.ERROR_MESSAGE);
      System.out.println("Input file not found.");
      e.printStackTrace();
    }
    return null;
  }
  
  /**
   * Send model to Builder.
   * @param model model of animation.
   * @return animation builder.
   */
  public static AnimationBuilder<AnimationModel> build(AnimationModel model) {
    return new Builder(model);
  }
  
  /**
   * Create view.
   * @param view view of animation.
   * @param viewType type of view.
   */
  private void createView(View view, String viewType) {
    view.createView();
    
    if (viewType.equalsIgnoreCase("playback")) {
      view.setStartButtonListener(this);
      view.setStopButtonListener(this);
      view.setRestartButtonListener(this);
      view.setSlowerButtonListener(this);
      view.setFasterButtonListener(this);
      view.setLoopButtonListener(this);
      
    }
  }
  
  /**
   * Return view type.
   * @param sb string builder, the argument that's being viewed.
   * @return view type.
   */
  private static String findViewType(StringBuilder sb) {
    Scanner view = new Scanner(sb.toString());
    view.findInLine("-view");
    return view.next();
  }
  
  /**
   * Validate view type. It has to be one of the four views.
   * @param outputView view type to check.
   * @param frame frame of animation.
   */
  private static void checkViewType(String outputView, JFrame frame) {
    outputView = outputView.toLowerCase();
    if (outputView.equalsIgnoreCase("text") 
        && outputView.equalsIgnoreCase("visual")
        && outputView.equalsIgnoreCase("svg") 
        && outputView.equalsIgnoreCase("playback")) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "The View type must be text, visual, svg, or playback",
                      "Invalid view type", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /**
   * Return the output file name.
   * @param sb the argument being viewed.
   * @return the output file name.
   */
  private static String getOutPutFileName(StringBuilder sb) {
    Scanner out = new Scanner(sb.toString());
    String outFile = out.findInLine("-out");
    if (outFile == null || outFile.equals("")) {
      return "System.out";
    } else {
      return out.next();
    }
  }

  /**
   * Return animation speed.
   * @param sb string builder, current argument.
   * @return animation speed.
   */
  private static int getOutPutSpeed(StringBuilder sb) {
    Scanner outputSpeed = new Scanner(sb.toString());
    String stringSpeed = outputSpeed.findInLine("-speed");
    if (stringSpeed == null) {
      return 1;
    } else {
      return Integer.parseInt(outputSpeed.next());
    }
  }
  
  /**
   * Validate speed.
   * @param outputSpeed speed of animation.
   * @param frame frame for exception.
   */
  private static void checkSpeed(int outputSpeed, JFrame frame) {
    try {
      if (outputSpeed < 1) {
        throw new NumberFormatException();
      }
    } catch (NumberFormatException e) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "Speed much be a positive integer", 
              "Invalid speed", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /**
   * Construct view using view factory.
   * @param factory view factory.
   * @return the needed view.
   */
  private static View newView(ViewFactory factory) {
    View view = null;
    try {
      view = factory.create();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return view;
  }
  
  @Override
  public void itemStateChanged(ItemEvent e) {
    AnimationCommand c = new LoopAnimation();
    try {
      c.start(model, view);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    AnimationCommand c;
    
    String command = e.getActionCommand();
    if (command.equals("START")) {
      c = new StartAnimation();
    } else if (command.equals("STOP")) {
      c = new StopAnimation();
    } else if (command.equals("RESTART")) {
      c = new RestartAnimation();
    } else if (command.equals("FASTER")) {
      c = new FasterAnimation(view);
    } else if (command.equals("SLOWER")) {
      c = new SlowerAnimation(view);
    } else {
      c = null;
    }
    
    if (c != null) {
      try {
        c.start(model, view);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    
  }
  
  /**
   * Return model.
   * @return model.
   */
  public AnimationModel getModel() {
    return model;
  }
  
  /**
   * Return view.
   * @return view.
   */
  public View getView() {
    return view;
  }
  
}
