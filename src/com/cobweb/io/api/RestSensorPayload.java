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


@Path("/sensor/message")
public class RestSensorPayload {

/** The Constant UNKNOWN_SENSOR_ID. */
private static final String UNKNOWN_SENSOR_ID 	= "{\"error\":\"Unknown Sensor Id or Unauthorized Sensor\"}";

/** The Constant JSON_ERROR. */
private static final String JSON_ERROR			= "{\"error\":\"JSON Parsing error\"}";	

	/**
	 * Gets the sensor payload.
	 *
	 * @param sensorId the sensor id
	 * @return the sensor payload
	 */
	@GET
	@Path("{sensorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSensorPayload(@PathParam("sensorId") String sensorId){
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		List<String> sensorIdList 			= readService.getSensorIdList(userId);
		List<String> subscribedSensorIdList = readService.getSubscribedSensorIdList(userId);
		
		if(!(sensorIdList.contains(sensorId) || subscribedSensorIdList.contains(sensorId)))					
			return UNKNOWN_SENSOR_ID;	
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		List<Payload> payloadList = new ArrayList<Payload>(); 
		payloadList = readService.getSensorPayloadList(sensorId);
		
		try {
			return objectWriter.writeValueAsString(payloadList);
		} catch (JsonProcessingException e) {		
			return JSON_ERROR;
		}		
	}
	
	/**
	 * Creates the sensor payload.
	 *
	 * @param sensorId the sensor id
	 * @param message the message
	 * @return the response
	 */
	@POST	
	@Path("{sensorId}")	
	@Consumes(MediaType.TEXT_PLAIN)	
	public Response createSensorPayload(@PathParam("sensorId") String sensorId, String message) {
		
		Payload payload 			= new Payload(message);
		CobwebWeaver cobwebWeaver 	= new CobwebWeaver();
		ReadService readService 	= new ReadService();
		
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		List<String> sensorIdList 			= readService.getSensorIdList(userId);
		
		if(!sensorIdList.contains(sensorId))					
			return Response.status(Response.Status.UNAUTHORIZED).build();
		
		cobwebWeaver.addSensorPayload(sensorId, payload);		
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
	public Response deleteSensorPayload(@PathParam("payloadId") String payloadId) {
		
		DeleteService deleteService = new DeleteService();
		ReadService readService = new ReadService();
		
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		List<String> sensorPayloadIdList = readService.getSensorPayloadIdListFromUser(userId);
		
		if(!sensorPayloadIdList.contains(payloadId))
			return Response.status(Response.Status.UNAUTHORIZED).build();
		
		if(deleteService.deletePayload(payloadId))
			return Response.status(Response.Status.OK).build();
		else
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();				
	}
}
