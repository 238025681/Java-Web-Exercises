/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.app;

import bg.home.webServer.app.controller.HomeController;
import bg.home.webServer.app.controller.UserController;
import bg.home.webServer.server.interfaces.routing.AppRouteConfig;
import bg.home.webServer.server.handler.GetHandler;
import bg.home.webServer.server.interfaces.Application;

/**
 *
 * @author kalin
 */
public class MainApplication implements Application {

    @Override
    public void start(AppRouteConfig appRouteConfig) {
//        appRouteConfig.addRoute("/", new GetHandler((httpContext
//                -> new HomeController().index())));

        appRouteConfig.addRoute("/users/{(?<name>[a-z]+)}/details", 
                new GetHandler(httpContext -> new HomeController().index(httpContext.getHttpRequest().getURLParameters().get("name"))));
    }

}
