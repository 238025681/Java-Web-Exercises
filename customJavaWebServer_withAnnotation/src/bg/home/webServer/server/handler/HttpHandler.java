/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.handler;

import bg.home.webServer.server.handler.RequestHandler;
import bg.home.webServer.server.http.response.HttpResponse;
import bg.home.webServer.server.interfaces.http.HttpContext;
import bg.home.webServer.server.interfaces.routing.RoutingContext;
import bg.home.webServer.server.interfaces.routing.ServerRouteConfig;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kalin
 */
public class HttpHandler implements RequestHandler{

    private Writer writer;
    private ServerRouteConfig serverRouteConfig;
    private HashMap<Class, Function<String, Object>> typeConversions;

    public HttpHandler(ServerRouteConfig serverRouteConfig, PrintWriter printWriter) {
        this.serverRouteConfig = serverRouteConfig;
        this.writer = printWriter;
        this.fillTypeConversions();
    }
    
    @Override
    public void handle(HttpContext httpContext) throws IOException {
        for (Map.Entry<String, RoutingContext> entry : this.serverRouteConfig.getRoutes().get(httpContext.getHttpRequest().getRequestType()).entrySet()) {
            Pattern pattern = Pattern.compile(entry.getKey());
            Matcher matcher = pattern.matcher(httpContext.getHttpRequest().getPath());
            
            if (!matcher.find()) {
                continue;
            }
            
           entry.getValue().getHandler().setFunction(context -> {
               Method method = entry.getValue().getActionPair().getAction();
               Map<Integer, Class> argumentPosition = entry.getValue().getArgumentMapping();
               String[] urlTokens = httpContext.getHttpRequest().getPath().split("/");
               Object[] argumentsToPass = new Object[argumentPosition.size()];
               
               int index = 0;
               for (Map.Entry<Integer, Class> typeMapping : argumentPosition.entrySet()) {
                   String valueToParse  = urlTokens[typeMapping.getKey()];
                   Class classToParseFrom = typeMapping.getValue();
                   
                   argumentsToPass[index++] = this.typeConversions.get(classToParseFrom).apply(valueToParse);
                   
                   
               }
               
               HttpResponse httpResponse = null;
                   
                   try {
                       Object response = method.invoke(entry.getValue().getActionPair().getController(), argumentsToPass);
                       httpResponse = (HttpResponse) response;  
                   } catch (IllegalAccessException | InvocationTargetException e) {
                       e.printStackTrace();
                   }
                   return httpResponse;
           });
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
    
    private void fillTypeConversions(){
        this.typeConversions = new HashMap<Class, Function<String, Object>>(){{
            this.put(String.class, s -> s);
            this.put(Integer.class, Integer::parseInt);
            this.put(int.class, Integer::parseInt);
            this.put(double.class, Double::parseDouble);
            this.put(Double.class, Double::parseDouble);
            this.put(Long.class, Long::parseLong);
            this.put(long.class, Long::parseLong);
        }};
    }
    
}
