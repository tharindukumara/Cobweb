package com.cobweb.io.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cobweb.io.meta.User;
import com.cobweb.io.service.CreateService;


@Path("/api/users")
public class RestUser {

	@POST
	@Path("/create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createUser(	@FormParam("firstname") String firstName,
    							@FormParam("lastname") String lastName,
    							@FormParam("email") String email,
    							@FormParam("password") String password) {
		
		
		User userObj = new User(firstName,lastName, email, password,""); 
    	CreateService createService = new CreateService(userObj);
        
		return Response.ok("SUCCESS", MediaType.TEXT_PLAIN).build();
    }                   

}
