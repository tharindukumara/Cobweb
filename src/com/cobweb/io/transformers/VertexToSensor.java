package com.cobweb.io.transformers;

import com.cobweb.io.meta.Sensor;
import com.cobweb.io.meta.SensorType;
import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class VertexToSensor.
 * @author Yasith Lokuge
 */
public class VertexToSensor {

	
	/**
	 * Transform.
	 *
	 * @param sensorVertex the sensor vertex
	 * @return the sensor
	 */
	public Sensor transform(Vertex sensorVertex){
		
		
		String name			= sensorVertex.getProperty("name");
		String id			= sensorVertex.getProperty("idValue");
		String sensorType	= sensorVertex.getProperty("sensorType");
		String description	= sensorVertex.getProperty("description");
		String otherType	= sensorVertex.getProperty("otherType");
		
		Sensor sensor = new Sensor(name, description, SensorType.valueOf(sensorType));
		sensor.setId(id);
		sensor.setOtherType(otherType);	
		
		
		return sensor;		
	}
}
