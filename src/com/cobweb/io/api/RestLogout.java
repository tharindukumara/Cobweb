package com.cobweb.io.api;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.shiro.SecurityUtils;

@Path ("/logout")
public class RestLogout {
		
	@GET	
	public Response logout() {
		SecurityUtils.getSubject().logout();
		URI targetURIForRedirection = UriBuilder.fromUri("http://localhost:8080/cobweb/").build();;
	    return Response.seeOther(targetURIForRedirection).build();
	}
}
