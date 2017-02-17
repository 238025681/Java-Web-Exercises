/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.enumeratoin;

/**
 *
 * @author kalin
 */
public enum HttpResponseCode {
    
    OK(200, "OK"), 
    MovedPermanently(301, "Moved Permanently"), 
    Created(201, "Created"), 
    Found(302, "Found"), 
    Unauthorized(401, "Unauthorized");
   
    private int value;
    private String text;

  HttpResponseCode(int value, String text) {
    this.value = value;
    this.text = text;
  }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

  
}
