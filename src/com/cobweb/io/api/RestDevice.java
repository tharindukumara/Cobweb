package com.cobweb.io.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jettison.json.JSONObject;

import com.cobweb.io.meta.Device;
import com.cobweb.io.meta.DeviceType;
import com.cobweb.io.service.GraphFactory;
import com.cobweb.io.service.ReadService;
import com.cobweb.io.transformers.VertexToDevice;
import com.cobweb.io.utils.CobwebWeaver;
import com.cobweb.io.validator.DeviceValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tinkerpop.blueprints.Vertex;

/**
 * @author Yasith Lokuge
 * The Class RestDevice.
 */
@Path("/devices")
public class RestDevice {	
	
	/** The Constant SUCCESS. */
	private static final String SUCCESS					= "SUCCESS";
	
	/**
	 * Gets the device.
	 *
	 * @return the device
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getDevice(){
		
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		ReadService readService = new ReadService();
		String userId = readService.getUserId(email);
		
		GraphFactory graphFactory = new GraphFactory();
		List<String> deviceIdList = new ArrayList<String>();
		
		deviceIdList = readService.getDeviceIds(userId);		
		List<Vertex> deviceVertexList = new ArrayList<Vertex>();	
		List<Device> deviceObjectList = new ArrayList<Device>();		
		Map<String, Object> deviceMap = new LinkedHashMap<>();
		
		VertexToDevice vertexToDevice = new VertexToDevice();

		  
		for (String deviceId : deviceIdList) {			
			deviceVertexList.add(graphFactory.getDeviceVertex(deviceId));
		}
		
		for (Vertex vertex : deviceVertexList) {
			deviceObjectList.add(vertexToDevice.transform(vertex));
		}
		
		for (Device device : deviceObjectList) {
			deviceMap.put(device.getName(), device);
		}

		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		try {
			return objectWriter.writeValueAsString(deviceMap);
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

		
		
		String deviceName 	= null;
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
			deviceName 	= (String) incomingData.get("name");
			description = (String) incomingData.get("description");
			type 		= (String) incomingData.get("type");				
			otherType 	= (String) incomingData.get("otherType");
			
		} catch (Exception e) {
			return e.toString();
		}
		
		
		DeviceValidator deviceValidator = new DeviceValidator();
		String validatorStatus = deviceValidator.validate(incomingData);
		
		if(!validatorStatus.equals(SUCCESS))	
			return validatorStatus;			
		
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		DeviceType deviceType = DeviceType.valueOf(type.toUpperCase());
				
		Device device = new Device(deviceName,description,deviceType);
		
		if(type.equalsIgnoreCase("OTHER")){
			device.setOtherType(otherType);
		}
		
		CobwebWeaver cobwebWeaver = new CobwebWeaver();		
		cobwebWeaver.addDevice(userId, device);

		return SUCCESS;
	}
}
