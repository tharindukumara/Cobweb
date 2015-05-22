package com.cobweb.io.meta;

import com.tinkerpop.blueprints.Vertex;

/**
 * The Class DeviceHasPayload.
 * @author YasithLokuge
 */
public class DeviceHasPayload {

	/** The device. */
	private Vertex device;
	
	/** The payload. */
	private Vertex payload;
	
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
