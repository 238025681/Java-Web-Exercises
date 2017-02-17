/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.interfaces.routing;

import bg.home.webServer.server.enumeratoin.HttpRequestType;
import java.util.Map;

/**
 *
 * @author kalin
 */
public interface ServerRouteConfig {

    Map<HttpRequestType, Map<String, RoutingContext>> getRoutes();
}
