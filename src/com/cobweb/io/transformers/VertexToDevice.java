package com.cobweb.io.transformers;

import com.cobweb.io.meta.Device;
import com.cobweb.io.meta.DeviceType;
import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class VertexToDevice.
 * @author Yasith Lokuge
 */
public class VertexToDevice {

	
	/**
	 * Transform.
	 *
	 * @param deviceVertex the device vertex
	 * @return the device
	 */
	public Device transform(Vertex deviceVertex){
		
		
		String name			= deviceVertex.getProperty("name");
		String id			= deviceVertex.getProperty("idValue");
		String deviceType	= deviceVertex.getProperty("deviceType");
		String description	= deviceVertex.getProperty("description");
		String otherType	= deviceVertex.getProperty("otherType");
		
		Device device = new Device(name, description, DeviceType.valueOf(deviceType));
		device.setId(id);
		device.setOtherType(otherType);		
		
		return device;		
	}
}
