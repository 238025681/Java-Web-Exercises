/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server;

import bg.home.webServer.server.exeption.BadRequestExeption;
import bg.home.webServer.server.handler.HttpHandler;
import bg.home.webServer.server.interfaces.http.HttpContext;
import bg.home.webServer.server.http.HttpContextImpl;
import bg.home.webServer.server.interfaces.http.HttpSession;
import bg.home.webServer.server.interfaces.routing.ServerRouteConfig;
import bg.home.webServer.server.routing.ServerRouteConfigImpl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

/**
 *
 * @author kalin
 */
public class ConnectionHandler implements Runnable{

    private final Socket clientSocket;
    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;
    private final ServerRouteConfig serverRouteConfig;
    private final Map<String, HttpSession> sessionMap;

    ConnectionHandler(Socket clientSocket, ServerRouteConfig serverRouteConfig, Map<String, HttpSession> sessionMap) throws IOException {
        this.clientSocket = clientSocket;
        
        this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.printWriter = new PrintWriter(clientSocket.getOutputStream());
        this.serverRouteConfig = serverRouteConfig;
        this.sessionMap = sessionMap;
    }


    
    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();
        try {
            while (this.bufferedReader.ready() ||sb.length() == 0) {
                sb.append((char) this.bufferedReader.read());
            }
            
            try {
                HttpContext httpContext = new HttpContextImpl(sb.toString(), this.sessionMap);
                new HttpHandler(this.serverRouteConfig, this.printWriter).handle(httpContext);
                
                HttpSession session = httpContext.getHttpRequest().getHttpSession();
                this.sessionMap.put(session.getId(), session);
                
            } catch (BadRequestExeption ex) {
                this.printWriter.write("HTTP/1.1 400 Bat Request");
            }catch(FileNotFoundException fnx){
                this.printWriter.write("HTTP/1.1 404 Not Found");
            }catch(Exception e){
                this.printWriter.write("HTTP/1.1 500 Iternal Server Error");
            }
            
            this.printWriter.close();
            this.bufferedReader.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            try {
                this.clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
    }
    
}
