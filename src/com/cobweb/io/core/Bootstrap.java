/**
 * Run only once
 */
package com.cobweb.io.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.cobweb.io.service.AbstractService;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientEdgeType;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

/**
 * The Class Bootstrap.
 *
 * @author Yasith Lokuge
 */
public class Bootstrap implements AbstractService {

	/** The firstname. */
	private final static String FIRSTNAME 		= "firstname";
	
	/** The lastname. */
	private final static String LASTNAME 		= "lastname";
	
	/** The name. */
	private final static String NAME 			= "name";
	
	/** The password. */
	private final static String PASSWORD 		= "password";
	
	/** The email. */
	private final static String EMAIL 			= "email";
	
	/** The salt. */
	private final static String SALT 			= "salt";	
		
	/** The Constant ROLE. */
	private final static String ROLE			= "role";
	
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
	
	/** The isAccepted. */
	private final static String ISACCEPTED 		= "isAccepted";
	
	/** The log. */
	private static Log log = LogFactory.getLog(Bootstrap.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		
			
		//------------------Creating Vertices----------------------------//
		log.debug("//------------------Creating Vertices----------------------------//");			
		/**
		 * Create User Class
		 */
		log.debug("--Creating User Vertex Class--");		
		OrientVertexType User = graph.createVertexType("User");
		Vertex user = graph.addVertex("class:User");		
		User.createProperty(FIRSTNAME, 	OType.STRING	);
		User.createProperty(LASTNAME, 	OType.STRING	);
		User.createProperty(PASSWORD, 	OType.STRING	);
		User.createProperty(EMAIL, 		OType.STRING	);
		User.createProperty(SALT, 		OType.STRING	);
		User.createProperty(ROLE, 		OType.STRING	);
		User.createProperty(ID, 		OType.STRING	);
		User.createProperty(IMAGEURL, 	OType.STRING	);
		User.createProperty(ISDELETED, 	OType.BOOLEAN	);
		
		
		/**
		 * Create Device Class
		 */
		log.debug("--Creating Device Vertex Class--");		
		OrientVertexType Device = graph.createVertexType("Device");
		Vertex device = graph.addVertex("class:Device");	
		
		Device.createProperty(NAME, 		OType.STRING	);
		Device.createProperty(DESCRIPTION,	OType.STRING	);
		Device.createProperty(DEVICETYPE, 	OType.STRING	);
		Device.createProperty(ID, 			OType.STRING	);
		Device.createProperty(IMAGEURL, 	OType.STRING	);
		Device.createProperty(ISDELETED,	OType.BOOLEAN	);
		Device.createProperty(OTHERTYPE, 	OType.STRING	);
				
		/**
		 * Create Sensor Class
		 */		
		log.debug("--Creating Sensor Vertex Class--/");		
		OrientVertexType Sensor = graph.createVertexType("Sensor");
		Vertex sensor = graph.addVertex("class:Sensor");
		
		Sensor.createProperty(NAME, 		OType.STRING	);
		Sensor.createProperty(ID, 			OType.STRING	);
		Sensor.createProperty(DESCRIPTION, 	OType.STRING	);
		Sensor.createProperty(SENSORTYPE, 	OType.STRING	);		
		Sensor.createProperty(IMAGEURL, 	OType.STRING	);
		Sensor.createProperty(ISDELETED, 	OType.BOOLEAN	);
		Sensor.createProperty(OTHERTYPE, 	OType.STRING	);

		/**
		 * Create Payload Class
		 */
		log.debug("--Creating Payload Vertex Class--");		
		OrientVertexType Payload = graph.createVertexType("Payload");
		Vertex payload = graph.addVertex("class:Payload");
		
		Payload.createProperty(MESSAGE, 	OType.STRING	);
		Payload.createProperty(DATETIME, 	OType.DATETIME	);
		Payload.createProperty(ID, 			OType.STRING	);
		Payload.createProperty(ISDELETED, 	OType.BOOLEAN	);
		graph.commit();
		
		//------------------Creating Edges----------------------------//
		log.debug("//------------------Creating Edges----------------------------//");		
		
		/**
		 * Create UserHasDevices Class
		 */
		log.debug("--Creating UserHasDevices Edge--");		
		graph.createEdgeType("UserHasDevices");		
		graph.addEdge(null, user, device, "UserHasDevices");
		
		
		/**
		 * Create DeviceHasSensors Class
		 */
		log.debug("--Creating DeviceHasSensors Edge--");		
		graph.createEdgeType("DeviceHasSensors");	
		graph.addEdge(null, device, sensor, "DeviceHasSensors");

		
		/**
		 * Create UserFollowsUser Class
		 */
		log.debug("--Creating UserFollowsUser Edge--");		
		OrientEdgeType UserFollowsUser = graph.createEdgeType("UserFollowsUser");				
		UserFollowsUser.createProperty(ISACCEPTED	, OType.BOOLEAN );
		graph.addEdge(null, user, user, "UserFollowsUser");
		
		
		/**
		 * Create UserSubscribesDevice Class
		 */
		log.debug("--Creating UserSubscribesDevice Edge--");		
		OrientEdgeType UserSubscribesDevice = graph.createEdgeType("UserSubscribesDevice");				
		UserSubscribesDevice.createProperty(ISACCEPTED	, OType.BOOLEAN );
		graph.addEdge(null, user, device, "UserSubscribesDevice");
		
		
		/**
		 * Create UserSubscribesSensor Class
		 */
		log.debug("--Creating UserSubscribesSensor Edge--");		
		OrientEdgeType UserSubscribesSensor = graph.createEdgeType("UserSubscribesSensor");				
		UserSubscribesSensor.createProperty(ISACCEPTED	, OType.BOOLEAN );
		graph.addEdge(null, user, device, "UserSubscribesSensor");
		

		/**
		 * Create DeviceHasPayload Class
		 */
		log.debug("--Creating DeviceHasPayload Edge--");		
		graph.createEdgeType("HasPayload");	
		graph.addEdge(null, device, payload, "HasPayload");
		
		/**
		 * Create SensorHasPayload Class
		 */
		log.debug("--Creating SensorHasPayload Edge--");		
		graph.createEdgeType("SensorHasPayload");	
		graph.addEdge(null, sensor, payload, "SensorHasPayload");
		
		graph.commit();
		
	}

}
