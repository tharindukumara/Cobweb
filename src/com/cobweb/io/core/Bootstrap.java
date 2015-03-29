/**
 * Run only once
 */
package com.cobweb.io.core;

import com.cobweb.io.service.AbstractService;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

/**
 * @author Yasith Lokuge
 *
 */
public class Bootstrap implements AbstractService {

	/** The name. */
	private final static String NAME 			= "name";
	
	/** The password. */
	private final static String PASSWORD 		= "password";
	
	/** The email. */
	private final static String EMAIL 			= "email";
	
	/** The salt. */
	private final static String SALT 			= "salt";	
	
	/** The id. */
	private final static String ID 				= "id";	
	
	/** The image url. */
	private final static String IMAGEURL 		= "imageUrl";	
	
	/** The description. */
	private final static String DESCRIPTION 	= "description";	
	
	/** The devicetype. */
	private final static String DEVICETYPE 		= "deviceType";	
	
	/** The sensortype. */
	private final static String SENSORTYPE 		= "sensorType";	
	
	/** The isdeleted. */
	private final static String ISDELETED 		= "isDeleted";	
	
	/** The other type. */
	private final static String OTHERTYPE 		= "otherType";
	
	/** The message. */
	private final static String MESSAGE 		= "message";
	
	/** The date and time. */
	private final static String DATETIME 		= "dateTime";
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		
		/**
		 * Create User Class
		 */
		
		OrientVertexType User = graph.createVertexType("User");
		graph.addVertex("class:User");		
		User.createProperty(NAME, 		OType.STRING	).setMandatory(true);
		User.createProperty(PASSWORD, 	OType.STRING	).setMandatory(true);
		User.createProperty(EMAIL, 		OType.STRING	).setMandatory(true);
		User.createProperty(SALT, 		OType.STRING	).setMandatory(true);
		User.createProperty(ID, 		OType.STRING	).setMandatory(true);
		User.createProperty(IMAGEURL, 	OType.STRING	);
		User.createProperty(ISDELETED, 	OType.BOOLEAN	);
		
		
		/**
		 * Create Device Class
		 */
		
		OrientVertexType Device = graph.createVertexType("Device");
		graph.addVertex("class:Device");	
		
		Device.createProperty(NAME, 		OType.STRING	).setMandatory(true);
		Device.createProperty(DESCRIPTION,	OType.STRING	);
		Device.createProperty(DEVICETYPE, 	OType.STRING	).setMandatory(true);
		Device.createProperty(ID, 			OType.STRING	).setMandatory(true);
		Device.createProperty(IMAGEURL, 	OType.STRING	);
		Device.createProperty(ISDELETED,	OType.BOOLEAN	);
		Device.createProperty(OTHERTYPE, 	OType.STRING	).setMandatory(true);
				
		/**
		 * Create Sensor Class
		 */		
		
		OrientVertexType Sensor = graph.createVertexType("Sensor");
		graph.addVertex("class:Sensor");
		
		Sensor.createProperty(NAME, 		OType.STRING	).setMandatory(true);
		Sensor.createProperty(ID, 			OType.STRING	).setMandatory(true);
		Sensor.createProperty(DESCRIPTION, 	OType.STRING	);
		Sensor.createProperty(SENSORTYPE, 	OType.STRING	).setMandatory(true);		
		Sensor.createProperty(IMAGEURL, 	OType.STRING	);
		Sensor.createProperty(ISDELETED, 	OType.BOOLEAN	);
		Sensor.createProperty(OTHERTYPE, 	OType.STRING	).setMandatory(true);

		/**
		 * Create Payload Class
		 */
		
		OrientVertexType Payload = graph.createVertexType("Payload");
		graph.addVertex("class:Payload");
		
		Payload.createProperty(MESSAGE, 	OType.STRING	).setMandatory(true);
		Payload.createProperty(DATETIME, 	OType.DATETIME	).setMandatory(true);
		Payload.createProperty(ISDELETED, 	OType.BOOLEAN	);
		
	}

}
