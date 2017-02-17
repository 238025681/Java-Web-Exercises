/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer;

import bg.home.webServer.app.MainApplication;
import bg.home.webServer.server.interfaces.routing.AppRouteConfig;
import bg.home.webServer.server.ServerImpl;
import bg.home.webServer.server.interfaces.Application;
import bg.home.webServer.server.interfaces.server.Server;
import bg.home.webServer.server.routing.AppRouteConfigImpl;
import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author kalin
 */
public class Main {
    
    public static void main(String[] args) {
        try {
            Application app = new MainApplication();
            AppRouteConfig appRouteConfig = new AppRouteConfigImpl();
            app.start(appRouteConfig);
            
            ServerSocket serverSocket = new ServerSocket(8080);
            Server server = new ServerImpl(serverSocket, appRouteConfig);
            
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
