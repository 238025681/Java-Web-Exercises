/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.handler;

import bg.home.webServer.server.http.HttpSessionImpl;
import bg.home.webServer.server.http.response.HttpResponse;
import bg.home.webServer.server.interfaces.http.HttpContext;
import bg.home.webServer.server.interfaces.http.HttpSession;
import bg.home.webServer.server.util.SessionCreator;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

public abstract class RequestHandlerImpl implements RequestHandler {

    private Writer writer;
    private  Function<HttpContext, HttpResponse> function;

    public RequestHandlerImpl() {
    }

    RequestHandlerImpl(Function<HttpContext, HttpResponse> function) {
        this.function = function;
    }

    void setFunction(Function<HttpContext, HttpResponse> function){
         this.function = function;
    }
    private void setSession (HttpContext httpContext, HttpResponse httpResponse){
        HttpSession httpSession = httpContext.getHttpRequest().getHttpSession();
        
        if (!httpContext.getHttpRequest().getHttpCookie().contains("sessionId") || httpSession == null) {
            String sessionId = SessionCreator.getIstance().generateSessionId();
            
            httpResponse.addResponseHeader("Set-Cookie", "sessionId=" + sessionId + "; HttpOnly; path=/");
            httpSession = new HttpSessionImpl(sessionId);
            httpContext.getHttpRequest().setSession(httpSession);
        }
    }
    
    @Override
    public void handle(HttpContext httpContext) throws IOException {
        HttpResponse httpResponse = this.function.apply(httpContext);
        httpResponse.addResponseHeader("Content-Type", "text/html");
        
        this.setSession(httpContext, httpResponse);
        this.writer.write(httpResponse.getResponse());
    }

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

}
