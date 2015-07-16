package com.cobweb.io.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.cobweb.io.meta.Payload;
import com.cobweb.io.service.ReadService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@Path("/sensor/message")
public class RestSensorPayload {

private static final String UNKNOWN_SENSOR_ID = "Unknown Sensor Id";
	
	@GET
	@Path("{sensorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSensorPayload(@PathParam("sensorId") String sensorId){
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		List<String> sensorIdList = readService.getSensorIdList(userId);
		
		if(!sensorIdList.contains(sensorId))
			return UNKNOWN_SENSOR_ID;
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		List<Payload> payloadList = new ArrayList<Payload>(); 
		payloadList = readService.getSensorPayloadList(sensorId);
		
		try {
			return objectWriter.writeValueAsString(payloadList);
		} catch (JsonProcessingException e) {		
			return e.toString();
		}
		
	}
}
