package com.cobweb.io.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.googlecode.scopeplugin.ScopeType;
import com.googlecode.scopeplugin.annotations.In;
import com.googlecode.scopeplugin.annotations.Out;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
 
// TODO: Auto-generated Javadoc
/**
 * The Class LoginAction.
 */
public class LoginAction extends ActionSupport implements SessionAware,Action {
    
    /** The Constant serialVersionUID. */	
	private static final long serialVersionUID = -5348973997977260164L;
	
	/** The username. */
	private String username;
    
    /** The password. */
    private String password;
    
    /** The session map. */
    private Map<String, Object> sessionMap;
    
    /** The saved username. */
    private String savedUsername;
	
	/** The saved password. */
	private String savedPassword;
	
	/** The remember. */
	private boolean remember;
    
	/**
	 * Home.
	 *
	 * @return the string
	 */
	public String home() {
		return SUCCESS;
	}
	
    /**
     * Login.
     *
     * @return the string
     */
    public String login() {
        
    	if (username == null) {
			username = savedUsername;
		}

		if (password == null) {
			password = savedPassword;
		}

		if (remember) {
			savedUsername = username;
			savedPassword = password;
		}
		
		if (username.equals("root") && password.equals("admin")) {
			sessionMap.put("loginId", username);
			return SUCCESS;
		} else {
			addActionError("Please Enter Valid emailId or Password");
			return LOGIN;
		}        
    }

 
    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }
 
    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the session.
     *
     * @return the session
     */
    public Map<String, Object> getSession() {
		return sessionMap;
	}
    
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		 this.sessionMap = sessionMap;		
	}
	
	// ---------------------------- Log Out register user

	/**
	 * Logout.
	 *
	 * @return the string
	 */
	public String logout() {
		sessionMap.remove("loginId");
		addActionMessage("You Have Been Successfully Logged Out");
		return SUCCESS;
	}
	
	/**
	 * Sets the saved user name.
	 *
	 * @param savedUsername the new saved user name
	 */
	@In(scope=ScopeType.COOKIE)
    public void setSavedUserName(String savedUsername) {
        this.savedUsername = savedUsername;
    }
 
    /**
     * Sets the saved password.
     *
     * @param savedPassword the new saved password
     */
    @In(scope=ScopeType.COOKIE)
    public void setSavedPassword(String savedPassword) {
        this.savedPassword = savedPassword;
    }
 
    /**
     * Gets the saved username.
     *
     * @return the saved username
     */
    @Out(scope=ScopeType.COOKIE)
    public String getSavedUsername() {
        return this.savedUsername;
    }
 
    /**
     * Gets the saved password.
     *
     * @return the saved password
     */
    @Out(scope=ScopeType.COOKIE)
    public String getSavedPassword() {
        return this.savedPassword;
    }
 
    /**
     * Sets the remember.
     *
     * @param remember the new remember
     */
    public void setRemember(boolean remember) {
        this.remember= remember;
    }
 

	
}