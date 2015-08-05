package com.cobweb.io.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cobweb.io.mqtt.MosquittoAuth;


/**
 * The Class RestMqttAuth.
 * @author Yasith Lokuge
 */
@Path("/authplugin")
public class RestMqttAuth {

	MosquittoAuth mosquittoAuth = new MosquittoAuth();
		
	/**
	 * Auth.
	 *
	 * @param username the username
	 * @param password the password
	 * @param topic the topic
	 * @param acc the acc
	 * @return the response
	 */
	@POST
	@Path("/auth")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response auth(	@FormParam("username") String username,   // consume only username and password
    						@FormParam("password") String password,
    						@FormParam("topic") String topic,
    						@FormParam("acc") String acc) {
		
		
		if(mosquittoAuth.authCheck(username, password)){			
			return Response.status(Response.Status.OK).build();			
		}

		return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }   
	
	
	/**
	 * Superuser.
	 *
	 * @param username the username
	 * @param password the password
	 * @param topic the topic
	 * @param acc the acc
	 * @return the response
	 */
	@POST
	@Path("/superuser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response superuser(	@FormParam("username") String username,  // consume only username
    							@FormParam("password") String password,
    							@FormParam("topic") String topic,
    							@FormParam("acc") String acc) {
						
		if(username.equals("superuser")){			
			return Response.status(Response.Status.OK).build();			
		}

		return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }  
	
	/**
	 * Acl.
	 *
	 * @param username the username
	 * @param password the password
	 * @param topic the topic
	 * @param acc the acc
	 * @param clientid the clientid
	 * @return the response
	 */
	@POST
	@Path("/acl")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response acl(	@FormParam("username") String username,  // consume only username and topic
    						@FormParam("password") String password,
    						@FormParam("topic") String topic,
    						@FormParam("acc") String acc,
    						@FormParam("clientid") String clientid) {
		
		
		if(mosquittoAuth.aclCheck(username, topic)){			
			return Response.status(Response.Status.OK).build();			
		}

		return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }  
	
	
}
