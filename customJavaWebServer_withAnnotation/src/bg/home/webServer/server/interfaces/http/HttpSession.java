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
public interface HttpSession {

    String getId();

    void clear();

    void add(String key, String value);

    String get(String key);

    boolean isAuthenticated();

}
