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
	
	
	/**
	 * Delete user device subscription.
	 *
	 * @param userId the user id
	 * @param deviceId the device id
	 * @return true, if successful
	 */
	public boolean deleteUserDeviceSubscription(String userId, String deviceId){
		int result = graph.command(new OCommandSQL("DELETE EDGE UserSubscribesDevice WHERE outV().idValue ='"+userId+"' AND inV().idValue ='"+deviceId+"'")).execute();
		return result == 1;	
	}
	
	
	/**
	 * Delete user sensor subscription.
	 *
	 * @param userId the user id
	 * @param sensorId the sensor id
	 * @return true, if successful
	 */
	public boolean deleteUserSensorSubscription(String userId, String sensorId){
		int result = graph.command(new OCommandSQL("DELETE EDGE UserSubscribesSensor WHERE outV().idValue ='"+userId+"' AND inV().idValue ='"+sensorId+"'")).execute();
		return result == 1;	
	}
	
	/**
	 * Delete user follows user.
	 *
	 * @param userId the user id
	 * @param friendId the friend id
	 * @return true, if successful
	 */
	public boolean deleteUserFollowsUser(String userId, String friendId){
		int result1 = graph.command(new OCommandSQL("DELETE EDGE UserFollowsUser WHERE outV().idValue ='"+userId+"' AND inV().idValue ='"+friendId+"'")).execute();
		int result2 = graph.command(new OCommandSQL("DELETE EDGE UserFollowsUser WHERE outV().idValue ='"+friendId+"' AND inV().idValue ='"+userId+"'")).execute();
		
		if(result1 == 0 && result2 == 0)
			return false;
		else
			return true;
	}
	
}
