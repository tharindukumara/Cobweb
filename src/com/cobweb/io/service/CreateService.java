package com.cobweb.io.service;

import com.cobweb.io.core.Device;
import com.cobweb.io.core.Sensor;
import com.cobweb.io.core.User;
import com.orientechnologies.orient.core.sql.OCommandSQL;

/**
 * The Class CreateService.
 *
 * @author Yasith Lokuge
 */

public class CreateService implements AbstractService{
	
	/** The name. */
	private final String NAME 			= "name";
	
	/** The password. */
	private final String PASSWORD 		= "password";
	
	/** The email. */
	private final String EMAIL 			= "email";
	
	/** The salt. */
	private final String SALT 			= "salt";	
	
	/** The id. */
	private final String ID 			= "id";	
	
	/** The description. */
	private final String DESCRIPTION 	= "description";	
	
	/** The devicetype. */
	private final String DEVICETYPE 	= "deviceType";	
	
	/** The sensortype. */
	private final String SENSORTYPE 	= "sensorType";	
	
	/** The isdeleted. */
	private final String ISDELETED 		= "isDeleted";	
	
	/** The image url. */
	private final String IMAGEURL 		= "imageUrl";

	/** The other type. */
	private final String OTHERTYPE 		= "otherType";
	
	/**
	 * Creates the device.
	 *
	 * @param device the device
	 */
	public CreateService(Device device){
		
		graph.command(new OCommandSQL("insert into Device (" + NAME 		+ ") values ('" + device.getName()			+ "')")).execute();		
		graph.command(new OCommandSQL("insert into Device (" + ID 			+ ") values ('" + device.getId()			+ "')")).execute();		
		graph.command(new OCommandSQL("insert into Device (" + DESCRIPTION 	+ ") values ('" + device.getDescription()	+ "')")).execute();		
		graph.command(new OCommandSQL("insert into Device (" + DEVICETYPE 	+ ") values ('" + device.getType()			+ "')")).execute();		
		graph.command(new OCommandSQL("insert into Device (" + ISDELETED 	+ ") values ('" + device.isDeleated()		+ "')")).execute();		
		graph.command(new OCommandSQL("insert into Device (" + OTHERTYPE 	+ ") values ('" + device.getOtherType()		+ "')")).execute();		
		graph.command(new OCommandSQL("insert into Device (" + IMAGEURL 	+ ") values ('" + device.getImageUrl()		+ "')")).execute();		

		
	}
	
	/**
	 * Creates the sensor.
	 *
	 * @param sensor the sensor
	 */
	public CreateService(Sensor sensor){
		
		graph.command(new OCommandSQL("insert into Sensor (" + NAME 		+ ") values ('" + sensor.getName()			+ "')")).execute();		
		graph.command(new OCommandSQL("insert into Device (" + ID 			+ ") values ('" + sensor.getId()			+ "')")).execute();		
		graph.command(new OCommandSQL("insert into Device (" + DESCRIPTION 	+ ") values ('" + sensor.getDescription()	+ "')")).execute();		
		graph.command(new OCommandSQL("insert into Device (" + SENSORTYPE 	+ ") values ('" + sensor.getType()			+ "')")).execute();		
		graph.command(new OCommandSQL("insert into Device (" + ISDELETED 	+ ") values ('" + sensor.isDeleated()		+ "')")).execute();		
		graph.command(new OCommandSQL("insert into Device (" + OTHERTYPE 	+ ") values ('" + sensor.getOtherType()		+ "')")).execute();		
		graph.command(new OCommandSQL("insert into Device (" + IMAGEURL 	+ ") values ('" + sensor.getImageUrl()		+ "')")).execute();		

	}

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 */
	public CreateService(User user){		
		
		graph.command(new OCommandSQL("insert into User (" + NAME 		+ ") values ('" + user.getName()		+ "')")).execute();		
		graph.command(new OCommandSQL("insert into User (" + PASSWORD 	+ ") values ('" + user.getPassword()	+ "')")).execute();		
		graph.command(new OCommandSQL("insert into User (" + EMAIL 		+ ") values ('" + user.getEmail() 		+ "')")).execute();		
		graph.command(new OCommandSQL("insert into User (" + SALT 		+ ") values ('" + user.getSalt() 		+ "')")).execute();		
		graph.command(new OCommandSQL("insert into User (" + ID 		+ ") values ('" + user.getUid()			+ "')")).execute();		
		graph.command(new OCommandSQL("insert into User (" + IMAGEURL 	+ ") values ('" + user.getImageUrl() 	+ "')")).execute();		
		graph.command(new OCommandSQL("insert into User (" + ISDELETED 	+ ") values ('" + user.isDeleted() 		+ "')")).execute();		

	}

	
}
