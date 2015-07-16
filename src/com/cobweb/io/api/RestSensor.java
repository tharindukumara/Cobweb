package com.cobweb.io.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jettison.json.JSONObject;

import com.cobweb.io.meta.Sensor;
import com.cobweb.io.meta.SensorType;
import com.cobweb.io.service.ReadService;
import com.cobweb.io.utils.CobwebWeaver;
import com.cobweb.io.validator.SensorValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author Yasith Lokuge
 * The Class RestSensor.
 */
@Path("/sensor")
public class RestSensor {	
	
	/** The Constant SUCCESS. */
	private static final String SUCCESS					= "SUCCESS";
	
	/**
	 * Gets the sensor.
	 *
	 * @return the sensor
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getSensor(){
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
				
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		List<Sensor> sensorList = new ArrayList<Sensor>(); 
		sensorList = readService.getSensorList(userId);
		
		try {
			return objectWriter.writeValueAsString(sensorList);
		} catch (JsonProcessingException e) {		
			return e.toString();
		}	
	} 	

	/**
	 * Creates the.
	 *
	 * @param jsonData the json data
	 * @return the string
	 */
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)	
	public String create(InputStream jsonData) {

		
		
		String sensorName 	= null;
		String description	= null;
		String type 		= null;		
		String otherType	= null;
		
		
		StringBuilder stringData = new StringBuilder();
		JSONObject incomingData;
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(jsonData));
			String line = null;
			while ((line = in.readLine()) != null) {
				stringData.append(line);
			}
			
			incomingData = new JSONObject(stringData.toString());
			sensorName 	= (String) incomingData.get("name");
			description = (String) incomingData.get("description");
			type 		= (String) incomingData.get("type");				
			otherType 	= (String) incomingData.get("otherType");
			
		} catch (Exception e) {
			return e.toString();
		}
		
		
		SensorValidator sensorValidator = new SensorValidator();
		String validatorStatus = sensorValidator.validate(incomingData);
		
		if(!validatorStatus.equals(SUCCESS))	
			return validatorStatus;			
		
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		SensorType sensorType = SensorType.valueOf(type.toUpperCase());
				
		Sensor sensor = new Sensor(sensorName,description,sensorType);
		
		if(type.equalsIgnoreCase("OTHER")){
			sensor.setOtherType(otherType);
		}
		
		CobwebWeaver cobwebWeaver = new CobwebWeaver();		
		cobwebWeaver.addSensor(userId, sensor);

		return SUCCESS;
	}
}
