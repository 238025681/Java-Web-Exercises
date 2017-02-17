/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.routing;

import bg.home.webServer.server.handler.RequestHandlerImpl;
import bg.home.webServer.server.interfaces.routing.RoutingContext;
import java.util.Collections;
import java.util.Map;

public class RoutingContextImpl implements RoutingContext {

    private final RequestHandlerImpl handler;
    private final Map<Integer, Class> argumentMapping;
    private final ControllerActionPair actionPair;

    public RoutingContextImpl(RequestHandlerImpl handler,ControllerActionPair actionPair, Map<Integer, Class> argumentMapping ) {
        this.handler = handler;
        this.argumentMapping = argumentMapping;
        this.actionPair = actionPair;
    }

    @Override
    public RequestHandlerImpl getHandler() {
        return this.handler;
    }

    

    @Override
    public Map<Integer, Class> getArgumentMapping() {
        return Collections.unmodifiableMap(this.argumentMapping);
    }

    @Override
    public ControllerActionPair getActionPair() {
        return this.actionPair;
    }

}
