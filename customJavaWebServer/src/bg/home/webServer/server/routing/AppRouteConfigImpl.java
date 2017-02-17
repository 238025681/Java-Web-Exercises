/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.routing;

import bg.home.webServer.server.interfaces.routing.AppRouteConfig;
import bg.home.webServer.server.handler.RequestHandlerImpl;
import bg.home.webServer.server.enumeratoin.HttpRequestType;
import java.util.HashMap;
import java.util.Map;

public class AppRouteConfigImpl implements AppRouteConfig {

    private Map<HttpRequestType, Map<String, RequestHandlerImpl>> routes;

    public AppRouteConfigImpl() {
        this.routes = new HashMap<>();

        for (HttpRequestType value : HttpRequestType.values()) {
            this.routes.put(value, new HashMap<>());
        }
    }

    @Override
    public AppRouteConfig addRoute(String path, RequestHandlerImpl handler) {
        if (handler.getClass().toString().toLowerCase().contains("get")) {
            this.routes.get(HttpRequestType.GET).put(path, handler);

        } else if (handler.getClass().toString().toLowerCase().contains("post")) {
            this.routes.get(HttpRequestType.POST).put(path, handler);

        }
        return this;
    }

    @Override
    public Iterable<Map.Entry<HttpRequestType, Map<String, RequestHandlerImpl>>> getRoutes() {
        return this.routes.entrySet();
    }

}
