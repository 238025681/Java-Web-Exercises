/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server;

import bg.home.webServer.server.interfaces.http.HttpSession;
import bg.home.webServer.server.interfaces.routing.AppRouteConfig;
import bg.home.webServer.server.interfaces.server.Server;
import bg.home.webServer.server.routing.ServerRouteConfigImpl;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;

/**
 *
 * @author kalin
 */
public class ServerImpl implements Server{

    private final ServerSocket serverSocket;
    private boolean isRunning;
    private final ServerRouteConfigImpl serverRouteConfig;
    private Map<String, HttpSession> sessionMap;

    public ServerImpl(ServerSocket serverSocket, AppRouteConfig appRouteConfig) {
        this.serverSocket = serverSocket;
        this.serverRouteConfig = new ServerRouteConfigImpl(appRouteConfig);
        this.sessionMap = new HashMap<>();
    }
    
    @Override
    public void runServer() throws SocketException {
        System.out.println("Server started");
        
        this.isRunning = true;
        this.serverSocket.setSoTimeout(10000);
        this.acceptRequest();
    }

    private void acceptRequest() {
        while (this.isRunning) {
            try(Socket clientSocket = this.serverSocket.accept()) {
               clientSocket.setSoTimeout(10000);
                ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket,  this.serverRouteConfig, this.sessionMap);
                FutureTask<?> futureTask = new FutureTask<>(connectionHandler, null);
                futureTask.run();
            } catch (IOException ignored) {
            }
            
        }
    }
    
}
