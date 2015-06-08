package com.cobweb.io.service;

import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.Vertex;


/**
 * A factory for creating Graph objects.
 * @author YasithLokuge
 * 
 */
public class GraphFactory implements AbstractService{

	
	/**
	 * Gets the user vertex.
	 *
	 * @param userId the user id
	 * @return the user vertex
	 */
	public Vertex getUserVertex(String email){		
		
		Vertex v 		= graph.command(new OCommandSQL("select from User where email=\""+email+"\"")).execute();		
		return v;		
	}
	
	/**
	 * Gets the user vertex.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the user vertex
	 */
	public Vertex getUserVertex(String email, String password){		
		
		Vertex v 		= graph.command(new OCommandSQL("select from User where email=\""+email+"\" and password=\""+password+"\"")).execute();		
		return v;		
	}

	
	/**
	 * Gets the device vertex.
	 *
	 * @param deviceId the device id
	 * @return the device vertex
	 */
	public Vertex getDeviceVertex(String deviceId){
		
		Vertex v 		= graph.command(new OCommandSQL("select from User where id=\""+deviceId+"\"")).execute();		
		return v;	
	}
	
	
	/**
	 * Gets the sensor vertex.
	 *
	 * @param sensorId the sensor id
	 * @return the sensor vertex
	 */
	public Vertex getSensorVertex(String sensorId){
		
		Vertex v 		= graph.command(new OCommandSQL("select from User where id=\""+sensorId+"\"")).execute();		
		return v;	
	}
		
	/**
	 * Gets the payload vertex.
	 *
	 * @param payloadId the payload id
	 * @return the payload vertex
	 */
	public Vertex getPayloadVertex(String payloadId){
		
		Vertex v 		= graph.command(new OCommandSQL("select from User where id=\""+payloadId+"\"")).execute();		
		return v;	
	}

	
	
}
