package com.cobweb.io.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/device/manage")
public class RestManageDevice {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRequests(){
		return null;
		
	}
}
