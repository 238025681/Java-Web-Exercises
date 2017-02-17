/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.http.response;

/**
 *
 * @author kalin
 */
public interface HttpResponse {

    String getResponse();

    void addResponseHeader(String header, String value);

}
