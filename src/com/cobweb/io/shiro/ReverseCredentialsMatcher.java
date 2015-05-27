package com.cobweb.io.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha256Hash;

public class ReverseCredentialsMatcher extends SimpleCredentialsMatcher {
 
    @Override
    public boolean doCredentialsMatch(AuthenticationToken tok, AuthenticationInfo info)
    {
        String tokenCredentials = charArrayToString(tok.getCredentials());
        String reverseToken = StringUtils.reverse(tokenCredentials);
        String encryptedToken = new Sha256Hash(reverseToken).toString();
 
        String accountCredentials = charArrayToString(info.getCredentials());
        return accountCredentials.equals(encryptedToken);
    }
 
    private String charArrayToString(Object credentials) {
        return new String((char[]) credentials);
    }
}