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
	public Vertex getUserVertex(String userId){		
		
		
		Iterable<Vertex> v 		= graph.command(new OCommandSQL("select from User where idValue=\""+userId+"\"")).execute();		
		return graph.getVertex(v.iterator().next().getId());		
	}
	

	/**
	 * Gets the device vertex.
	 *
	 * @param deviceId the device id
	 * @return the device vertex
	 */
	public Vertex getDeviceVertex(String deviceId){
		
		Iterable<Vertex> v 		= graph.command(new OCommandSQL("select from Device where idValue=\""+deviceId+"\"")).execute();		
		return graph.getVertex(v.iterator().next().getId());	
	}
	
	
	/**
	 * Gets the sensor vertex.
	 *
	 * @param sensorId the sensor id
	 * @return the sensor vertex
	 */
	public Vertex getSensorVertex(String sensorId){
		
		Iterable<Vertex> v 		= graph.command(new OCommandSQL("select from Sensor where idValue=\""+sensorId+"\"")).execute();		
		return graph.getVertex(v.iterator().next().getId());	
	}
		
	/**
	 * Gets the payload vertex.
	 *
	 * @param payloadId the payload id
	 * @return the payload vertex
	 */
	public Vertex getPayloadVertex(String payloadId){
		
		Iterable<Vertex> v 		= graph.command(new OCommandSQL("select from Payload where idValue=\""+payloadId+"\"")).execute();		
		return graph.getVertex(v.iterator().next().getId());	
	}

	
	
}
