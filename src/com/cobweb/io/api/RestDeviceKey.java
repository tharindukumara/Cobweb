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
 * The Class RestDeviceKey.
 * @author Yasith Lokuge
 */

@Path ("/device/key")
public class RestDeviceKey {

	/** The Constant ERROR. */
	private static final String ERROR				= "ERROR";
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path ("{deviceId}")
	public String getKey(@PathParam("deviceId") String deviceId){
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		List<String> deviceIdList 			= readService.getDeviceIdList(userId);
		
		if(!deviceIdList.contains(deviceId))					
			return ERROR;	
		
		String key = readService.getKeyFromDeviceId(deviceId);
		return key;		
	}
}
