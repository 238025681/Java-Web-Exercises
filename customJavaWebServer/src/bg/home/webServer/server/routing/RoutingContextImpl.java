/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.routing;

import bg.home.webServer.server.handler.RequestHandlerImpl;
import bg.home.webServer.server.interfaces.routing.RoutingContext;
import java.util.List;

public class RoutingContextImpl implements RoutingContext {

    private RequestHandlerImpl handler;
    private List<String> paramNames;

    public RoutingContextImpl(RequestHandlerImpl handler, List<String> paramNames) {
        this.handler = handler;
        this.paramNames = paramNames;
    }

    @Override
    public RequestHandlerImpl getHandler() {
        return this.handler;
    }

    @Override
    public Iterable<String> getParamNames() {
        return this.paramNames;
    }

}
