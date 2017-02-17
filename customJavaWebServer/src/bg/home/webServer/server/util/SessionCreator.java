/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author kalin
 */
public class SessionCreator {

    private SecureRandom random;
    private static SessionCreator sesssionCreator;

    private SessionCreator() {
        this.random = new SecureRandom();
    }

    public static SessionCreator getIstance(){
        return sesssionCreator == null ? (sesssionCreator = new SessionCreator()) : sesssionCreator;
    }
    
//TODO: Initialize the random and make the class singleton
    public String generateSessionId() {
        return new BigInteger(130, this.random).toString(32);
    }

}
