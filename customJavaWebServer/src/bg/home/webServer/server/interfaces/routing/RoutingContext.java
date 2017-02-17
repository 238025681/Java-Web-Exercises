/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.interfaces.routing;

import bg.home.webServer.server.handler.RequestHandlerImpl;

/**
 *
 * @author kalin
 */
public interface RoutingContext {

    RequestHandlerImpl getHandler();

    Iterable<String> getParamNames();
}
