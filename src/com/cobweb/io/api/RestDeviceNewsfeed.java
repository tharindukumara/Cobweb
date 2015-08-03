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


/**
 * The Class RestDeviceNewsfeed.
 * @author Yasith Lokuge
 */
@Path("/device/newsfeed")
public class RestDeviceNewsfeed {

	/** The Constant JSON_ERROR. */
	private static final String JSON_ERROR				= "{\"status\":\"JSON Parsing error\"}";	
	
	
	/**
	 * Gets the device payload.
	 *
	 * @return the device payload
	 */
	@GET
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
			return JSON_ERROR;
		}
		
	}	
}
