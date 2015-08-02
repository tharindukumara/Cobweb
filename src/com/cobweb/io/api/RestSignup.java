package com.cobweb.io.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.cobweb.io.meta.Email;
import com.cobweb.io.meta.User;
import com.cobweb.io.service.CreateService;
import com.cobweb.io.service.ReadService;
import com.cobweb.io.service.UpdateService;
import com.cobweb.io.utils.HashGenerator;
import com.cobweb.io.utils.SendMail;


/**
 * The Class RestSignup.
 * @author Yasith Lokuge
 */
@Path("/signup")
public class RestSignup {	
	
	/** The Constant idMap. */
	private static final Map<String, String> idMap = new HashMap<String, String>();
		
	/**
	 * Signup.
	 *
	 * @param jsonData the json data
	 * @return the response
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signup(InputStream jsonData, @Context UriInfo ui) {

		Subject currentUser = SecurityUtils.getSubject();
		
		if(currentUser.isAuthenticated())
			return Response.status(Status.NOT_ACCEPTABLE).build();
		
		
		String firstName 	= null;
		String lastName 	= null;
		String email 		= null;
		String password 	= null;

		StringBuilder stringData = new StringBuilder();
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(jsonData));
			String line = null;
			while ((line = in.readLine()) != null) {
				stringData.append(line);
			}
		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}		
		
		JSONObject incomingData;

		try {
			
			incomingData 	= new JSONObject(stringData.toString());
			firstName 		= (String) incomingData.get("firstname");
			lastName 		= (String) incomingData.get("lastname");
			email 			= (String) incomingData.get("email");
			password 		= (String) incomingData.get("password");
			
		} catch (JSONException e) {			
			return Response.status(Status.NOT_ACCEPTABLE).build();				
		}
		
		ReadService readService = new ReadService();
		List<String> confirmedEmailList = readService.getConfirmedUserNamesList();
		List<String> unconfirmedEmailList = readService.getUnconfirmedUserNamesList();
		
		String sessionId = (String) currentUser.getSession().getId();
		
		if(confirmedEmailList.contains(email))
			return Response.status(Status.NOT_ACCEPTABLE).build();
		
		if(unconfirmedEmailList.contains(email))
			return Response.status(Status.NOT_ACCEPTABLE).build();
		
		if(idMap.values().contains(sessionId))
			return Response.status(Status.NOT_ACCEPTABLE).build();
		
		HashGenerator hashGenerator = new HashGenerator();
		String salt = hashGenerator.generateSalt();		
		User userObj = new User(firstName,lastName, email, hashGenerator.saltHashPassword(password,salt) ,salt); 
    	CreateService createService = new CreateService();
    	userObj.setDeleted(true);
    	createService.CreateUser(userObj);
    	
		String userId	= userObj.getUid();
		
		
		idMap.put(userId, sessionId);
		
		SendMail sendMail = new SendMail();
		Email emailObj = new Email();
		
		String baseUrl = ui.getBaseUri().toString();
		
		emailObj.setTo(email);
		emailObj.setFrom("info@cobweb.io");
		emailObj.setSubject("Confirm Your Email Address for Cobweb IO account");
		emailObj.setBody("Hi "+firstName+" "+lastName+",\n \n Welcome to Cobweb.IO \n Congratulations, Your account has been successfully created, please click the following link to confirm your email address. \n "+baseUrl+"signup/"+userId+"\n \n Thank You!");
    	
		try {
			sendMail.send(emailObj);
		} catch (Exception e) {			
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		
		return Response.status(Status.ACCEPTED).build();	
		
	}
	
	
	/**
	 * Validate.
	 *
	 * @param userId the user id
	 * @return the string
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{userId}")
	public String validate(@PathParam("userId") String userId) {
		
		if(!idMap.keySet().contains(userId))
			return "Invalid Parameter. Request Cancelled";
		
		UpdateService updateService = new UpdateService();
		
		if(!updateService.activateUser(userId))
			return "Internal Error. Request Cancelled";
		
		idMap.remove(userId);	
		return "Your account has been successfully created. Goto home page and login to continue.";		
	}	
	
}
