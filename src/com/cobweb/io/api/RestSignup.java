package com.cobweb.io.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
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
import javax.ws.rs.core.UriBuilder;
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
	
	/** The Constant AUTHENTICATION_ERROR. */
	private static final String AUTHENTICATION_ERROR 	= "{\"status\":\"logout to create new account\"}";	
	
	/** The Constant JSON_ERROR. */
	private static final String JSON_ERROR				= "{\"status\":\"JSON Parsing error\"}";	

	/** The Constant CONFIRMED_EMAIL. */
	private static final String CONFIRMED_EMAIL			= "{\"status\":\"Given email has an account already\"}";	
	
	/** The Constant UNCONFIRMED_EMAIL. */
	private static final String UNCONFIRMED_EMAIL		= "{\"status\":\"Check your email and confirm\"}";	
	
	/** The Constant UNCONFIRMED_SESSION. */
	private static final String UNCONFIRMED_SESSION		= "{\"status\":\"Unconfirmed session awaiting.Check your email and confirm\"}";	
		
	/** The Constant INTERNAL_ERROR. */
	private static final String INTERNAL_ERROR			= "{\"status\":\"Internal Error\"}";	
	
	/** The Constant SUCCESS. */
	private static final String SUCCESS					= "{\"status\":\"Success\"}";	
	
	
	/**
	 * Signup.
	 *
	 * @param jsonData the json data
	 * @return the response
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String signup(InputStream jsonData, @Context UriInfo ui) {

		Subject currentUser = SecurityUtils.getSubject();
		
		if(currentUser.isAuthenticated())
			return AUTHENTICATION_ERROR;
		
		
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
			return JSON_ERROR;
		}		
		
		JSONObject incomingData;

		try {
			
			incomingData 	= new JSONObject(stringData.toString());
			firstName 		= (String) incomingData.get("firstname");
			lastName 		= (String) incomingData.get("lastname");
			email 			= (String) incomingData.get("email");
			password 		= (String) incomingData.get("password");
			
		} catch (JSONException e) {			
			return JSON_ERROR;				
		}
		
		ReadService readService = new ReadService();
		List<String> confirmedEmailList = readService.getConfirmedUserNamesList();
		List<String> unconfirmedEmailList = readService.getUnconfirmedUserNamesList();
		
		String sessionId = (String) currentUser.getSession().getId();
		
		if(confirmedEmailList.contains(email))
			return CONFIRMED_EMAIL;
		
		if(unconfirmedEmailList.contains(email))
			return UNCONFIRMED_EMAIL;
		
		if(idMap.values().contains(sessionId))
			return UNCONFIRMED_SESSION;
		
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
			return INTERNAL_ERROR;
		}
		
		return SUCCESS;	
		
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
	public Response validate(@PathParam("userId") String userId, @Context UriInfo ui) {
		
		Subject currentUser = SecurityUtils.getSubject();
		String sessionId = (String) currentUser.getSession().getId();
		ReadService readService = new ReadService();
		URI targetURIForRedirection = null;
		String baseUrl = ui.getBaseUri().toString();
		
		if(!idMap.keySet().contains(userId) || !idMap.values().contains(sessionId)){
			
			String email = readService.getEmail(userId);
			List<String> unconfirmedEmailList = readService.getUnconfirmedUserNamesList();
			
			if(unconfirmedEmailList.contains(email)){				
				idMap.remove(userId);
				idMap.put(userId, sessionId);
				
				SendMail sendMail = new SendMail();
				Email emailObj = new Email();				
								
				emailObj.setTo(email);
				emailObj.setFrom("info@cobweb.io");
				emailObj.setSubject("Confirm Your Email Address for Cobweb IO account");
				emailObj.setBody("Please click the following link to confirm your email address. \n "+baseUrl+"signup/"+userId+"\n \n Thank You!");
		    	
				try {
					sendMail.send(emailObj);
				} catch (Exception e) {		
					targetURIForRedirection = UriBuilder.fromUri(baseUrl.replace("anon/", "web-anon/internalerror.html")).build();
					return Response.seeOther(targetURIForRedirection).build();
				}				
				targetURIForRedirection = UriBuilder.fromUri(baseUrl.replace("anon/", "web-anon/reconfirm.html")).build();				
			}else{
				targetURIForRedirection = UriBuilder.fromUri(baseUrl.replace("anon/", "web-anon/error.html")).build();
			}
			
		}else{			
			UpdateService updateService = new UpdateService();			
			if(!updateService.activateUser(userId)){				
				targetURIForRedirection = UriBuilder.fromUri(baseUrl.replace("anon/", "web-anon/internalerror.html")).build();
			}else{	
				idMap.remove(userId);	
				targetURIForRedirection = UriBuilder.fromUri(baseUrl.replace("anon/", "web-anon/success.html")).build();			
			}
		}	 		
		return Response.seeOther(targetURIForRedirection).build();
	}	
	
}
