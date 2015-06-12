package com.cobweb.io.core;

import java.util.ArrayList;
import java.util.List;

import com.cobweb.io.meta.Device;
import com.cobweb.io.meta.DeviceHasPayload;
import com.cobweb.io.meta.DeviceHasSensors;
import com.cobweb.io.meta.Payload;
import com.cobweb.io.meta.Sensor;
import com.cobweb.io.meta.SensorHasPayload;
import com.cobweb.io.meta.User;
import com.cobweb.io.meta.UserHasDevices;
import com.cobweb.io.service.CreateService;
import com.cobweb.io.service.GraphFactory;
import com.cobweb.io.service.ReadService;
import com.tinkerpop.blueprints.Vertex;

/**
 * The Class CobwebWeaver.
 * @author YasithLokuge
 * 
 */
public class CobwebWeaver extends GraphFactory{

	/** The create service. */
	CreateService createService = new CreateService();		
	
	/** The read service. */
	ReadService readService = new ReadService();
	
	/**
	 * Adds the user.
	 *
	 * @param user the user
	 */
	public void addUser(User user){
		createService.CreateUser(user);
	}
	
	/**
	 * Adds the device.
	 *
	 * @param userEmail the user email
	 * @param device the device
	 */
	public void addDevice(String userEmail, Device device){
		
		UserHasDevices userHasDevices = new UserHasDevices();		
		Vertex userVertex = getUserVertex(userEmail); 
		Vertex deviceVertex = createService.CreateDevice(device);		
		userHasDevices.setUser(userVertex);
		userHasDevices.setDevice(deviceVertex);
		createService.CreateUserHasDevices(userHasDevices);
	}
	
	/**
	 * Adds the sensor.
	 *
	 * @param deviceId the device id
	 * @param sensorId the sensor id
	 */
	public void addSensor(String deviceId, Sensor sensor){
		
		DeviceHasSensors deviceHasSensors = new DeviceHasSensors();		
		Vertex deviceVertex = getDeviceVertex(deviceId);
		Vertex sensorVertex = createService.CreateSensor(sensor);	
		deviceHasSensors.setDevice(deviceVertex);
		deviceHasSensors.setSensor(sensorVertex);		
		createService.CreateDeviceHasSensors(deviceHasSensors);
	}
	
	/**
	 * Adds the device payload.
	 *
	 * @param deviceId the device id
	 * @param payload the payload
	 */
	public void addDevicePayload(String deviceId, Payload payload){
		
		DeviceHasPayload deviceHasPayload = new DeviceHasPayload();
		Vertex deviceVertex  = getDeviceVertex(deviceId);
		Vertex payloadVertex = createService.CreatePayload(payload);
		deviceHasPayload.setDevice(deviceVertex);
		deviceHasPayload.setPayload(payloadVertex);
		createService.CreateDeviceHasPayload(deviceHasPayload);
	}
	
	/**
	 * Adds the sensor payload.
	 *
	 * @param sensorId the sensor id
	 * @param payload the payload
	 */
	public void addSensorPayload(String sensorId, Payload payload){

		SensorHasPayload sensorHasPayload = new SensorHasPayload();
		Vertex sensorVertex  = getSensorVertex(sensorId);
		Vertex payloadVertex = createService.CreatePayload(payload);
		sensorHasPayload.setPayload(payloadVertex);
		sensorHasPayload.setPayload(sensorVertex);
		createService.CreateSensorHasPayload(sensorHasPayload);		
	}
	
	/**
	 * Checks if is authorized device.
	 *
	 * @param userEmail the user email
	 * @param deviceId the device id
	 * @return true, if is authorized device
	 */
	public boolean isAuthorizedDevice(String userEmail, String deviceId){
		
		List<String> idList = new ArrayList<>();		
		idList = readService.ReadDeviceIds(userEmail);		
		return idList.contains(deviceId);		
	}
}
