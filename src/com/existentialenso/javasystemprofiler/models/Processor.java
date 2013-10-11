package com.existentialenso.javasystemprofiler.models;

public class Processor {
  /**
   * The processor's architecture. This currently will only be profiled on Windows.
   * 
   * Possible values include but are not limited to...
   * Intel/AMD 64-bit: "x86-64"
   * Intel/AMD 32-bit: "x86"
   * Intel Itanium: "IA64"
   */
  protected String architecture;
  
  /**
   * The number of virtual cores the Processor has. On processors with HyperThreading and related technologies,
   * this will be double the true amount.
   */
  protected int virtual_cores;
  
  /**
   * Profiles the current machine, populating the class' fields.
   * 
   * @param operating_system An already-profiled OperatingSystem object to determine OS-specific behavior.
   */
  public void profileThisDevice(OperatingSystem operating_system) {
    virtual_cores = Runtime.getRuntime().availableProcessors();
    
    // Windows-only data gathering
    if(operating_system.getName().contains("Windows")) {
      architecture = System.getenv("PROCESSOR_ARCHITECTURE");
      
      // Modernize naming conventions a bit
      if(architecture.equalsIgnoreCase("AMD64")) architecture = "x86-64";
    }
  }
}
