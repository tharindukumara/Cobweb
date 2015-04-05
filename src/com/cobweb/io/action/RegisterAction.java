package com.cobweb.io.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cobweb.io.meta.User;
import com.cobweb.io.service.CreateService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


/**
 * The Class RegisterAction.
 * @author Yasith Lokuge
 */
public class RegisterAction extends ActionSupport implements SessionAware,Action{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3550161316671464570L;
	
	/** The first name. */
	private String firstName;
    
    /** The last name. */
    private String lastName;
    
    /** The session map. */
    private Map<String, Object> sessionMap;
    
    /** The email. */
    private String email;
    
    /** The password. */
    private String password;
    
    /** The password reentered. */
    private String passwordReentered;
    
    
    /**
     * Register.
     *
     * @return the string
     */
    public String register(){
    	sessionMap.put("loginId", email);
    	User userObj = new User(firstName,lastName, email, password,""); 
    	CreateService createService = new CreateService(userObj);
    	return SUCCESS;
    }

	/**
	 * Gets the first name.
	 *
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the session.
	 *
	 * @return the sessionMap
	 */
	public Map<String, Object> getSession() {
		return sessionMap;
	}

	/** 
	 * @param sessionMap the sessionMap to set
	 */
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
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
	 * Gets the password reentered.
	 *
	 * @return the passwordReentered
	 */
	public String getPasswordReentered() {
		return passwordReentered;
	}

	/**
	 * Sets the password reentered.
	 *
	 * @param passwordReentered the passwordReentered to set
	 */
	public void setPasswordReentered(String passwordReentered) {
		this.passwordReentered = passwordReentered;
	}

	
}
