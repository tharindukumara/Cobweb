package com.cobweb.io.core;

import java.net.MalformedURLException;
import java.net.URL;

// TODO: Auto-generated Javadoc
/**
 * The Class Device.
 *
 * @author Yasith Lokuge
 */

public class Device implements Item{
	
	/** The name. */
	private String name;
	
	/** The id. */
	private String id;
	
	/** The description. */
	private String description;
	
	/** The devicetype. */
	private DeviceType devicetype;
	
	/** The is deleated. */
	private boolean isDeleated=false;

	/** The Other Type if any. */
	private String otherType = "default";
		
	/** The image url. */
	private URL imageUrl;

	
	/**
	 * Instantiates a new device.
	 *
	 * @param name the name
	 * @param id the id
	 * @param description the description
	 * @param devicetype the devicetype
	 * @param otherType the other type
	 * @param imageUrl the image url
	 */
	public Device(String name, String id, String description, DeviceType devicetype, String otherType, URL imageUrl){
		this.name = name;
		this.id = id;
		this.description = description;
		this.devicetype = devicetype;
		this.otherType = otherType;
		this.imageUrl = imageUrl;		
	}
	
	/**
	 * Instantiates a new device.
	 *
	 * @param name the name
	 * @param id the id
	 * @param description the description
	 * @param devicetype the devicetype
	 */
	public Device(String name, String id, String description, DeviceType devicetype){
		this.name = name;
		this.id = id;
		this.description = description;
		this.devicetype = devicetype;
		
		try {
			imageUrl = new URL("www.cobweb.io/default/device/devicedefault.jpg");
		} catch (MalformedURLException e) {
			imageUrl = null;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#setDevId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#getDevId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#setDeleted(boolean)
	 */
	@Override
	public void setDeleted(boolean deleted) {
		this.isDeleated = deleted;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#isDeleated()
	 */
	@Override
	public boolean isDeleated() {
		return isDeleated;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#setType(com.cobweb.io.core.DeviceType)
	 */
	@Override
	public void setType(DeviceType devicetype) {
		this.devicetype = devicetype;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#setType(com.cobweb.io.core.SensorType)
	 */
	@Override
	public void setType(SensorType sensortype) {

	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#getType()
	 */
	@Override
	public DeviceType getType() {
		return devicetype;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#sensortype()
	 */
	@Override
	public SensorType sensortype() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#setOtherType(java.lang.String)
	 */
	@Override
	public void setOtherType(String type) {
		otherType = type;		
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#getOtherType()
	 */
	@Override
	public String getOtherType() {		
		return otherType;
	}

	@Override
	public URL getImageUrl() {
		return imageUrl;
	}

	@Override
	public void setImageUrl(URL imageUrl) {
		this.imageUrl = imageUrl;		
	}

}
