/**
 * @author Thorne Melcher <tmelcher@portdusk.com>
 * @copyright 2013 Thorne Melcher
 * @license GPLv3 
 */

package com.existentialenso.javasystemprofiler.models;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

/**
 * Represents the Device being profiled.
 */
public class Device {
	/**
	 * The canonical name of the device.
	 */
	protected String name;
	
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
	 * Populates the Device object based on data from the machine actually running the code.
	 */
	public void profileThisDevice() {
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File[] roots = File.listRoots();
		drives = new ArrayList<Drive>();
		
		// Map information for each drive available to the JVM to one of our objects
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
		
		// Fill in other, easy values
		operating_system = System.getProperty("os.name");
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
}
