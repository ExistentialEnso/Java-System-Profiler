/**
 * @author Thorne Melcher <tmelcher@portdusk.com>
 * @copyright 2013 Thorne Melcher
 * @license GPLv3 
 * @version 0.0.2
 */

package com.existentialenso.javasystemprofiler.models;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.lang.management.OperatingSystemMXBean;
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
	
	protected String processor_name;
	
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
	 * The name of the OS the device is using.
	 */
	protected String operating_system;
	
	/**
	 * The dimensions of the primary screen of this device in pixels. 
	 */
	protected Dimension screen_dimensions;
	
	/**
	 * The DPI (dots per inch) rating for the primary screen for this device.
	 */
	protected int screen_dpi;
	
	/**
	 * The estimated size of the primary screen's panel in inches. Accuracy not guranteed
	 * (calculated from screen_dimensions and screen_dpi).
	 */
	protected double estimated_screen_size;
	
	/**
	 * Populates the Device object based on data from the machine actually running the code.
	 */
	public void profileThisDevice() {
		// Easy stuff
		operating_system = System.getProperty("os.name");
		
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
		
		// Use AWT's (yes, the graphics library) to get the computer's screen's dimensions and DPI
		screen_dimensions = Toolkit.getDefaultToolkit().getScreenSize();
		screen_dpi = Toolkit.getDefaultToolkit().getScreenResolution();
		
		// Use the Pythagorean theorem to calculate the screen's size (corner to corner in inches)
		estimated_screen_size = Math.sqrt(
				Math.pow((screen_dimensions.height/screen_dpi), 2) +
				Math.pow((screen_dimensions.width/screen_dpi), 2));
		
		
		
		// Load in Windows-specific information 
		if(operating_system.contains("Windows")) {
			processor_name = System.getenv("PROCESSOR_IDENTIFIER");
			processor_architecture = System.getenv("PROCESSOR_ARCHITECTURE");
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
	public String getOperatingSystem() {
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
	
	/**
	 * Gets the estimated screen size of the device's primary screen.
	 * 
	 * @return
	 */
	public Double getEstimatedScreenSize() {
		return estimated_screen_size;
	}
}
