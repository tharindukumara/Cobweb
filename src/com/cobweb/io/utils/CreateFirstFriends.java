package com.cobweb.io.utils;

import com.cobweb.io.meta.Device;
import com.cobweb.io.meta.DeviceType;
import com.cobweb.io.meta.Payload;
import com.cobweb.io.meta.Sensor;
import com.cobweb.io.meta.SensorType;
import com.cobweb.io.meta.User;

public class CreateFirstFriends {
	
	/** The Constant USER1_ID. */
	private static final String USER1_ID 		= "2cc1bc0b-8faf-4537-86b8-e2390051fed5";
	
	/** The Constant USER2_ID. */
	private static final String USER2_ID 		= "db36b40d-dbb2-48a1-b4bb-a5660b1b04db";
	
	/** The Constant USER1_DEVICE_ID. */
	private static final String USER1_DEVICE_ID = "15f358a3-f2ac-4372-9948-e3081631d45b";
	
	/** The Constant USER1_SENSOR_ID. */
	private static final String USER1_SENSOR_ID = "23894fdb-a97d-4e81-9929-6977513de430";
	
	/** The Constant USER2_DEVICE_ID. */
	private static final String USER2_DEVICE_ID = "b4892ea8-d3b9-4b4b-8827-c5bd67874a6b";
	
	/** The Constant USER2_SENSOR_ID. */
	private static final String USER2_SENSOR_ID = "7046a84a-a8f5-4a57-bf00-9f4ad64cdcec";
	
	
	public void create() {
		
		/** The cobweb weaver. */
		CobwebWeaver cobwebWeaver = new CobwebWeaver();
		
		
		/** Create MQTT Superuser */
		User superuser	= new User("Super", "User", "admin@cobweb.io");
		superuser.setPassword("C0bw3b105up3ru53r");
		
		User userAnn 	= new User("Lucy", "Ann", "lucy@cobweb.io");
		userAnn.setUid(USER1_ID);
		
		
		Device user1device = new Device("Coffee Machine", "My Coffee Machine at Home", DeviceType.OTHER);
		user1device.setOtherType("Coffee Machine");
		user1device.setId(USER1_DEVICE_ID);
		
		Payload device1payload = new Payload("coffee machine ready");
		
		Sensor device1sensor = new Sensor("Coffee Level Sensor", "Coffee Level Sensor in my Coffee Machine", SensorType.OTHER);
		device1sensor.setOtherType("Coffee Level Sensor");
		device1sensor.setId(USER1_SENSOR_ID);
		
		Payload sensor1payload = new Payload("Running Out of Coffee!!! Please Refill");
		
		User userPeter 	= new User("Peter", "Williams", "peter@cobweb.io");
		userPeter.setUid(USER2_ID);
		
		Device user2device = new Device("Smart Iron", "My Smart Iron at Home", DeviceType.OTHER);
		user2device.setOtherType("Smart Iron");
		user2device.setId(USER2_DEVICE_ID);
		
		Payload device2payload = new Payload("Smart Iron Switched On!");
		
		Sensor device2sensor = new Sensor("Power Sensor", "Power Sensor in my smart iron", SensorType.OTHER);
		device2sensor.setOtherType("Power Sensor");
		device2sensor.setId(USER2_SENSOR_ID);
		
		Payload sensor2payload = new Payload("Current Consumption 100W");
		
		cobwebWeaver.addUser(superuser);
		cobwebWeaver.addUser(userAnn);
		cobwebWeaver.addUser(userPeter);
		cobwebWeaver.addDevice(USER1_ID, user1device);
		cobwebWeaver.addDevice(USER2_ID, user2device);
		cobwebWeaver.addDevicePayload(USER1_DEVICE_ID, device1payload);
		cobwebWeaver.addDevicePayload(USER2_DEVICE_ID, device2payload);
		cobwebWeaver.addSensor(USER1_DEVICE_ID, device1sensor);
		cobwebWeaver.addSensor(USER2_DEVICE_ID, device2sensor);
		cobwebWeaver.addSensorPayload(USER1_SENSOR_ID, sensor1payload);
		cobwebWeaver.addSensorPayload(USER2_SENSOR_ID, sensor2payload);
		
		
	}

}
