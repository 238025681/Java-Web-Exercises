/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer;

import bg.home.webServer.server.ServerImpl;
import bg.home.webServer.server.interfaces.server.Server;
import bg.home.webServer.server.provider.ClassProvider;
import bg.home.webServer.server.provider.ClassProviderImpl;
import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author kalin
 */
public class Main {
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
           ServerSocket serverSocket = new ServerSocket(8080);
            ClassProvider classProvider = new ClassProviderImpl();
            Server server = new ServerImpl(serverSocket, classProvider);
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
