package com.cobweb.io.meta;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 *
 * @author Yasith Lokuge
 */

@JsonIgnoreProperties({"salt","deleted","password","role"})
public class User{
	
	/** The firstname. */
	private String firstName 	= "User";
	
	/** The lastname. */
	private String lastName 	= "User";
	
	/** The uid. */
	private String uid 		= "00000";
	
	/** The email. */
	private String email 	= "info@cobweb.io";
		
	/** The password. */
	private String password = "admin";
	
	/** The salt. */
	private String salt		= "admin";
	
	/** The is deleted. */
	private boolean isDeleted = false;	
	
	/** The role. */
	private String role = "standard";		
	
	
	
	/**
	 * Instantiates a new user.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 * @param password the password
	 * @param salt the salt
	 */
	public User(String firstName,String lastName,String email, String password, String salt){
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.salt=salt;
		this.password=password;	
		
		UUID uuid = UUID.randomUUID();
		uid = uuid.toString();			
	}
		
	/**
	 * Gets the fisrtName.
	 *
	 * @return the name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the firstname.
	 *
	 * @param name the name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	/**
	 * Gets the lastName.
	 *
	 * @return the name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the lastname.
	 *
	 * @param name the name to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	

	
}
