/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.interfaces.http;

/**
 *
 * @author kalin
 */
public interface HttpCookie {

    void addCookie(String key, String value);

    void removeCookie(String key);

    boolean contains(String key);

    String getCookie(String name);

}
