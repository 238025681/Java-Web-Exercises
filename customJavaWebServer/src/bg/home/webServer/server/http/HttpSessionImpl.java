/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.http;

import bg.home.webServer.server.interfaces.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


public class HttpSessionImpl implements HttpSession {

    private String id;
    private Map<String, String> parameters;

    public HttpSessionImpl(String id) {
        this.parameters = new HashMap<>();
        this.id = id;
    }
    
    

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void clear() {
        this.parameters.clear();
    }

    @Override
    public void add(String key, String value) {
        this.parameters.put(key, value);
    }

    @Override
    public String get(String key) {
        return  this.parameters.get(key);
    }

    @Override
    public boolean isAuthenticated() {
        return this.parameters.size() > 0;
    }
    
}
