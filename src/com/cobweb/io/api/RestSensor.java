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

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.cobweb.io.meta.Sensor;
import com.cobweb.io.meta.SensorType;
import com.cobweb.io.utils.CobwebWeaver;


/**
 * The Class RestSensor
 * @author Yasith Lokuge
 * 
 */

@Path("/sensor")
public class RestSensor {

	/**
	 * Creates the.
	 *
	 * @param jsonData the json data
	 * @return the response builder
	 */
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
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		
		if(!cobwebWeaver.isAuthorizedDevice(email, parentDeviceId)){
			return Response.ok("Not Authorized", MediaType.TEXT_PLAIN);
		}
		
		Sensor sensor = new Sensor(sensorName, description, sensorType, otherType, url);
		cobwebWeaver.addSensor(parentDeviceId, sensor);

		return Response.ok("SUCCESS", MediaType.TEXT_PLAIN);
	}
}
