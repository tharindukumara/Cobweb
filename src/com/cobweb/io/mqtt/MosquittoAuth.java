package com.cobweb.io.mqtt;

import com.cobweb.io.service.ReadService;
import com.cobweb.io.utils.HashGenerator;

/**
 * The Class MosquittoAuth.
 * @author Yasith Lokuge
 */
public class MosquittoAuth {

	/** The read service. */
	ReadService readService = new ReadService();
	
	/**
	 * Check user name.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	public boolean checkUserName(String username){
		return readService.checkUserNameExists(username);
	}
	
	/**
	 * Check password.
	 *
	 * @param email the email
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean checkPassword(String email ,String password){
		
		HashGenerator hashGenerator = new HashGenerator();
		String salt = readService.getSalt(email);
		String saltedPass = hashGenerator.saltHashPassword(password, salt);
		
		return readService.checkPasswordExists(saltedPass);		 
	}
}
