package com.cobweb.io.api;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.shiro.SecurityUtils;

@Path ("/logout")
public class RestLogout {
		
	@GET	
	public Response logout(@Context UriInfo ui) {
		String baseUrl = ui.getBaseUri().toString().replace("api/", "");
		SecurityUtils.getSubject().logout();				
		URI targetURIForRedirection = UriBuilder.fromUri(baseUrl).build();			
		return Response.seeOther(targetURIForRedirection).build();
	}	
}
