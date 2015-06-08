package com.cobweb.io.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


/**
 * The Class RestDemo.
 */
@Path("/data")
public class RestDemo {

	/**
	 * Gets the.
	 *
	 * @return the string
	 */
	@GET	
	@Path("/get")
	public String get() {
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println(currentUser.getPrincipal());
		return "OK";
	}
	
	/**
	 * Creates the data in json.
	 *
	 * @param data the data
	 * @return the response
	 */
	@POST
	@Path("/post")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response createDataInJSON(String data) {

		String result = "Data post: " + data;

		System.out.println(data);
		return Response.status(201).entity(result).build();
	}

}
