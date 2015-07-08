package com.cobweb.io.core;

import com.cobweb.io.meta.Device;
import com.cobweb.io.meta.DeviceType;
import com.cobweb.io.meta.Sensor;
import com.cobweb.io.meta.SensorType;
import com.cobweb.io.meta.User;
import com.cobweb.io.service.AbstractService;
import com.cobweb.io.service.ReadService;

public class DemoTest implements AbstractService{

	public static void main(String args[]){
		
		
		User user1 = new User("Tharindu", "Kumara", "hatkumara@yahoo.com", "1qaz", "2wsx");
		User user2 = new User("Sandaruwan", "Gunasinghe", "sandaruwan.gunasinghe@gmail.com", "2wsx", "1qaz");
		
		Device device1 = new Device("iphone", "iphone", DeviceType.IPHONE);
		Device device2 = new Device("windowsphone", "windowsphone", DeviceType.WINDOWSPHONE);
		
		Sensor sensor1 = new Sensor("proximity", "proximity sensor", SensorType.PROXIMITY);
		Sensor sensor2 = new Sensor("temperature", "temperature sensor", SensorType.TEMPERATURE);
		
		
		CobwebWeaver cobwebWeaver = new CobwebWeaver();
		
		cobwebWeaver.addUser(user1);
		cobwebWeaver.addUser(user2);

		cobwebWeaver.addDevice(user1.getEmail(), device1);
		cobwebWeaver.addDevice(user2.getEmail(), device2);
		
		cobwebWeaver.addSensor(device1.getId(), sensor1);
		cobwebWeaver.addSensor(device2.getId(), sensor2);
		
		
		
	}
}
