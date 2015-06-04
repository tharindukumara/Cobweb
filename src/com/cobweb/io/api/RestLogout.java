package com.cobweb.io.api;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/data/api/logout")
public class RestLogout {

    @Context    
    @GET
    public Response logout(@CookieParam("accountHref") String accountHref,String email) throws Exception {
    	
    	Cookie myCookie = new Cookie("accountHref", email);    		
	    NewCookie newCookie = new NewCookie(myCookie,null,60*60,true);		  
	    
	    return Response.ok("SUCCESS", MediaType.TEXT_PLAIN).cookie(newCookie).build();
    }
}