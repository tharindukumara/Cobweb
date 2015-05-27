package com.cobweb.io.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

@Path("/api")
public class RestDemo {

//	 	@POST
//	    @Path("/post")	 
//	    @Consumes(MediaType.TEXT_PLAIN)
//	 	@RequiresAuthentication
//	    public Response createDataInJSON(String data) { 
//
//	        String result = "Data post: "+data;
//
//	        System.out.println(data);
//	        return Response.status(201).entity(result).build(); 
//	    }
	
	
	@GET
	public String get() {
        return "OK";
    }
}
