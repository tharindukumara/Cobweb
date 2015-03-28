package com.cobweb.io.service;

import com.cobweb.io.core.Device;
import com.cobweb.io.core.Sensor;
import com.cobweb.io.core.User;
import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateService.
 *
 * @author Yasith Lokuge
 */

public class CreateService implements AbstractService{
	
	/** The name. */
	private final String NAME = "name";
	
	/** The password. */
	private final String PASSWORD = "password";
	
	/** The email. */
	private final String EMAIL = "email";
	
	/** The salt. */
	private final String SALT = "salt";	
	
	
	/**
	 * Creates the device.
	 *
	 * @param device the device
	 */
	public void Create(Device device){
		
	}
	
	/**
	 * Creates the sensor.
	 *
	 * @param sensor the sensor
	 */
	public void Create(Sensor sensor){
		
	}

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 */
	public void Create(User user){
		
		Vertex v = graph.addVertex(user);
		v.setProperty(NAME	,user.getName() );
		v.setProperty(PASSWORD	,user.getPassword() );
		v.setProperty(EMAIL	,user.getEmail() );
		v.setProperty(SALT	,user.getSalt() );
		graph.commit();
	
	}
	
}
