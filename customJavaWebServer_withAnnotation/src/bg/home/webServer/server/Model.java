/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kalin
 */
public class Model {
  
    private Map<String, Object> objParams;

    public Model() {
        this.objParams = new HashMap<>();
    }
    
    public void add(String key, Object value){
        this.objParams.put(key, value);
    }
    
    public Object get (String key){
        return this.objParams.get(key);
    }
    
    
}
