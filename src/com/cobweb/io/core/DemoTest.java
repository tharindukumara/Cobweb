package com.cobweb.io.core;

import com.cobweb.io.service.AbstractService;
import com.cobweb.io.service.ReadService;

public class DemoTest implements AbstractService{

	public static void main(String args[]){
		
		
//		User userObj = new User("firstName","lastName", "email", "password",""); 
//    	CreateService createService = new CreateService();
//        Vertex v = createService.CreateUser(userObj);
//		
//        
//        System.out.println(v.getId());
		
		CobwebWeaver cobwebWeaver = new CobwebWeaver();
		ReadService readService = new ReadService();
		
		//System.out.println(readService.ReadDeviceIds("yasith1@gmail.com"));
		System.out.println(cobwebWeaver.isAuthorizedDevice("yasith1@gmail.com", "12234"));
        
//		try {
//			JSONObject jsonObject = new JSONObject("{\"firstname\":\"Yasith\",\"lastname\":\"Lokuge\",\"email\":\"yasith1@gmail.com\",\"password\":\"Yasith1991\"}");
//		
//			System.out.println(jsonObject.get("firstname"));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//				
//		List<ODocument> result =  graph.getRawGraph().query(new OSQLSynchQuery<Object>("select from User where firstname=\"Yasith\" and lastname=\"Lokuge\""));
//		if(!result.isEmpty()){
//			Vertex v = graph.getVertex(result.get(0).getIdentity());
//			System.out.println(v.getProperty("firstname"));
//		}else{
//			System.out.println("Invalid data");
//		}
//		
		
		
	}
}
