package com.cobweb.io.transformers;

import java.net.MalformedURLException;
import java.net.URL;

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
		String url			= sensorVertex.getProperty("imageUrl");
		
		Sensor sensor = new Sensor(name, description, SensorType.valueOf(sensorType));
		sensor.setId(id);
		sensor.setOtherType(otherType);
		
		if (!url.equals("null")) {
			try {
				sensor.setImageUrl(new URL(url));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		return sensor;		
	}
}
