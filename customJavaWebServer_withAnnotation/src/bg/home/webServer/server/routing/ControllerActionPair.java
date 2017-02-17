/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.routing;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

/**
 *
 * @author kalin
 */
public class ControllerActionPair {
    private final Method action;
    private final Object controller;
    private final Map<Integer, Class> argumentMapping;

    public ControllerActionPair(Method action, Object controller, Map<Integer, Class> argumentMapping) {
        this.action = action;
        this.controller = controller;
        this.argumentMapping = argumentMapping;
    }

    public Method getAction() {
        return this.action;
    }

    public Object getController() {
        return this.controller;
    }

    public Map<Integer, Class> getArgumentMapping() {
        return Collections.unmodifiableMap(this.argumentMapping);
    }
    
    
    
    
}
