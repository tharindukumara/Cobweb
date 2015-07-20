package com.cobweb.io.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.cobweb.io.meta.Payload;
import com.cobweb.io.service.DeleteService;
import com.cobweb.io.service.ReadService;
import com.cobweb.io.utils.CobwebWeaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;




@Path("/device/message")
public class RestDevicePayload {

	/** The Constant UNKNOWN_DEVICE_ID. */
	private static final String UNKNOWN_DEVICE_ID 	= "{\"error\":\"Unknown Device Id or Unauthorized Device\"}";
	
	/** The Constant JSON_ERROR. */
	private static final String JSON_ERROR			= "{\"error\":\"JSON Parsing error\"}";	
	
	/**
	 * Gets the device payload.
	 *
	 * @param deviceId the device id
	 * @return the device payload
	 */
	@GET
	@Path("{deviceId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDevicePayload(@PathParam("deviceId") String deviceId){
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		List<String> deviceIdList 			= readService.getDeviceIdList(userId);
		List<String> subscribedDeviceIdList = readService.getSubscribedDeviceIdList(userId);
		
		if(!(deviceIdList.contains(deviceId) || subscribedDeviceIdList.contains(deviceId)))					
			return UNKNOWN_DEVICE_ID;	
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		List<Payload> payloadList = new ArrayList<Payload>(); 
		payloadList = readService.getDevicePayloadList(deviceId);
		
		try {
			return objectWriter.writeValueAsString(payloadList);
		} catch (JsonProcessingException e) {		
			return JSON_ERROR;
		}
		
	}
	
	/**
	 * Creates the device payload.
	 *
	 * @param deviceId the device id
	 * @param message the message
	 * @return the response
	 */
	@POST	
	@Path("{deviceId}")
	@Consumes(MediaType.TEXT_PLAIN)	
	public Response createDevicePayload(@PathParam("deviceId") String deviceId, String message) {
		
		Payload payload 			= new Payload(message);
		CobwebWeaver cobwebWeaver 	= new CobwebWeaver();
		ReadService readService 	= new ReadService();
		
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		List<String> deviceIdList = readService.getDeviceIdList(userId);
		
		if(!deviceIdList.contains(deviceId))					
			return Response.status(Response.Status.UNAUTHORIZED).build();
		
		cobwebWeaver.addSensorPayload(deviceId, payload);
		
		return Response.status(Response.Status.OK).build();
	}
	
	/**
	 * Delete sensor payload.
	 *
	 * @param payloadId the payload id
	 * @return the response
	 */
	@DELETE
	@Path("{payloadId}")
	public Response deleteDevicePayload(@PathParam("payloadId") String payloadId) {
		
		DeleteService deleteService = new DeleteService();
		ReadService readService = new ReadService();
		
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		List<String> devicePayloadIdList = readService.getDevicePayloadIdListFromUser(userId);
		
		if(!devicePayloadIdList.contains(payloadId))
			return Response.status(Response.Status.UNAUTHORIZED).build();
		
		if(deleteService.deletePayload(payloadId))
			return Response.status(Response.Status.OK).build();
		else
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();				
	}
}
