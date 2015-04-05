package com.cobweb.io.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cobweb.io.meta.SensorType;
import com.opensymphony.xwork2.ActionSupport;

public class AddSensorAction extends ActionSupport{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7230294688799993587L;
	
	/** The sensor list. */
	private List<SensorType> sensorList;
	
	/** The selected sensor. */
	private SensorType selectedSensor;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The other type. */
	private String otherType;
	
	/**
	 * Initialize list.
	 *
	 * @return the string
	 */
	public String initializeList(){		
		sensorList = new ArrayList<SensorType>();
		sensorList = Arrays.asList(SensorType.values());		
		return NONE;		
	}	
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		return SUCCESS;	
	}
	/**
	 * Gets the sensor list.
	 *
	 * @return the sensor list
	 */
	public List<SensorType> getSensorList() {
		return sensorList;
	}

	/**
	 * Sets the sensor list.
	 *
	 * @param sensorList the new sensor list
	 */
	public void setSensorList(List<SensorType> sensorList) {
		this.sensorList = sensorList;
	}

	/**
	 * Adds the sensor.
	 *
	 * @return the string
	 */
	public String addSensor(){			
		return SUCCESS;
	}

	/**
	 * @return the selectedSensor
	 */
	public SensorType getSelectedSensor() {
		return selectedSensor;
	}

	/**
	 * @param selectedSensor the selectedSensor to set
	 */
	public void setSelectedSensor(SensorType selectedSensor) {
		this.selectedSensor = selectedSensor;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the otherType
	 */
	public String getOtherType() {
		return otherType;
	}

	/**
	 * @param otherType the otherType to set
	 */
	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}
}
