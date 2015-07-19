package com.cobweb.io.service;

import com.orientechnologies.orient.core.sql.OCommandSQL;


/**
 * The Class DeleteService.
 *
 * @author Yasith Lokuge
 */

public class DeleteService implements AbstractService{

	/**
	 * Delete device.
	 *
	 * @param deviceId the device id
	 * @return true, if successful
	 */
	public boolean deleteDevice(String deviceId){		
		int result = graph.command(new OCommandSQL("UPDATE Device SET isDeleted=true WHERE idValue = '"+deviceId+"'")).execute();
		return result == 1;
	}
	
	
	/**
	 * Delete sensor.
	 *
	 * @param sensorId the sensor id
	 * @return true, if successful
	 */
	public boolean deleteSensor(String sensorId){		
		int result = graph.command(new OCommandSQL("UPDATE Sensor SET isDeleted=true WHERE idValue = '"+sensorId+"'")).execute();
		return result == 1;
	}
	
	/**
	 * Delete payload.
	 *
	 * @param payloadId the payload id
	 * @return true, if successful
	 */
	public boolean deletePayload(String payloadId){		
		int result = graph.command(new OCommandSQL("UPDATE Payload SET isDeleted=true WHERE idValue = '"+payloadId+"'")).execute();
		return result == 1;
	}
}
