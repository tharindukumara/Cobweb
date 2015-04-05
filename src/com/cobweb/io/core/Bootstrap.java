/**
 * Run only once
 */
package com.cobweb.io.core;

import com.cobweb.io.service.AbstractService;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientEdgeType;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

/**
 * @author Yasith Lokuge
 *
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
	
	/** The isParentOnly. */
	private final static String ISPARENTONLY 	= "isParentOnly";
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		
			
		//------------------Creating Vertices----------------------------//
		System.out.println("//------------------Creating Vertices----------------------------//");			
		/**
		 * Create User Class
		 */
		System.out.println("--Creating User Vertex Class--");		
		OrientVertexType User = graph.createVertexType("User");
		Vertex user = graph.addVertex("class:User");		
		User.createProperty(FIRSTNAME, 	OType.STRING	);
		User.createProperty(LASTNAME, 	OType.STRING	);
		User.createProperty(PASSWORD, 	OType.STRING	);
		User.createProperty(EMAIL, 		OType.STRING	);
		User.createProperty(SALT, 		OType.STRING	);
		User.createProperty(ID, 		OType.STRING	);
		User.createProperty(IMAGEURL, 	OType.STRING	);
		User.createProperty(ISDELETED, 	OType.BOOLEAN	);
		
		
		/**
		 * Create Device Class
		 */
		System.out.println("--Creating Device Vertex Class--");		
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
		System.out.println("--Creating Sensor Vertex Class--/");		
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
		System.out.println("--Creating Payload Vertex Class--");		
		OrientVertexType Payload = graph.createVertexType("Payload");
		Vertex payload = graph.addVertex("class:Payload");
		
		Payload.createProperty(MESSAGE, 	OType.STRING	);
		Payload.createProperty(DATETIME, 	OType.DATETIME	);
		Payload.createProperty(ISDELETED, 	OType.BOOLEAN	);
		graph.commit();
		
		//------------------Creating Edges----------------------------//
		System.out.println("//------------------Creating Edges----------------------------//");		
		
		/**
		 * Create UserHasDevices Class
		 */
		System.out.println("--Creating UserHasDevices Edge--");		
		graph.createEdgeType("UserHasDevices");		
		graph.addEdge(null, user, device, "UserHasDevices");
		/**
		 * Create DeviceHasSensors Class
		 */
		System.out.println("--Creating DeviceHasSensors Edge--");		
		graph.createEdgeType("DeviceHasSensors");	
		graph.addEdge(null, device, sensor, "DeviceHasSensors");

		/**
		 * Create UserSubscribes Class
		 */
		System.out.println("--Creating UserSubscribes Edge--");		
		OrientEdgeType UserSubscribes = graph.createEdgeType("UserSubscribes");				
		UserSubscribes.createProperty(ISPARENTONLY	, OType.BOOLEAN );
		graph.addEdge(null, user, device, "UserSubscribes");
		graph.addEdge(null, user, sensor, "UserSubscribes");

		/**
		 * Create HasPayload Class
		 */
		System.out.println("--Creating HasPayload Edge--");		
		graph.createEdgeType("HasPayload");	
		graph.addEdge(null, device, payload, "HasPayload");
		graph.addEdge(null, sensor, payload, "HasPayload");
		graph.commit();
		
	}

}
