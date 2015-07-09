package com.cobweb.io.core;
 
import java.math.BigInteger;
import java.security.SecureRandom;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.SimpleByteSource;
 
public class HashGenerator{

    public String generateSalt() {
    	return new BigInteger(250, new SecureRandom()).toString(32);
    }    
    
    public String generateHash(String password){
    	Sha256Hash hash = new Sha256Hash(password);
    	return hash.toHex();
    }
    
    public String saltHashPassword(String password, String salt) {        
        Sha256Hash hash = new Sha256Hash(password, (new SimpleByteSource(salt)).getBytes());        
        return hash.toHex();
    }
    
}