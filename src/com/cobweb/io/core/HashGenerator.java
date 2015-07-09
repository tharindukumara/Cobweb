package com.cobweb.io.core;
 
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
 
public class HashGenerator 
{

	private SecureRandom random = new SecureRandom();
    
	public String generateHash(String password)throws Exception{
    	
 
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        } 
 
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	return hexString.toString();
    }
    

    public String generateSalt() {
      return new BigInteger(130, random).toString(32);
    }    
    
}