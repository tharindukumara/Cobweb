package com.cobweb.io.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.cobweb.io.meta.User;
import com.cobweb.io.service.GraphFactory;
import com.cobweb.io.service.ReadService;
import com.cobweb.io.transformers.VertexToUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class RestUser.
 */
@Path("/user")
public class RestUser {
	
	/** The Constant JSON_ERROR. */
	private static final String JSON_ERROR				= "{\"status\":\"JSON Parsing error\"}";	
	
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(){
		
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		
		ReadService readService = new ReadService();
		GraphFactory graphFactory = new GraphFactory();
		VertexToUser vertexToUser = new VertexToUser();
		String userId = readService.getUserId(email); 
		Vertex userVertex = graphFactory.getUserVertex(userId);
		User user = vertexToUser.transform(userVertex);	
		 
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();		
		
		try {
			return objectWriter.writeValueAsString(user);
		} catch (JsonProcessingException e) {			
			return JSON_ERROR;
		}		
	}	
}
