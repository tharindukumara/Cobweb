package com.cobweb.io.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cobweb.io.meta.DeviceType;
import com.cobweb.io.meta.SensorType;
import com.opensymphony.xwork2.ActionSupport;

public class Initialize extends ActionSupport{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3785829468596152882L;
	
	/** The sensor list. */
	private List<SensorType> sensorList;
	
	/** The device list. */
	private List<DeviceType> deviceList;
	
	/**
	 * Initialize list.
	 *
	 * @return the string
	 */
	public String initializeList(){		
		sensorList = new ArrayList<SensorType>();
		sensorList = Arrays.asList(SensorType.values());
		deviceList = new ArrayList<DeviceType>();
		deviceList = Arrays.asList(DeviceType.values());	
		return NONE;		
	}

	/**
	 * @return the sensorList
	 */
	public List<SensorType> getSensorList() {
		return sensorList;
	}

	/**
	 * @param sensorList the sensorList to set
	 */
	public void setSensorList(List<SensorType> sensorList) {
		this.sensorList = sensorList;
	}

	/**
	 * @return the deviceList
	 */
	public List<DeviceType> getDeviceList() {
		return deviceList;
	}

	/**
	 * @param deviceList the deviceList to set
	 */
	public void setDeviceList(List<DeviceType> deviceList) {
		this.deviceList = deviceList;
	}


	
}
