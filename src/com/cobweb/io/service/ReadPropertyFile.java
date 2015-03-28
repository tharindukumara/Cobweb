package com.cobweb.io.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadPropertyFile.
 *
 * @author Yasith Lokuge
 */

public class ReadPropertyFile {
	
	
	/** The database. */
	private String DATABASE = "database_path";
	
	/** The db user. */
	private String DB_USER  = "database_username";
	
	/** The db password. */
	private String DB_PASSWORD = "database_password";
	
	/** The database url. */
	private String databaseUrl;
	
	/** The database user name. */
	private String databaseUserName;
	
	/** The database password. */
	private String databasePassword;
	
	/**
	 * Instantiates a new read property file.
	 */
	public ReadPropertyFile(){		
		
		Properties settings = new Properties();
    	InputStream input = null;
    	
    	try {
 
    		input = new FileInputStream("cobweb.properties");   		
    		settings.load(input);              
    		databaseUrl = settings.getProperty(DATABASE);
    	    databaseUserName = settings.getProperty(DB_USER);
    	    databasePassword = settings.getProperty(DB_PASSWORD);
 
    	} catch (IOException ex) {
    		
    		ex.printStackTrace();
        
    	} finally{
        	
        	if(input!=null){
        		try {
				input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
		
	}
	
	/**
	 * Gets the DB url.
	 *
	 * @return the DB url
	 */
	public String getDBUrl(){		
		return databaseUrl;
	}
	
	/**
	 * Gets the DB user.
	 *
	 * @return the DB user
	 */
	public String getDBUser(){
		return databaseUserName;
	}
	
	/**
	 * Gets the DB pass.
	 *
	 * @return the DB pass
	 */
	public String getDBPass(){
		return databasePassword;
	}
	

}
