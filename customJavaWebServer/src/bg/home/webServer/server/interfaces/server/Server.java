/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.interfaces.server;

import java.net.SocketException;

/**
 *
 * @author kalin
 */
public interface Server {

    void runServer() throws SocketException;

}
