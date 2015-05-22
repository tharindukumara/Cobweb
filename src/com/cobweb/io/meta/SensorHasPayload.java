package com.cobweb.io.meta;

import com.tinkerpop.blueprints.Vertex;


/**
 * The Class SensorHasPayload.
 * @author YasithLokuge
 */
public class SensorHasPayload {

	/** The sensor. */
	private Vertex sensor;
	
	/** The payload. */
	private Vertex payload;
	
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
	
	/**
	 * Gets the payload.
	 *
	 * @return the payload
	 */
	public Vertex getPayload() {
		return payload;
	}
	
	/**
	 * Sets the payload.
	 *
	 * @param payload the new payload
	 */
	public void setPayload(Vertex payload) {
		this.payload = payload;
	}
	
}
