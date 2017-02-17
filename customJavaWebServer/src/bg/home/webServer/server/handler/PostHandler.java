/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.handler;

import bg.home.webServer.server.interfaces.http.HttpContext;
import bg.home.webServer.server.http.response.HttpResponse;
import java.util.function.Function;

/**
 *
 * @author kalin
 */
public class PostHandler extends RequestHandlerImpl{
    
    public PostHandler(Function<HttpContext, HttpResponse> function) {
        super(function);
    }
    
}
