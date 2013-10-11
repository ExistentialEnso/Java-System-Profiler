/**
 * @author Thorne Melcher <tmelcher@portdusk.com>
 * @copyright 2013 Thorne Melcher
 * @license GPLv3 
 */

package com.existentialenso.javasystemprofiler.models;

public class Drive {
	/**
	 * The system name of this drive, e.g. "C:\".
	 */
	protected String name;
	
	/**
	 * The display name of this drive.
	 */
	protected String display_name;
	
	/**
	 * The total space of this drive, in bytes.
	 */
	protected long total_space;
	
	/**
	 * The remaining free space for this drive, in bytes.
	 */
	protected long free_space;

	/**
	 * Whether or not this drive is writable.
	 */
	protected boolean is_writable;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDisplayName(String display_name) {
		this.display_name = display_name;
	}
	
	public void setTotalSpace(long total_space) {
		this.total_space = total_space;
	}
	
	public void setFreeSpace(long free_space) {
		this.free_space = free_space;
	}
	
	public void setWritable(boolean is_writable) {
		this.is_writable = is_writable;
	}
	
	public boolean isWritable() {
		return is_writable;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDisplayName() {
		return display_name;
	}
	
	public long getTotalSpace() {
		return total_space;
	}
	
	public long getFreeSpace() {
		return free_space;
	}
}
