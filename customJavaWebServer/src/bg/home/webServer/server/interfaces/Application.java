/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.interfaces;

import bg.home.webServer.server.interfaces.routing.AppRouteConfig;

/**
 *
 * @author kalin
 */
public interface Application {

    void start(AppRouteConfig appRouteConfig);
}
