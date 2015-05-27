package com.cobweb.io.shiro;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.crypto.hash.Sha256Hash;

import com.google.common.collect.HashMultimap;

public class Safe {

	static Map<String, String> passwords = new HashMap<String, String>();
    static HashMultimap<String, String> roles = HashMultimap.create();
 
    static{
        passwords.put("pierre", encrypt("green"));
        passwords.put("paul", encrypt("blue"));
        roles.put("paul", "vip");
    }
 
    private static String encrypt(String password) {
        return new Sha256Hash(password).toString();
    }
 
    public static String getPassword(String username) {
        return passwords.get(username);
    }
 
    public static Set<String> getRoles(String username) {
        return roles.get(username);
    }
}
