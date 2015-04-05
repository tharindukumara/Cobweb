package com.cobweb.io.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cobweb.io.meta.DeviceType;
import com.opensymphony.xwork2.ActionSupport;

// TODO: Auto-generated Javadoc
/**
 * The Class AddDeviceAction.
 */
public class AddDeviceAction extends ActionSupport{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6083971429356924665L;	
	
	/** The device list. */
	private List<DeviceType> deviceList;
	
	/** The selected device. */
	private DeviceType selectedDevice;
	
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
		deviceList = new ArrayList<DeviceType>();
		deviceList = Arrays.asList(DeviceType.values());		
		return NONE;		
	}	
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		return SUCCESS;	
	}
	
	/**
	 * Gets the device list.
	 *
	 * @return the device list
	 */
	public List<DeviceType> getDeviceList() {
		return deviceList;
	}

	/**
	 * Sets the device list.
	 *
	 * @param deviceList the new device list
	 */
	public void setDeviceList(List<DeviceType> deviceList) {
		this.deviceList = deviceList;
	}

	/**
	 * Adds the device.
	 *
	 * @return the string
	 */
	public String addDevice(){		
		return SUCCESS;
	}

	/**
	 * Gets the selected device.
	 *
	 * @return the selectedDevice
	 */
	public DeviceType getSelectedDevice() {
		return selectedDevice;
	}

	/**
	 * Sets the selected device.
	 *
	 * @param selectedDevice the selectedDevice to set
	 */
	public void setSelectedDevice(DeviceType selectedDevice) {
		this.selectedDevice = selectedDevice;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the other type.
	 *
	 * @return the otherType
	 */
	public String getOtherType() {
		return otherType;
	}

	/**
	 * Sets the other type.
	 *
	 * @param otherType the otherType to set
	 */
	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}
}
