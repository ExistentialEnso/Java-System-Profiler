package com.existentialenso.javasystemprofiler.models;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Screen {
  protected Dimension dimensions;
  
  protected int dpi;
  
  protected double estimated_size;
  
  public void profileThis() {
    // Use AWT's (yes, the graphics library) to get the computer's screen's dimensions and DPI
    dimensions = Toolkit.getDefaultToolkit().getScreenSize();
    dpi = Toolkit.getDefaultToolkit().getScreenResolution();
    
    // Use the Pythagorean theorem to calculate the screen's size (corner to corner in inches)
    estimated_size = Math.sqrt(
        Math.pow((dimensions.height/dpi), 2) +
        Math.pow((dimensions.width/dpi), 2));
  }
}
