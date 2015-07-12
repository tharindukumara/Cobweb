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
	 * Gets the user names list.
	 *
	 * @return the list
	 */
	public List<String> GetUserNamesList(){
		
		List<String> userNameList = new ArrayList<>();
		List<ODocument> result =  graph.getRawGraph().query(new OSQLSynchQuery<Object>("Select email from User"));		
		
		for (ODocument oDocument : result) {
			userNameList.add(oDocument.field("email"));
		}		
		return userNameList;		
	}
	
	
	/**
	 * Check user name exists.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public boolean CheckUserNameExists(String email){				
		try {
			ODocument result = (ODocument) graph.getRawGraph().query(new OSQLSynchQuery<Object>("Select email from User where email='"+email+"'")).get(0);
			String data = result.field("email");
			return data.equals(email);
		} catch (Exception e) {			
			return false;
		}	
	}
	
	/**
	 * Check password exists.
	 *
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean CheckPasswordExists(String password){				
		try {
			ODocument result = (ODocument) graph.getRawGraph().query(new OSQLSynchQuery<Object>("Select password from User where password='"+password+"'")).get(0);
			String data = result.field("password");
			return data.equals(password);
		} catch (Exception e) {			
			return false;
		}	
	}
	
	/**
	 * Read device ids.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	public List<String> ReadDeviceIds(String userId){
		List<String> idList = new ArrayList<>();
		ODocument result =  (ODocument) graph.getRawGraph().query(new OSQLSynchQuery<Object>("Select out('UserHasDevices').idValue from User where idValue='"+userId+"'")).get(0);
		idList = result.field("out");
		return idList;
	}
	
	
	/**
	 * Gets the user id.
	 *
	 * @param email the email
	 * @return the user id
	 */
	public String getUserId(String email){
		ODocument result =  (ODocument) graph.getRawGraph().query(new OSQLSynchQuery<Object>("Select idValue from User where email='"+email+"'")).get(0);
		String idValue = result.field("idValue");		
		return idValue;
	}

	/**
	 * Gets the salt.
	 *
	 * @param userId the user id
	 * @return the salt
	 */
	public String getSalt(String userId){		
		ODocument result =  (ODocument) graph.getRawGraph().query(new OSQLSynchQuery<Object>("Select salt from User where idValue='"+userId+"'")).get(0);
		String salt = result.field("salt");
		return salt;
	}

}
