package com.cobweb.io.core;

import java.net.URL;
import java.util.Set;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.VertexQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 *
 * @author Yasith Lokuge
 */

public class User implements Vertex{
	
	/** The name. */
	private String name;
	
	/** The uid. */
	private String uid;
	
	/** The email. */
	private String email;
	
	/** The image url. */
	private URL imageUrl;
	
	/** The password. */
	private String password;
	
	/** The salt. */
	private String salt;
	
	/** The is deleted. */
	private boolean isDeleted = false;
	
	/**
	 * Instantiates a new user.
	 *
	 * @param name the name
	 * @param email the email
	 * @param imageUrl the image url
	 * @param password the password
	 * @param salt the salt
	 */
	public User(String name, String email,URL imageUrl, String password, String salt){
		this.name=name;
		this.email=email;
		this.salt=salt;
		this.password=password;
		this.imageUrl=imageUrl;		
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param name the name
	 * @param email the email
	 * @param password the password
	 * @param salt the salt
	 */
	public User(String name, String email, String password, String salt){
		this.name=name;
		this.email=email;
		this.salt=salt;
		this.password=password;			
	}
		
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * Sets the uid.
	 *
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * Gets the image url.
	 *
	 * @return the imageUrl
	 */
	public URL getImageUrl() {
		return imageUrl;
	}

	/**
	 * Sets the image url.
	 *
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(URL imageUrl) {
		this.imageUrl = imageUrl;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#getProperty(java.lang.String)
	 */
	@Override
	public <T> T getProperty(String key) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#getPropertyKeys()
	 */
	@Override
	public Set<String> getPropertyKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#setProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setProperty(String key, Object value) {
		
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#removeProperty(java.lang.String)
	 */
	@Override
	public <T> T removeProperty(String key) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#remove()
	 */
	@Override
	public void remove() {
		
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#getId()
	 */
	@Override
	public Object getId() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Vertex#getEdges(com.tinkerpop.blueprints.Direction, java.lang.String[])
	 */
	@Override
	public Iterable<Edge> getEdges(Direction direction, String... labels) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Vertex#getVertices(com.tinkerpop.blueprints.Direction, java.lang.String[])
	 */
	@Override
	public Iterable<Vertex> getVertices(Direction direction, String... labels) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Vertex#query()
	 */
	@Override
	public VertexQuery query() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Vertex#addEdge(java.lang.String, com.tinkerpop.blueprints.Vertex)
	 */
	@Override
	public Edge addEdge(String label, Vertex inVertex) {
		return null;
	}

	/**
	 * Checks if is deleted.
	 *
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * Gets the salt.
	 *
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * Sets the salt.
	 *
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	

	
}
