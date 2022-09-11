package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.Timer;

import controller.AnimationCommand;
import controller.LoopAnimation;
import model.AnimationModel;

/**
 * This class represents Playback view. 
 * It contains the existing visual view as a component within it, and add the following abilities:
 * Start, pause, resume and restart the animation with explicit input (button click).
 * Enable and disable looping.
 * Increase or decrease the speed of animation.
 * @author whitneycai
 *
 */
public class PlaybackView extends VisualView implements ActionListener {
  
  private JPanel playbackFrame;
  private ButtonGroup buttonGroup;
  private GridBagLayout playbackLayout;
  private GridBagConstraints layoutConstraints;
  
  private JToggleButton startButton;
  private JToggleButton stopButton;
  private JToggleButton restartButton;
  
  private JCheckBox loopCheckBox;
  
  private JButton fasterButton;
  private JButton slowerButton;
  
  private JLabel speedNumber;
  private JLabel speedLabel;
  
  
  /**
   * Construct a Playback view with given model and speed.
   * @param model model of the view.
   * @param speed speed of animation.
   */
  public PlaybackView(AnimationModel model, int speed) {
    super(model, speed);
  }
  
  @Override
  public void createView() {
    // create outer container and setup GridBag layout.
    playbackFrame = new JPanel();
    playbackFrame.setBorder(BorderFactory.createTitledBorder("Playback Animation"));
    
    playbackLayout = new GridBagLayout();
    layoutConstraints = new GridBagConstraints();
    playbackFrame.setLayout(playbackLayout);

    buttonGroup = new ButtonGroup();
    
    speedLabel = new JLabel();
    speedNumber = new JLabel();
    playbackFrame.add(this.speedLabel);
    playbackFrame.add(this.speedNumber);
    
    createAnimation();
    
    startButton();
    stopButton();
    restartButton();
    fasterButton();
    slowerButton();
    speedArea();
    loopCheckBox();
    
    JScrollPane scroll = new JScrollPane(playbackFrame);
    this.add(scroll);
    setContentPane(scroll);
    
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    
    frameAtTick(0);
    
  }

  /**
   * Set up animation panel and layout.
   */
  private void createAnimation() {
    animationPanel.setPreferredSize(new Dimension(model.getCanvasWidth(), 
            model.getCanvasHeight()));
    GridBagLayout animationLayout = new GridBagLayout();
    animationPanel.setLayout(animationLayout);
    setItemFields(animationPanel, 4, 0, 16, 17, GridBagConstraints.BOTH, 1, 0, 
            GridBagConstraints.NORTH);
    playbackFrame.add(animationPanel);
    
    setVisible(true);
    
  }

  /**
   * Set animation constraints using GridBagConstraints object.
   * @param item JComponent item.
   * @param gridX specify the row and column at the upper left of the component.
   * @param gridY specify the row and column at the upper left of the component.
   *        The left most column has address gridX=0 and top row has address gridY=0.
   * @param gridW specify the number of columns in the component's display area.
   * @param gridH specify the number of rows in the component's display area.
   * @param fill used when the component's display area is larger than the component's requested
   *        size to determine whether and how to resize the component.
   * @param weightX determine how to distribute space among columns.
   * @param weightY determine how to distribute space among rows.
   * @param anchor used when the component is smaller than its display area to determine where
   *        (within the area) to place the component.
   */
  private void setItemFields(JComponent item, int gridX, int gridY, 
                             int gridW, int gridH, int fill,int weightX, int weightY, int anchor) {
    layoutConstraints.gridx = gridX;
    layoutConstraints.gridy = gridY;
    layoutConstraints.gridwidth = gridW;
    layoutConstraints.gridheight = gridH;
    layoutConstraints.fill = fill;
    layoutConstraints.weightx = weightX;
    layoutConstraints.weighty = weightY;
    layoutConstraints.anchor = anchor;
    playbackLayout.setConstraints(item, layoutConstraints);
    
  }
  
  /**
   * Creates an unselected JToggleButton "start".
   * It can also be used as "resume".
   */
  private void startButton() {
    startButton = new JToggleButton("Start");
    startButton.setSelected(true);
    buttonGroup.add(startButton);
    setItemFields(startButton, 1, 1, 1, 1, GridBagConstraints.BOTH, 
            1, 0, GridBagConstraints.NORTH);
    startButton.setActionCommand(Actions.START.toString());
    playbackFrame.add(startButton);
  }
  
  /**
   * Set up listener for start button.
   * @param actionEvent action listener event, click start button.
   */
  @Override
  public void setStartButtonListener(ActionListener actionEvent) {
    startButton.addActionListener(actionEvent);
  }
  
  /**
   * Creates an unselected JToggleButton "stop".
   * It can also be used as "pause".
   */
  private void stopButton() {
    stopButton = new JToggleButton("Stop");
    buttonGroup.add(stopButton);
    setItemFields(stopButton, 1, 3, 1, 1, GridBagConstraints.BOTH, 
            1, 0, GridBagConstraints.NORTH);
    stopButton.setActionCommand(Actions.STOP.toString());
    playbackFrame.add(stopButton);
  }
  
  /**
   * Set listener for stop button.
   * @param actionEvent click stop button.
   */
  @Override
  public void setStopButtonListener(ActionListener actionEvent) {
    stopButton.addActionListener(actionEvent);
  }
  
  /**
   * Creates an unselected JToggleButton "restart".
   * It rewinds the animation to beginning and start again.
   */
  private void restartButton() {
    restartButton = new JToggleButton("Rest"
        + "art");
    buttonGroup.add(stopButton);
    setItemFields(restartButton, 1, 4, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    restartButton.setActionCommand(Actions.RESTART.toString());
    playbackFrame.add(restartButton);
    
  }
  
  /**
   * Set listener for restart button.
   * @param actionEvent click restart button.
   */
  @Override
  public void setRestartButtonListener(ActionListener actionEvent) {
    restartButton.addActionListener(actionEvent);
  }
  
  /**
   * Create a JButton "speed ++".
   * Increase speed of animation.
   */
  private void fasterButton() {
    fasterButton = new JButton("Speed ++");
    setItemFields(fasterButton, 1, 9, 1, 1, 
            GridBagConstraints.BOTH, 1, 0, GridBagConstraints.NORTH);
    fasterButton.setActionCommand(Actions.FASTER.toString());
    playbackFrame.add(fasterButton);
  }
  
  /**
   * Set listener for faster button.
   * @param actionEvent click "speed++" button.
   */
  @Override
  public void setFasterButtonListener(ActionListener actionEvent) {
    fasterButton.addActionListener(actionEvent);
  }
  
  /**
   * Create a JButton "speed --".
   * Decrease speed of animation.
   */
  private void slowerButton() {
    slowerButton = new JButton("Speed --");
    setItemFields(slowerButton, 1, 10, 1, 1, 
            GridBagConstraints.BOTH, 1, 0, GridBagConstraints.NORTH);
    slowerButton.setActionCommand(Actions.SLOWER.toString());
    playbackFrame.add(slowerButton);
  }
  
  /**
   * Set listener for slower button.
   * @param actionEvent click "speed--" button.
   */
  @Override
  public void setSlowerButtonListener(ActionListener actionEvent) {
    slowerButton.addActionListener(actionEvent);
  }
  
  /**
   * Create text area that shows current speed.
   */
  private void speedArea() {
    int currentSpeed = this.speed;
    speedLabel.setText("Current speed: ");
    speedNumber.setText(String.valueOf(currentSpeed));
    setItemFields(speedLabel, 1, 7, 1, 1, GridBagConstraints.HORIZONTAL, 
            1, 1, GridBagConstraints.SOUTH);
    setItemFields(speedNumber, 1, 8, 1, 1, GridBagConstraints.BOTH, 
            1, 0, GridBagConstraints.SOUTH);
    
  }
  
  /**
   * Create check box to enable/disable looping.
   * Enabling looping should cause the animation to automatically restart one it reaches the end.
   */
  private void loopCheckBox() {
    loopCheckBox = new JCheckBox("Looping");
    setItemFields(loopCheckBox, 1, 14, 1, 1, GridBagConstraints.BOTH, 1, 0, 
        GridBagConstraints.NORTH);
    loopCheckBox.setActionCommand(Actions.LOOP.toString());
    playbackFrame.add(loopCheckBox);
  }
  
  /**
   * Return the loop check box.
   * @return loop check box.
   */
  @Override
  public JCheckBox getLoopCheckBox() {
    return loopCheckBox;
  }
  
  /**
   * Set up listener for loop check box.
   * @param itemEvent check or uncheck loop check box.
   */
  @Override
  public void setLoopButtonListener(ItemListener itemEvent) {
    loopCheckBox.addItemListener(itemEvent);
  }
  
  /**
   * Enable looping for animation.
   */
  @Override
  public void enableLoop() {
    int endTick = model.getEndTick();
    if (tick > endTick) {
      tick = 0;
      timer.restart();
    }
  }
  
  /**
   * Disable looping for animation.
   */
  @Override
  public void disableLoop() {
    int endTick = model.getEndTick();
    if (tick > endTick) {
      timer.stop();
    }
  }
  
  /**
   * Paint the animation at given tick.
   * @param tick current tick.
   */
  private void frameAtTick(int tick) {
    animationPanel.setTick(tick);
    this.repaint();
  }
  
  /**
   * Play the animation.
   */
  @Override
  public void play() {
    timer = new Timer(1000 / speed, this);
    timer.setInitialDelay(100);
    timer.start();
  }

  
  /**
   * Set the speed of animation.
   * @param speed speed of animation.
   */
  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
    speedArea();
  }
  
  /**
   * Return speed of animation.
   * @return animation speed.
   */
  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    animationPanel.setTick(tick);
    animationPanel.repaint();
    tick++;
    AnimationCommand loop = new LoopAnimation();
    try {
      loop.start(model, this);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    
  }
  
  @Override
  public AnimationModel getModel() {
    return this.model;
  }
  
  @Override
  public void setModel(AnimationModel model) {
    this.model = model;
  }
  
  /**
   * Clear the model.
   */
  public void clearMode() {
    this.model.clearAll();
  }

}
