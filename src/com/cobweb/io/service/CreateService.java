package com.cobweb.io.service;

import com.cobweb.io.meta.Device;
import com.cobweb.io.meta.DeviceHasPayload;
import com.cobweb.io.meta.DeviceHasSensors;
import com.cobweb.io.meta.Payload;
import com.cobweb.io.meta.Sensor;
import com.cobweb.io.meta.SensorHasPayload;
import com.cobweb.io.meta.User;
import com.cobweb.io.meta.UserHasDevices;
import com.cobweb.io.meta.UserFollowsUser;
import com.cobweb.io.meta.UserSubscribesDevice;
import com.cobweb.io.meta.UserSubscribesSensor;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
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
	
	/** The role. */
	private final String ROLE			= "role";
	
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
	
	/** The isAccepted. */
	private final String ISACCEPTED 	= "isAccepted";
	
	/** The message. */
	private final String MESSAGE 		= "message";	
	
	/** The datetime. */
	private final String DATETIME 		= "dateTime";	

	/** The userfollowsuser. */
	private final String USERFOLLOWSUSER	= "userFollowsUser";	
	
	/** The usersubscribesdevice. */
	private final String USERSUBSCRIBESDEVICE	= "userSubscribesDevice";	
	
	/** The usersubscribessensor. */
	private final String USERSUBSCRIBESSENSOR	= "userSubscribesSensor";		
	
	/** The userhasdevices. */
	private final String USERHASDEVICES 		= "hasDevices";
	
	/** The devicehassensors. */
	private final String DEVICEHASSENSORS 		= "hasSensors";
	
	/** The devicehaspayload. */
	private final String DEVICEHASPAYLOAD 		= "deviceHasPayload";
	
	/** The sensorhaspayload. */
	private final String SENSORHASPAYLOAD 		= "sensorHasPayload";
	
	/**
	 * Creates the device.
	 *
	 * @param device the device
	 * @return the vertex
	 */
	public Vertex CreateDevice(Device device){
		
		Vertex v = graph.command(new OCommandSQL("insert into Device ("+ NAME 			+","
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
		
		return v;
	}
	
	/**
	 * Creates the sensor.
	 *
	 * @param sensor the sensor
	 * @return the vertex
	 */
	public Vertex CreateSensor(Sensor sensor){	

		Vertex v = graph.command(new OCommandSQL("insert into Sensor ("+ NAME 			+","
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
		return v;
	}

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the vertex
	 */
	public Vertex CreateUser(User user){	
		
		Vertex v = graph.command(new OCommandSQL("insert into User (" 	+ FIRSTNAME 	+","
															+ LASTNAME		+","
															+ PASSWORD		+","
															+ EMAIL			+","
															+ SALT			+","
															+ ROLE			+","
															+ ID			+","
															+ IMAGEURL		+","
															+ ISDELETED		+"	) values ('" 	
															
															+ user.getFirstName()	+"'"+","+"'"
															+ user.getLastName()	+"'"+","+"'"
															+ user.getPassword()  	+"'"+","+"'"
															+ user.getEmail()		+"'"+","+"'"
															+ user.getSalt()		+"'"+","+"'"
															+ user.getRole()		+"'"+","+"'"
															+ user.getUid()			+"'"+","+"'"
															+ user.getImageUrl()	+"'"+","+"'"
															+ user.isDeleted()		+"')")).execute();
	
		return v;
	}
	
	
	
	/**
	 * Creates the payload.
	 *
	 * @param payload the payload
	 * @return the vertex
	 */
	public Vertex CreatePayload(Payload payload){			
		
		Vertex v = graph.command(new OCommandSQL("insert into Payload (" 	+ MESSAGE 	+","
																+ DATETIME	+","
																+ ISDELETED + ") values ('" 
																
																+ payload.getMessage()	+"'"+","+"'"
																+ payload.getDateTime()	+"'"+","+"'"
																+ payload.isDeleted()	+ "')")).execute();	
	
		return v;		
	}
	
	/**
	 * Creates the userSubscribes.
	 *
	 * @param userSubscribes the userSubscribes
	 * @return the edge
	 */
	public Edge CreateUserFollowsUser(UserFollowsUser userFollowsUser){
		
		Edge e = graph.addEdge(null, userFollowsUser.getUserOut()	, userFollowsUser.getUserIn(), USERFOLLOWSUSER);
		e.setProperty(ISACCEPTED, false);
		return e;
	}
	
	public Edge CreateUserSubscribesDevice(UserSubscribesDevice userSubscribesDevice){
		
		Edge e = graph.addEdge(null, userSubscribesDevice.getUser()	, userSubscribesDevice.getDevice(), USERSUBSCRIBESDEVICE);
		e.setProperty(ISACCEPTED, false);
		return e;
	}
	
	public Edge CreateUserSubscribesSensor(UserSubscribesSensor userSubscribesSensor){
		
		Edge e = graph.addEdge(null, userSubscribesSensor.getUser()	, userSubscribesSensor.getSensor(), USERSUBSCRIBESSENSOR);
		e.setProperty(ISACCEPTED, false);
		return e;
	}

	/**
	 * Creates the user has devices.
	 *
	 * @param userHasDevices the user has devices
	 * @return the edge
	 */
	public Edge CreateUserHasDevices(UserHasDevices userHasDevices){
		
		Edge e = graph.addEdge(null, userHasDevices.getUser(), userHasDevices.getDevice(), USERHASDEVICES);		
		return e;		
	}
	
	/**
	 * Creates the device has sensors.
	 *
	 * @param deviceHasSensors the device has sensors
	 * @return the edge
	 */
	public Edge CreateDeviceHasSensors(DeviceHasSensors deviceHasSensors){
		
		Edge e = graph.addEdge(null, deviceHasSensors.getDevice(), deviceHasSensors.getSensor(), DEVICEHASSENSORS);		
		return e;		
	}
	
	/**
	 * Creates the sensor has payload.
	 *
	 * @param sensorHasPayload the sensor has payload
	 * @return the edge
	 */
	public Edge CreateSensorHasPayload(SensorHasPayload sensorHasPayload){
		
		Edge e = graph.addEdge(null, sensorHasPayload.getSensor(), sensorHasPayload.getPayload(), SENSORHASPAYLOAD);		
		return e;		
	}

	
	/**
	 * Creates the device has payload.
	 *
	 * @param deviceHasPayload the device has payload
	 * @return the edge
	 */
	public Edge CreateDeviceHasPayload(DeviceHasPayload deviceHasPayload){
		
		Edge e = graph.addEdge(null, deviceHasPayload.getDevice(), deviceHasPayload.getPayload(), DEVICEHASPAYLOAD);
		return e;		
	}
	
}
