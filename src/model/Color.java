package model;

/**
 * This class represents shape colors with RGB color values.
 * A RGBColor has red, green, and blue values.
 * @author whitneycai
 *
 */
public class Color {
  
  private int red;
  private int green;
  private int blue;
  
  /**
   * Construct an RGBColor object with red, green, and blue values.
   * @param red red value of color.
   * @param green green value of color.
   * @param blue blue value of color.
   * @throws IllegalArgumentException if color values are invalid.
   */
  public Color(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || green < 0 || blue < 0 || red > 255 || green > 255 || blue > 255) {
      throw new IllegalArgumentException("RGB color values must be a number between 0 and 255.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
  
  /**
   * Return the red value of the color.
   * @return red value of the color.
   */
  public int getRed() {
    return this.red;
  }
  
  /**
   * Return the green value of the color.
   * @return green value of the color.
   */
  public int getGreen() {
    return this.green;
  }
  
  /**
   * Return the blue value of the color.
   * @return blue value of the color.
   */
  public int getBlue() {
    return this.blue;
  }
  
  /**
   * Return a string representing the RGB color.
   * @return a string representing the RGB color.
   */
  @Override
  public String toString() {
    return "(" + this.red + "," + this.green + "," + this.blue + ")";
  }
  
  /**
   * Based on the RGB values, return the color they represents.
   * @return the color name that the values represents.
   */
  public String toColor() {
    if (this.red > 0 && this.green == 0 && this.blue == 0) {
      return "red";
    } else if (this.green > 0 && this.red == 0 && this.blue == 0) {
      return "green";
    } else if (this.blue > 0 && this.red == 0 && this.green == 0) {
      return "blue";
    } else {
      return this.toString();
    }
  }

}
