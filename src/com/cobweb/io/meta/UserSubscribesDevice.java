package com.cobweb.io.meta;

import com.tinkerpop.blueprints.Vertex;


/**
 * The Class UserSubscribes.
 */
public class UserSubscribesDevice {
	
	
	/** The user. */
	private Vertex user;
	
	/** The Device */
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
	 * Gets the Device.
	 *
	 * @return the Device
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
