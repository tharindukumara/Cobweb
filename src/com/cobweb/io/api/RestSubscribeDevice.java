package com.cobweb.io.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.cobweb.io.service.DeleteService;
import com.cobweb.io.service.ReadService;
import com.cobweb.io.utils.CobwebWeaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Path("/device/subscriptions")
public class RestSubscribeDevice {
	
	/** The Constant JSON_ERROR. */
	private static final String JSON_ERROR				= "{\"error\":\"JSON Parsing error\"}";	
	
	/** The Constant SUCCESS. */
	private static final String SUCCESS					= "SUCCESS";
	
	/** The Constant ERROR. */
	private static final String ERROR					= "ERROR";
	
	/** The Constant UNKNOWN_DEVICE_ID. */
	private static final String UNKNOWN_DEVICE_ID 		= "Unknown Device Id or Unauthorized Device";
	
	
	/**
	 * Gets the device subscriptions.
	 *
	 * @return the device subscriptions
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getDeviceSubscriptions(){
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		ReadService readService = new ReadService();
		
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
				
		List<String> subscriptionIdList = readService.getUserSubscribedDeviceList(userId);
				
		try {
			return objectWriter.writeValueAsString(subscriptionIdList);
		} catch (JsonProcessingException e) {
			return JSON_ERROR;
		}		
	}
	
	
	/**
	 * Delete device subscription.
	 *
	 * @param deviceId the device id
	 * @return the string
	 */
	@DELETE
	@Consumes(MediaType.TEXT_PLAIN)
	public String deleteDeviceSubscription(String deviceId){
		
		DeleteService deleteService = new DeleteService();
		ReadService readService = new ReadService();
		
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		List<String> deviceIdList	= readService.getDeviceIdList(userId);

		if(!deviceIdList.contains(deviceId))					
			return UNKNOWN_DEVICE_ID;			
		
		boolean status = deleteService.deleteUserDeviceSubscription(userId, deviceId);
		
		if(status)
			return SUCCESS;
		else
			return ERROR;	
	}

	/**
	 * Subscribe.
	 *
	 * @param deviceId the device id
	 * @return the string
	 */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public String subscribe(String deviceId){
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		String ownerId = readService.getParentUserIdFromDevice(deviceId);
		List<String> friendList = readService.getUserFollowersIdList(userId);
		
		if(!friendList.contains(ownerId))
			return UNKNOWN_DEVICE_ID;	
		
		CobwebWeaver cobwebWeaver = new CobwebWeaver();
		cobwebWeaver.addDeviceSubscription(userId, deviceId);
		
		return SUCCESS;
	}
}
