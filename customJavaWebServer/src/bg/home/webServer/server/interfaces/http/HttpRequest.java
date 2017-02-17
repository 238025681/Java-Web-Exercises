/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.interfaces.http;

import bg.home.webServer.server.enumeratoin.HttpRequestType;
import java.util.Map;

/**
 *
 * @author kalin
 */
public interface HttpRequest {

    String getURL();

    String getPath();

    HttpRequestType getRequestType();

    String getHeader(String name);

    Map<String, String> getURLParameters();

    Map<String, String> getQueryParameters();

    Map<String, String> getFormData();

    void addParameter(String name, String value);
    
    void setSession(HttpSession session);
    HttpSession getHttpSession();
    HttpCookie getHttpCookie();

}
