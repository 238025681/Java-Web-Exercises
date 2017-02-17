/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.routing;

import bg.home.webServer.server.enumeratoin.HttpRequestType;
import bg.home.webServer.server.handler.GetHandler;
import bg.home.webServer.server.handler.PostHandler;
import bg.home.webServer.server.handler.RequestHandlerImpl;
import bg.home.webServer.server.interfaces.routing.RoutingContext;
import bg.home.webServer.server.interfaces.routing.ServerRouteConfig;
import bg.home.webServer.server.parser.ControllerAnnotationParser;
import bg.home.webServer.server.provider.ClassProvider;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kalin
 */
public class ServerRouteConfigImpl implements ServerRouteConfig {

    private final HashMap<HttpRequestType, Map<String, RoutingContext>> routes;
    private final ClassProvider classProvider;

    public ServerRouteConfigImpl(ClassProvider classProvider ) throws InstantiationException, IllegalAccessException {
        this.classProvider = classProvider;
        this.routes = new HashMap<>();
        for (HttpRequestType val : HttpRequestType.values()) {
            this.routes.put(val, new HashMap<>());
        }

        this.initializeServerConfig();
    }

    @Override
    public Map<HttpRequestType, Map<String, RoutingContext>> getRoutes() {
        return Collections.unmodifiableMap(this.routes);
    }

    private void initializeServerConfig() throws InstantiationException, IllegalAccessException{
    
        Map<HttpRequestType, Map<String, ControllerActionPair>> annotationRoutes = new HashMap<>();
        ControllerAnnotationParser annotationParser = new ControllerAnnotationParser(this.classProvider);
        annotationParser.parse(annotationRoutes);
        
        annotationRoutes.entrySet().forEach((entry) -> {
            entry.getValue().entrySet().forEach((actionPairEntry) -> {
                RequestHandlerImpl handler;
                if (entry.getKey() == HttpRequestType.GET) {
                    handler = new GetHandler();
                }else{
                    handler = new PostHandler();
                }
                
                Map<Integer, Class> args = actionPairEntry.getValue().getArgumentMapping();
                ControllerActionPair actionPair = actionPairEntry.getValue();
                
                RoutingContext routingContext = new RoutingContextImpl(handler, actionPair, args);
                
                this.routes.get(entry.getKey()).put(actionPairEntry.getKey(), routingContext);
            });
        });
    }
}
