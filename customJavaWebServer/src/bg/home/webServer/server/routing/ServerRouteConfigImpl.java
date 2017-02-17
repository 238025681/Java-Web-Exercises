/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.routing;

import bg.home.webServer.server.interfaces.routing.AppRouteConfig;
import bg.home.webServer.server.handler.RequestHandlerImpl;
import bg.home.webServer.server.enumeratoin.HttpRequestType;
import bg.home.webServer.server.interfaces.routing.RoutingContext;
import bg.home.webServer.server.interfaces.routing.ServerRouteConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kalin
 */
public class ServerRouteConfigImpl implements ServerRouteConfig {

    private final HashMap<HttpRequestType, Map<String, RoutingContext>> routes;

    public ServerRouteConfigImpl(AppRouteConfig appRouteConfig) {
        this.routes = new HashMap<>();
        for (HttpRequestType val : HttpRequestType.values()) {
            this.routes.put(val, new HashMap<>());
        }

        this.initializeServerConfig(appRouteConfig);
    }

    @Override
    public Map<HttpRequestType, Map<String, RoutingContext>> getRoutes() {
        return this.routes;
    }

    private void initializeServerConfig(AppRouteConfig appRouteConfig) {
        for (Map.Entry<HttpRequestType, Map<String, RequestHandlerImpl>> approutes : appRouteConfig.getRoutes()) {
            for (Map.Entry<String, RequestHandlerImpl> entry : approutes.getValue().entrySet()) {

                List<String> params = new ArrayList<>();
                String newPattern = this.parseRoute(entry.getKey(), params);
                
                RoutingContext rc = new RoutingContextImpl(entry.getValue(), params);
                this.routes.get(approutes.getKey()).put(newPattern, rc);
            }
        }
    }

    private String parseRoute(String key, List<String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^");
        
        if ("/".equals(key)) {
            stringBuilder.append(key).append("$");
            return stringBuilder.toString();
        }
        
        String[] tokens = key.split("/");

        for (int i = 0; i < tokens.length; i++) {
            if (!tokens[i].startsWith("{") && !tokens[i].endsWith("}")) {
                stringBuilder.append(tokens[i]);
                stringBuilder.append(i == tokens.length - 1 ? "$" : "/");
                continue;
            }

            Pattern pattern = Pattern.compile("<\\w+>");
            Matcher match = pattern.matcher(tokens[i]);

            if (!match.find()) {
                continue;
            }

            String paramName = match.group(0).substring(1, match.group(0).length() - 1);
            params.add(paramName);
            stringBuilder.append(tokens[i].substring(1, tokens[i].length() - 1));
            stringBuilder.append(i == tokens.length - 1 ? "$" : "/");
        }

        return stringBuilder.toString();
    }

}
