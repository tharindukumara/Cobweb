package com.cobweb.io.core;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;




import com.cobweb.io.meta.User;
import com.cobweb.io.service.AbstractService;
import com.cobweb.io.service.CreateService;
import com.cobweb.io.service.ReadService;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.tinkerpop.blueprints.Vertex;

public class DemoTest implements AbstractService{

	public static void main(String args[]){
		
		
		User userObj = new User("firstName","lastName", "email", "password",""); 
    	CreateService createService = new CreateService();
        Vertex v = createService.CreateUser(userObj);
		
        System.out.println(v.getId());
        
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
