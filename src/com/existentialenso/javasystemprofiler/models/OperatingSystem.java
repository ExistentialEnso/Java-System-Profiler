package com.existentialenso.javasystemprofiler.models;

public class OperatingSystem {
  protected String name;
  
  protected String version;
  
  /**
   * The architecture the OS is using. This is *NOT* necessarily the same as the processor architecture, since
   * it's possible to, for instance, install 32-bit Windows on a 64-bit machine.
   */
  protected String architecture;
  
  /**
   * Populates the values based on the current machine running the code.
   */
  public void profileThisDevice() {
    name = System.getProperty("os.name");
    version = System.getProperty("os.version");
    architecture = System.getProperty("os.arch");
    
    if(architecture.equalsIgnoreCase("AMD64")) architecture = "x86-64";
  }
  
  public String getName() {
    return name;
  }
}
