package com.cobweb.io.utils;

import java.util.List;

import com.cobweb.io.meta.Payload;
import com.cobweb.io.service.ReadService;

/**
 * The Class PayloadPublisher.
 * @author Yasith Lokuge
 */
public class PayloadPublisher {

	/** The Constant INVALID. */
	private static final String INVALID = "Invalid Private Key";
	
	/** The Constant SUCCESS. */
	private static final String SUCCESS = "Success";

	/**
	 * Publish.
	 *
	 * @param key the key
	 * @param value the value
	 * @return the string
	 */
	public String publish(String key, String value) {
		
		ReadService readService = new ReadService();
		CobwebWeaver cobwebWeaver = new CobwebWeaver();
		Payload payload = new Payload(value);
		
		List<String> sensorKeyList = readService.getSensorKeyList();
		List<String> deviceKeyList = readService.getDeviceKeyList();
		
		if(sensorKeyList.contains(key)){
			
			String sensorId = readService.getSensorIdFromKey(key);
			cobwebWeaver.addSensorPayload(sensorId, payload);
			
			return SUCCESS;
			
		}else if(deviceKeyList.contains(key)){
			
			String deviceId = readService.getDeviceIdFromKey(key);
			cobwebWeaver.addDevicePayload(deviceId, payload);
			
			return SUCCESS;
			
		}else{
			
			return INVALID;
		}
		
	}

}
