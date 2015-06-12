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

import com.cobweb.io.meta.LoggedUser;
import com.cobweb.io.service.ReadService;

/**
 * The Class RestLogin.
 * @author Yasith Lokuge
 * 
 */
@Path("/login")
public class RestLogin {
   
	/** The invalid. */
	private final String INVALID = "Invalid user name or password";

    
    /**
     * Login.
     *
     * @param jsonData the json data
     * @return the response
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(InputStream jsonData){
      
    	String email = null;
    	String password = null;
    	
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
			incomingData = new JSONObject(stringData.toString());
			email = (String) incomingData.get("email");
			password = (String) incomingData.get("password");
		} catch (JSONException e) {			
			e.printStackTrace();
		}
    	
    	LoggedUser loggedUser = new LoggedUser(email, password);
    	
    	
    	if(UserExists(loggedUser)){    		
    		Cookie myCookie = new Cookie("accountHref", email);    		
		    NewCookie newCookie = new NewCookie(myCookie,null,60*60,true);		  
		    return Response.ok("SUCCESS", MediaType.TEXT_PLAIN).cookie(newCookie).build();
    	}else{    			
    		return Response.ok("INVALID", MediaType.TEXT_PLAIN).build();    		
    	}
        
        
    }
    
    /**
     * User exists.
     *
     * @param loggedUser the logged user
     * @return true, if successful
     */
    public boolean UserExists(LoggedUser loggedUser){
    	
    	ReadService readService = new ReadService();
    	String message = readService.Read(loggedUser,"firstname");
    	
    	if(message != INVALID){   		
    		return true;
    	}else{   		
    		return false;
    	}
    }
    
 }