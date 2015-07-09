package com.cobweb.io.service;

import java.util.ArrayList;
import java.util.List;

import com.cobweb.io.meta.Device;
import com.cobweb.io.meta.LoggedUser;
import com.cobweb.io.meta.Sensor;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.tinkerpop.blueprints.Vertex;


/**
 * The Class ReadService.
 *
 * @author Yasith Lokuge
 */

public class ReadService implements AbstractService{

	/** The invalid. */
	private final String INVALID = "Invalid user name or password"; 
	
	/**
	 * Instantiates a new read service.
	 *
	 * @param device the device
	 */
	public ReadService(Device device){
		
	}
	
	/**
	 * Instantiates a new read service.
	 *
	 * @param sensor the sensor
	 */
	public ReadService(Sensor sensor){
		
	}
	
	/**
	 * Instantiates a new read service.
	 */
	public ReadService(){
		
	}


	
	/**
	 * Read.
	 *
	 * @param loggedUser the logged user
	 * @param data the data
	 * @return the string
	 */
	public String Read(LoggedUser loggedUser,String data){
	
		String email= loggedUser.getEmail();
		String password = loggedUser.getPassword();
		String info;
		
		List<ODocument> result =  graph.getRawGraph().query(new OSQLSynchQuery<Object>("select from User where email=\""+email+"\" and password=\""+password+"\""));
		 		
		if(!result.isEmpty()){			
			Vertex v = graph.getVertex(result.get(0).getIdentity());
			info = v.getProperty(data);
			return info;
		}else{
			return INVALID;
		}
		
	}
	
	/**
	 * Read device ids.
	 *
	 * @param email the email
	 * @return the list
	 */
	public List<String> ReadDeviceIds(String email){
		List<String> idList = new ArrayList<>();
		ODocument result =  (ODocument) graph.getRawGraph().query(new OSQLSynchQuery<Object>("Select out('UserHasDevices').idValue from User where email='"+email+"'")).get(0);
		idList = result.field("out");
		return idList;
	}

}
