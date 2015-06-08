package com.cobweb.io.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.cobweb.io.core.CobwebWeaver;
import com.cobweb.io.meta.Sensor;
import com.cobweb.io.meta.SensorType;

@Path("/sensor")
public class RestSensor {

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)	
	public ResponseBuilder create(InputStream jsonData) {

		
		String sensorName 		= null;
		String parentDeviceId	= null;
		String description		= null;
		String type 			= null;
		String imageUrl 		= null;
		String otherType		= null;
		
		
		
		StringBuilder stringData = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(jsonData));
			String line = null;
			while ((line = in.readLine()) != null) {
				stringData.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data Received: " + stringData.toString());

		JSONObject incomingData;

		try {
			incomingData 	= new JSONObject(stringData.toString());
			sensorName 		= (String) incomingData.get("name");
			parentDeviceId	= (String) incomingData.get("parentDeviceId");
			description 	= (String) incomingData.get("description");
			type 			= (String) incomingData.get("type");
			imageUrl 		= (String) incomingData.get("imageUrl");		
			otherType 		= (String) incomingData.get("otherType");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		SensorType sensorType = SensorType.valueOf(type);
		URL url = null;
		
		try {
			url = new URL(imageUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
				
		CobwebWeaver cobwebWeaver = new CobwebWeaver();
		
		if(!cobwebWeaver.isAuthorizedDevice()){
			
			return Response.ok("Not Authorized", MediaType.TEXT_PLAIN);
		}
		
		Sensor sensor = new Sensor(sensorName, description, sensorType, otherType, url);
		cobwebWeaver.addSensor(parentDeviceId, sensor);

		return Response.ok("SUCCESS", MediaType.TEXT_PLAIN);
	}
}
