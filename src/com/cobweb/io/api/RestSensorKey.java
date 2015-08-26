package com.cobweb.io.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.cobweb.io.service.ReadService;

/**
 * The Class RestSensorKey.
 * @author Yasith Lokuge
 */

@Path ("/sensor/key")
public class RestSensorKey {

	/** The Constant ERROR. */
	private static final String ERROR				= "ERROR";
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path ("{sensorId}")
	public String getKey(@PathParam("sensorId") String sensorId){
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		List<String> sensorIdList 			= readService.getSensorIdList(userId);
		
		if(!sensorIdList.contains(sensorId))					
			return ERROR;	
		
		String key = readService.getKeyFromSensorId(sensorId);
		return key;		
	}
}
