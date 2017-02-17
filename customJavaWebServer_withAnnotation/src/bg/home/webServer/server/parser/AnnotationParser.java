/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.parser;

import java.util.Map;

/**
 *
 * @author kalin
 */
public interface AnnotationParser<RequestMethod, RouteInfo> {
    
    void parse(Map<RequestMethod, RouteInfo> routes) throws IllegalAccessException, InstantiationException;
}
