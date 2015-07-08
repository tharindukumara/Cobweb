package com.cobweb.io.meta;

import com.tinkerpop.blueprints.Vertex;


// TODO: Auto-generated Javadoc
/**
 * The Class UserSubscribesSensor.
 */
public class UserSubscribesSensor {
	
	
	/** The user. */
	private Vertex user;
	
	/** The sensor. */
	private Vertex sensor;
	
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
	 * Gets the sensor.
	 *
	 * @return the sensor
	 */
	public Vertex getSensor() {
		return sensor;
	}

	/**
	 * Sets the sensor.
	 *
	 * @param sensor the new sensor
	 */
	public void setSensor(Vertex sensor) {
		this.sensor = sensor;
	}	
}
