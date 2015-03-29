package com.cobweb.io.service;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceSamurai.
 *
 * @author Yasith Lokuge
 */

public class ServiceSamurai {
	
	/** The dburl. */
	private String DBURL;
	
	/** The dbuser. */
	private String DBUSER;
	
	/** The dbpass. */
	private String DBPASS;
	
	/** The instance. */
	private static ServiceSamurai instance = null;
	
	/**
	 * Instantiates a new service samurai.
	 */
	protected ServiceSamurai() {
	      // Exists only to defeat instantiation.
	}
	
	/**
	 * Gets the single instance of ServiceSamurai.
	 *
	 * @return single instance of ServiceSamurai
	 */
	public static ServiceSamurai getInstance() {	  
		if(instance == null) {	     
			instance = new ServiceSamurai();	  
		}      
		return instance;
	}
	
	/**
	 * Gets the graph db.
	 *
	 * @return the graph db
	 */
	public OrientGraph getGraphDB(){		
		
		ReadPropertyFile readFile = new ReadPropertyFile();		
		
		DBURL = readFile.getDBUrl();
		DBUSER = readFile.getDBUser();
		DBPASS = readFile.getDBPass();
		
		OrientGraph graph = new OrientGraph(DBURL,DBUSER, DBPASS);
		
		return graph;
		
		
	}
	
	
	   
	   

}
