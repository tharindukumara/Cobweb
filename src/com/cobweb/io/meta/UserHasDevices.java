package com.cobweb.io.meta;

import com.tinkerpop.blueprints.Vertex;

/**
 * The Class UserHasDevices.
 * @author YasithLokuge
 */
public class UserHasDevices {

	/** The user. */
	private Vertex user;
	
	/** The device. */
	private Vertex device;
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public Vertex getUser() {
		return user;
	}
	
	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(Vertex user) {
		this.user = user;
	}
	
	/**
	 * Gets the device.
	 *
	 * @return the device
	 */
	public Vertex getDevice() {
		return device;
	}
	
	/**
	 * Sets the device.
	 *
	 * @param device the new device
	 */
	public void setDevice(Vertex device) {
		this.device = device;
	}
	
}
