/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.interfaces.routing;

import bg.home.webServer.server.enumeratoin.HttpRequestType;
import bg.home.webServer.server.handler.RequestHandlerImpl;
import java.util.Map;

/**
 *
 * @author kalin
 */
public interface AppRouteConfig {

    AppRouteConfig addRoute(String path, RequestHandlerImpl handler);

    Iterable<Map.Entry<HttpRequestType, Map<String, RequestHandlerImpl>>> getRoutes();

}
