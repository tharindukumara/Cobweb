package com.cobweb.io.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.cobweb.io.meta.Device;
import com.cobweb.io.meta.Sensor;
import com.cobweb.io.service.ReadService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@Path("/friends")
public class RestFriendItems {

	/** The Constant UNKNOWN_SENSOR_ID. */
	private static final String UNKNOWN_FRIEND_ID 	= "{\"error\":\"Unknown User Id\"}";

	/** The Constant UNKNOWN_SENSOR_ID. */
	private static final String NOT_FRIEND 			= "{\"error\":\"User is not your friend\"}";

	/** The Constant JSON_ERROR. */
	private static final String JSON_ERROR			= "{\"error\":\"JSON Parsing error\"}";	

	
	@GET
	@Path("/device/{friendId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDevices(@PathParam("friendId") String friendId){
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		List<String> friendIdList = readService.getFriendsIdList(userId);
		
		if(!readService.checkUserNameExistsById(friendId))
			return UNKNOWN_FRIEND_ID;
		
		if(!friendIdList.contains(friendId))
			return NOT_FRIEND;
		
		List<Device> deviceList = readService.getDeviceList(friendId);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

		try {
			return objectWriter.writeValueAsString(deviceList);
		} catch (JsonProcessingException e) {		
			return JSON_ERROR;
		}		
	}
	
	
	@GET
	@Path("/sensor/{friendId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSensors(@PathParam("friendId") String friendId){
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		List<String> friendIdList = readService.getFriendsIdList(userId);
		
		if(!readService.checkUserNameExistsById(friendId))
			return UNKNOWN_FRIEND_ID;
		
		if(!friendIdList.contains(friendId))
			return NOT_FRIEND;
		
		List<Sensor> sensorList = readService.getSensorList(friendId);
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

		try {
			return objectWriter.writeValueAsString(sensorList);
		} catch (JsonProcessingException e) {		
			return JSON_ERROR;
		}		
	}
	
}
