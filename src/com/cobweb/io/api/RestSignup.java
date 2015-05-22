package com.cobweb.io.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.cobweb.io.meta.User;
import com.cobweb.io.service.CreateService;

@Path("/api/signup")
public class RestSignup {


	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signup(InputStream jsonData) {

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
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data Received: " + stringData.toString());

		JSONObject incomingData;

		try {
			
			incomingData 	= new JSONObject(stringData.toString());
			firstName 		= (String) incomingData.get("firstname");
			lastName 		= (String) incomingData.get("lastname");
			email 			= (String) incomingData.get("email");
			password 		= (String) incomingData.get("password1");
			
		} catch (JSONException e) {
			
			e.printStackTrace();
			
		}
		
		User userObj = new User(firstName,lastName, email, password,""); 
    	CreateService createService = new CreateService();
    	createService.CreateUser(userObj);
	
		Cookie myCookie = new Cookie("accountHref", email);
		NewCookie newCookie = new NewCookie(myCookie, null, 60 * 60, true);
		return Response.ok("SUCCESS", MediaType.TEXT_PLAIN).cookie(newCookie).build();
	
	}

}