/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.http;

import bg.home.webServer.server.exeption.BadRequestExeption;
import bg.home.webServer.server.interfaces.http.HttpContext;
import bg.home.webServer.server.interfaces.http.HttpRequest;
import bg.home.webServer.server.interfaces.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;


public class HttpContextImpl implements HttpContext {

    private final HttpRequestImpl httpRequest;

    public HttpContextImpl(String requestString, Map<String, HttpSession> sessionMap) throws BadRequestExeption, UnsupportedEncodingException {
        this.httpRequest = new HttpRequestImpl(requestString, sessionMap);
    }

    
    

    @Override
    public HttpRequest getHttpRequest() {
        return this.httpRequest;
    }
    
}
