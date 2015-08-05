package com.cobweb.io.transformers;

import com.cobweb.io.meta.User;
import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class VertexToUser.
 * @author Yasith Lokuge
 */
public class VertexToUser {
	
	/**
	 * Transform.
	 *
	 * @param userVertex the user vertex
	 * @return the user
	 */
	public User transform(Vertex userVertex){
		
		String firstName 	= userVertex.getProperty("firstname");
		String lastName  	= userVertex.getProperty("lastname");
		String id        	= userVertex.getProperty("idValue");
		String email		= userVertex.getProperty("email");
		String password		= userVertex.getProperty("password");
		String salt 		= userVertex.getProperty("salt");
		String role			= userVertex.getProperty("role");
		
		User user = new User(firstName, lastName, email, password, salt);
		
		user.setRole(role);
		user.setUid(id);
		user.setDeleted(false);
		user.setEmailHash();
		
		return user;	
	}

}
