/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.http;

import bg.home.webServer.server.interfaces.http.HttpCookie;
import java.util.HashMap;
import java.util.Map;


public class HttpCookieImpl implements HttpCookie {
    
    private Map<String, String> cookies;

    public HttpCookieImpl() {
        this.cookies = new HashMap<>();
    }

    @Override
    public void addCookie(String key, String value) {
        this.cookies.put(key, value);
    }

    @Override
    public void removeCookie(String key) {
        if (this.contains(key)) {
            this.cookies.remove(key);
        }
    }

    @Override
    public boolean contains(String key) {
        return this.cookies.containsKey(key);
    }

    @Override
    public String getCookie(String key) {
        String cookie = this.cookies.get(key);
        return cookie;
    }
    
}
