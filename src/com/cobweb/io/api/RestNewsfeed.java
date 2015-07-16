package com.cobweb.io.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.cobweb.io.meta.Payload;
import com.cobweb.io.service.ReadService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@Path("/newsfeed")
public class RestNewsfeed {

	
	@GET
	@Path("/device")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDevicePayload(){
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		List<Payload> devicePayloadList = readService.getSubscribedDevicePayloads(userId);
			
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		try {
			return objectWriter.writeValueAsString(devicePayloadList);
		} catch (JsonProcessingException e) {		
			return e.toString();
		}
		
	}
	
	@GET
	@Path("/sensor")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSensorPayload(){
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		List<Payload> sensorPayloadList = readService.getSubscribedSensorPayloads(userId);
			
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		try {
			return objectWriter.writeValueAsString(sensorPayloadList);
		} catch (JsonProcessingException e) {		
			return e.toString();
		}
		
	}
	
}
