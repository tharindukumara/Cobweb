package com.cobweb.io.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authplugin")
public class RestMqttAuth {

	@GET
	public String test(){
		return "SUCCESS";
	}
	
	@POST
	@Path("/auth")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response auth(	@FormParam("username") String username,   // consume only username and password
    						@FormParam("password") String password,
    						@FormParam("topic") String topic,
    						@FormParam("acc") String acc) {
		//System.out.println("auth");
		//System.out.println(username+" : "+password+" : "+topic);
		if(username.equals("yasith") && password.equals("qwerty")){			
			return Response.status(Response.Status.OK).build();			
		}

		return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }   
	
	
	@POST
	@Path("/superuser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response superuser(	@FormParam("username") String username,  // consume only username
    							@FormParam("password") String password,
    							@FormParam("topic") String topic,
    							@FormParam("acc") String acc) {
		
		//System.out.println("superuser");
		//System.out.println(username+" : "+password+" : "+topic);
		
		if(username.equals("c08w385up3ru53r")){			
			return Response.status(Response.Status.OK).build();			
		}

		return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }  
	
	@POST
	@Path("/acl")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response acl(	@FormParam("username") String username,  // consume only username and topic
    						@FormParam("password") String password,
    						@FormParam("topic") String topic,
    						@FormParam("acc") String acc,
    						@FormParam("clientid") String clientid) {
		
		//System.out.println("acl");
		//System.out.println(username+" : "+password+" : "+topic);

		if(username.equals("yasith") && topic.equals("temp")){			
			return Response.status(Response.Status.OK).build();			
		}

		return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }  
	
	
}
