package com.cobweb.io.meta;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

// TODO: Auto-generated Javadoc
/**
 * The Class Sensor.
 *
 * @author Yasith Lokuge
 */

public class Sensor implements Item{

	/** The name. */
	private String name;
	
	/** The id. */
	private String id;
	
	/** The description. */
	private String description;
	
	/** The devicetype. */
	private SensorType sensortype;
	
	/** The is deleated. */
	private boolean isDeleted=false;
	
	/** The id. */
	private String otherType ="default";
	
	/** The image url. */
	private URL imageUrl;
	
	/**
	 * Instantiates a new sensor.
	 *
	 * @param name the name 
	 * @param description the description
	 * @param sensortype the sensortype
	 * @param otherType the other type
	 * @param imageUrl the image url
	 */
	public Sensor(String name, String description, SensorType sensortype, String otherType, URL imageUrl){
		
		this.name = name;		
		this.description = description;
		this.sensortype = sensortype;
		this.otherType = otherType;
		this.imageUrl = imageUrl;	
		
		UUID uuid = UUID.randomUUID();
		id = uuid.toString();
	}
	
	
	/**
	 * Instantiates a new sensor.
	 *
	 * @param name the name 
	 * @param description the description
	 * @param sensortype the sensortype
	 */
	public Sensor(String name, String description, SensorType sensortype){
		this.name = name;		
		this.description = description;
		this.sensortype = sensortype;
		
		UUID uuid = UUID.randomUUID();
		id = uuid.toString();
		
		try {
			imageUrl = new URL("www.cobweb.io/default/sensor/sensordefault.jpg");
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
		this.isDeleted = deleted;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#isDeleated()
	 */
	@Override
	public boolean isDeleted() {
		return isDeleted;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#setType(com.cobweb.io.core.DeviceType)
	 */
	@Override
	public void setType(DeviceType devicetype) {

	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#setType(com.cobweb.io.core.SensorType)
	 */
	@Override
	public void setType(SensorType sensortype) {
		this.sensortype = sensortype;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#getType()
	 */
	@Override
	public DeviceType getType() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#sensortype()
	 */
	@Override
	public SensorType sensortype() {
		return sensortype;
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

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#getImageUrl()
	 */
	@Override
	public URL getImageUrl() {
		return imageUrl;
	}

	/* (non-Javadoc)
	 * @see com.cobweb.io.core.Item#setImageUrl(java.net.URL)
	 */
	@Override
	public void setImageUrl(URL imageUrl) {
		this.imageUrl = imageUrl;
	}

}
