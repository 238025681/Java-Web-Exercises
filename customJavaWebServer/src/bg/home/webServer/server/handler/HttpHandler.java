/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.handler;

import bg.home.webServer.server.handler.RequestHandler;
import bg.home.webServer.server.interfaces.http.HttpContext;
import bg.home.webServer.server.interfaces.routing.RoutingContext;
import bg.home.webServer.server.interfaces.routing.ServerRouteConfig;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kalin
 */
public class HttpHandler implements RequestHandler{

    private Writer writer;
    private ServerRouteConfig serverRouteConfig;

    public HttpHandler(ServerRouteConfig serverRouteConfig, PrintWriter printWriter) {
        this.serverRouteConfig = serverRouteConfig;
        this.writer = printWriter;
    }
    
    @Override
    public void handle(HttpContext httpContext) throws IOException {
        for (Map.Entry<String, RoutingContext> entry : this.serverRouteConfig.getRoutes().get(httpContext.getHttpRequest().getRequestType()).entrySet()) {
            Pattern pattern = Pattern.compile(entry.getKey());
            Matcher matcher = pattern.matcher(httpContext.getHttpRequest().getPath());
            
            if (!matcher.find()) {
                continue;
            }
            
            for (String paramName : entry.getValue().getParamNames()) {
                httpContext.getHttpRequest().addParameter(paramName, matcher.group(paramName));
            }
            
            entry.getValue().getHandler().setWriter(this.writer);
            entry.getValue().getHandler().handle(httpContext);
            return;
        }
        
        throw new FileNotFoundException();
    }

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }
    
}
