package com.cobweb.io.service;

import com.cobweb.io.meta.Device;
import com.cobweb.io.meta.Payload;
import com.cobweb.io.meta.Sensor;
import com.cobweb.io.meta.User;
import com.cobweb.io.meta.UserSubscribes;
import com.orientechnologies.orient.core.sql.OCommandSQL;

/**
 * The Class CreateService.
 *
 * @author Yasith Lokuge
 */

public class CreateService implements AbstractService{
	
	/** The name. */
	private final String NAME 			= "name";
	
	/** The firstname. */
	private final String FIRSTNAME 		= "firstname";
	
	/** The lastname. */
	private final String LASTNAME 		= "lastname";
	
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
	
	/** The isParentOnly. */
	private final String ISPARENTONLY 	= "isParentOnly";
	
	/** The message. */
	private final String MESSAGE 		= "message";
	
	/** The message. */
	private final String DATETIME 		= "dateTime";
	
	
	/**
	 * Creates the device.
	 *
	 * @param device the device
	 */
	public CreateService(Device device){
		
		graph.command(new OCommandSQL("insert into Device ("+ NAME 			+","
															+ ID			+","
															+ DESCRIPTION	+","
															+ DEVICETYPE	+","
															+ ISDELETED		+","
															+ OTHERTYPE		+","
															+ IMAGEURL		+"	) values ('" 	
															
															+ device.getName()			+"'"+","+"'"
															+ device.getId()			+"'"+","+"'"
															+ device.getDescription()  	+"'"+","+"'"
															+ device.getType()			+"'"+","+"'"
															+ device.isDeleted()		+"'"+","+"'"
															+ device.getOtherType()		+"'"+","+"'"
															+ device.getImageUrl()		+"')")).execute();
		
	}
	
	/**
	 * Creates the sensor.
	 *
	 * @param sensor the sensor
	 */
	public CreateService(Sensor sensor){	

		graph.command(new OCommandSQL("insert into Sensor ("+ NAME 			+","
															+ ID			+","
															+ DESCRIPTION	+","
															+ SENSORTYPE	+","
															+ ISDELETED		+","
															+ OTHERTYPE		+","
															+ IMAGEURL		+"	) values ('" 	
															
															+ sensor.getName()			+"'"+","+"'"
															+ sensor.getId()			+"'"+","+"'"
															+ sensor.getDescription()  	+"'"+","+"'"
															+ sensor.getType()			+"'"+","+"'"
															+ sensor.isDeleted()		+"'"+","+"'"
															+ sensor.getOtherType()		+"'"+","+"'"
															+ sensor.getImageUrl()		+"')")).execute();
	}

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 */
	public CreateService(User user){	
		
		graph.command(new OCommandSQL("insert into User (" 	+ FIRSTNAME 	+","
															+ LASTNAME		+","
															+ PASSWORD		+","
															+ EMAIL			+","
															+ SALT			+","
															+ ID			+","
															+ IMAGEURL		+","
															+ ISDELETED		+"	) values ('" 	
															
															+ user.getFirstName()	+"'"+","+"'"
															+ user.getLastName()	+"'"+","+"'"
															+ user.getPassword()  	+"'"+","+"'"
															+ user.getEmail()		+"'"+","+"'"
															+ user.getSalt()		+"'"+","+"'"
															+ user.getUid()			+"'"+","+"'"
															+ user.getImageUrl()	+"'"+","+"'"
															+ user.isDeleted()		+"')")).execute();
	}
	
	
	/**
	 * Creates the payload.
	 *
	 * @param payload the payload
	 */
	public CreateService(Payload payload){			
		
		graph.command(new OCommandSQL("insert into Payload (" 	+ MESSAGE 	+","
																+ DATETIME	+","
																+ ISDELETED + ") values ('" 
																
																+ payload.getMessage()	+"'"+","+"'"
																+ payload.getDateTime()	+"'"+","+"'"
																+ payload.isDeleted()	+ "')")).execute();	
	}
	
	/**
	 * Creates the userSubscribes.
	 *
	 * @param userSubscribes the userSubscribes
	 */
	public CreateService(UserSubscribes userSubscribes){
		
		graph.command(new OCommandSQL("insert into Payload (" + ISPARENTONLY + ") values ('" + userSubscribes.isParentOnly() + "')")).execute();		
		
	}
	
	public void addDevice(User user, Device device){
		
		
	}
	

	
}
