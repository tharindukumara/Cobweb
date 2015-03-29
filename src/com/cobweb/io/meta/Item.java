package com.cobweb.io.meta;

import java.net.URL;

// TODO: Auto-generated Javadoc
/**
 * The Interface Item.
 *
 * @author Yasith Lokuge
 */

public interface Item {
	
	
	/**
	 * Sets the name.
	 */
	public void setName(String name);
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Sets the description.
	 *
	 * @param Description the new description
	 */
	public void setDescription(String Description);
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription();
	
	/**
	 * Sets the id.
	 *
	 * @param devId the new id
	 */
	public void setId(String id);
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId();
	
	/**
	 * Sets the deleted.
	 *
	 * @param deleted the new deleted
	 */
	public void setDeleted(boolean deleted);
	
	/**
	 * Checks if is deleated.
	 *
	 * @return true, if is deleated
	 */
	public boolean isDeleated();
	
	/**
	 * Sets the type.
	 *
	 * @param devicetype the new type
	 */
	public void setType(DeviceType devicetype);
	
	/**
	 * Sets the type.
	 *
	 * @param sensortype the new type
	 */
	public void setType(SensorType sensortype);
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public DeviceType getType();
	
	/**
	 * Sensortype.
	 *
	 * @return the sensor type
	 */
	public SensorType sensortype();

	
	/**
	 * Sets the other type.
	 *
	 * @param type the new other type
	 */
	public void setOtherType(String type);
	
	/**
	 * Gets the other type.
	 *
	 * @return the other type
	 */
	public String getOtherType();
	
	/**
	 * Gets the image url.
	 *
	 * @return the imageUrl
	 */
	public URL getImageUrl();

	/**
	 * Sets the image url.
	 *
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(URL imageUrl);
	
}
