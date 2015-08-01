package com.cobweb.io.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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


/**
 * The Class RestGetFriends.
 * @author Yasith Lokuge
 */
@Path("/friends")
public class RestGetFriends {

	/** The Constant JSON_ERROR. */
	private static final String JSON_ERROR				= "{\"error\":\"JSON Parsing error\"}";	
	
	/** The Constant UNKNOWN_SENSOR_ID. */
	private static final String UNKNOWN_FRIEND_ID 		= "{\"error\":\"Unknown Friend Id or Unauthorized \"}";
	
	
	/**
	 * Gets the friend.
	 *
	 * @param friendId the friend id
	 * @return the friend
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{friendId}")
	public String getFriend(@PathParam("friendId") String friendId){
	
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		List<String> friendIdList = readService.getFriendsIdList(userId);
				
		if(!(friendId.equals(userId) || friendIdList.contains(friendId)))
			return UNKNOWN_FRIEND_ID;
		
		GraphFactory graphFactory = new GraphFactory();
		Vertex userVertex = graphFactory.getUserVertex(userId);
		VertexToUser toUser = new VertexToUser();
		User user = toUser.transform(userVertex);
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

		try {
			return objectWriter.writeValueAsString(user);
		} catch (JsonProcessingException e) {		
			return JSON_ERROR;
		}
	}	
}
