/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.http;

import bg.home.webServer.server.enumeratoin.HttpRequestType;
import bg.home.webServer.server.exeption.BadRequestExeption;
import bg.home.webServer.server.interfaces.http.HttpCookie;
import bg.home.webServer.server.interfaces.http.HttpRequest;
import bg.home.webServer.server.interfaces.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestImpl implements HttpRequest {

    private final Map<String, String> formData;
    private final Map<String, String> queryParameters;
    private final Map<String, String> urlParameters;
    private final Map<String, String> headers;
    private HttpRequestType requestMethod;
    private String url;
    private String path;
    private HttpSession session;
    private HttpCookie cookie;

    HttpRequestImpl(String requestString, Map<String, HttpSession> sessionMap) throws BadRequestExeption, UnsupportedEncodingException {
        this.headers = new HashMap<>();
        this.urlParameters = new HashMap<>();
        this.queryParameters = new HashMap<>();
        this.formData = new HashMap<>();

        this.parseRequest(requestString, sessionMap);
    }


    @Override
    public String getURL() {
        return this.url;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public HttpRequestType getRequestType() {
        return this.requestMethod;
    }

    @Override
    public String getHeader(String name) {
        return this.headers.get(name);
    }

    @Override
    public Map<String, String> getURLParameters() {
        return this.urlParameters;
    }

    @Override
    public Map<String, String> getQueryParameters() {
        return this.queryParameters;
    }

    @Override
    public Map<String, String> getFormData() {
        return this.formData;
    }

    @Override
    public void addParameter(String name, String value) {
        this.getURLParameters().put(name, value);
    }

    private void parseRequest(String requestString, Map<String, HttpSession> sessionMap) throws BadRequestExeption, UnsupportedEncodingException {
        String[] requestLines = requestString.split("\n");
        String[] requestLine = requestLines[0].trim().split("\\s+");

        if (requestLine.length != 3 || requestLine[2].toLowerCase().equals("HTTP/1.1")) {
            throw new BadRequestExeption("Invalid request line");
        }

        this.requestMethod = this.parseRequestMethod(requestLine[0].toLowerCase());
        this.url = requestLine[1];
        this.path = url.split("\\?")[0];

        this.parseHeaders(requestLines);
        this.parseParameters();
        if (this.requestMethod == HttpRequestType.POST) {
            this.parseQuery(requestLines[requestLines.length - 1], this.formData);
        }
        
        this.parseCookie();
        this.setSession(sessionMap);
    }

    private void parseCookie() {
        String cookiesString = this.headers.get("Cookie");
        this.cookie = new HttpCookieImpl();
    
        if (cookiesString == null) {
            return;
        }
        
        String[] cookies = cookiesString.trim().split(";");
        for (String cooky : cookies) {
            String[] cookieArgs = cooky.trim().split("=");
            this.cookie.addCookie(cookieArgs[0].trim(), cookieArgs[1].trim());
        }
    }
    
    private HttpRequestType parseRequestMethod(String requestMethod) throws BadRequestExeption {
        switch (requestMethod) {
            case "post":
                return HttpRequestType.POST;
            case "get":
                return HttpRequestType.GET;
            default:
                throw new BadRequestExeption("Invalid request line");
        }
    }

    private void parseHeaders(String[] requestLines) throws BadRequestExeption {
        int endIndex = Arrays.asList(requestLines).indexOf("\r");

        for (int i = 1; i < endIndex; i++) {
            String[] headerArgs = requestLines[i].split(": ");
            this.headers.put(headerArgs[0], headerArgs[1]);
        }

        if (!this.headers.containsKey("Host")) {
            throw new BadRequestExeption("Invalid header");
        }
    }

    private void parseParameters() throws UnsupportedEncodingException {
        if (!this.url.contains("?")) {
            return;
        }
        String query = this.url.trim().split("?")[1];
        this.parseQuery(query, this.queryParameters);
    }

    private void parseQuery(String query, Map<String, String> map) throws UnsupportedEncodingException {
        if (!query.contains("=")) {
            return;
        }
        String[] queryPairs = query.split("&");
        for (String queryPair : queryPairs) {
            String[] queryArgs = queryPair.split("=");
            map.put(URLDecoder.decode(queryArgs[0], "UTF-8"), URLDecoder.decode(queryArgs[1], "UTF-8"));
        }
    }

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public HttpSession getHttpSession() {
        return this.session;
    }

    @Override
    public HttpCookie getHttpCookie() {
        return this.cookie;
    }

    private void setSession(Map<String, HttpSession> sessionMap) {
        this.session = sessionMap.get(this.cookie.getCookie("sessionId"));
    }


}
