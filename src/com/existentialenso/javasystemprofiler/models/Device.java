/**
 * @author Thorne Melcher <tmelcher@portdusk.com>
 * @copyright 2013 Thorne Melcher
 * @license GPLv3 
 * @version 0.0.2
 */

package com.existentialenso.javasystemprofiler.models;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

/**
 * Represents the Device being profiled. The screen-related information is limited to what the OS views as the 
 * "main" screen. This is all that's easily available to Java, though this may potentially be overhauled in
 * the future to get more.
 */
public class Device {
  /**
   * The canonical name of the device.
   */
  protected String name;
  
  /**
   * The processor's architecture. This currently will only be profiled on Windows.
   * 
   * Possible values include but are not limited to...
   * Intel/AMD 64-bit: "x86-64"
   * Intel/AMD 32-bit: "x86"
   * Intel Itanium: "IA64"
   */
  protected String processor_architecture;
  
  /**
   * The local IP address of the device.
   */
  protected String ip_address;

  /**
   * Any drives (be they hard, solid state, or optical disc) on this device.
   */
  protected ArrayList<Drive> drives;
  
  /**
   * Information about the device's OS.
   */
  protected OperatingSystem operating_system;
  
  /**
   * Information about the device's processor.
   */
  protected Processor processor;
  
  /**
   * Information about the device's primary screen. Secondary monitor info currently not available.
   */
  protected Screen primary_screen;
  
  /**
   * Populates the Device object based on data from the machine actually running the code.
   */
  public void profileThisDevice() {
    // Create and populate our OS object
    operating_system = new OperatingSystem();
    operating_system.profileThisDevice();
    
    // Create and populate our Screen object
    primary_screen = new Screen();
    primary_screen.profileThisDevice();
    
    // Create and populate our Processor object
    processor = new Processor();
    processor.profileThisDevice(operating_system);
    
    // Prepare to load Drive information
    FileSystemView fsv = FileSystemView.getFileSystemView();
    File[] roots = File.listRoots();
    drives = new ArrayList<Drive>();
    
    // Map information to our Drive objects
    for (File fDrive: roots) {
      if(fDrive.getTotalSpace() != 0) {
        Drive drive = new Drive();
        drive.setName(fDrive.toString());
        drive.setDisplayName(fsv.getSystemDisplayName(fDrive));
        drive.setTotalSpace(fDrive.getTotalSpace());
        drive.setFreeSpace(fDrive.getUsableSpace());
        drive.setWritable(fDrive.canWrite());
        
        drives.add(drive);
      }
    }
    
    // Get the name and IP address of the machine. These will fail if networking is disabled
    // on the computer, though, while this model could potentially be used outside the project,
    // it is designed for preparing information explicitly for the purpose of sending it over
    // the network..
    try {
      name = InetAddress.getLocalHost().getHostName();
      ip_address = InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      name = "Unknown";
      ip_address = "Unknown";
    }
    
  }
  
  /**
   * Gets the drives on this device.
   * 
   * @return The drives.
   */
  public ArrayList<Drive> getDrives() {
    return drives;
  }
  
  /**
   * Gets the name of the OS this device is running.
   * 
   * @return The name.
   */
  public OperatingSystem getOperatingSystem() {
    return operating_system;
  }
  
  /**
   * Gets the local IP Address of the device.
   * 
   * @return The address.
   */
  public String getIpAddress() {
    return ip_address;
  }
}
