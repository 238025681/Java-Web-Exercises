/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.interfaces.routing;

import bg.home.webServer.server.handler.RequestHandlerImpl;
import bg.home.webServer.server.routing.ControllerActionPair;
import java.util.Map;

/**
 *
 * @author kalin
 */
public interface RoutingContext {

    RequestHandlerImpl getHandler();

    Map<Integer, Class> getArgumentMapping();
    
    ControllerActionPair getActionPair();
}
