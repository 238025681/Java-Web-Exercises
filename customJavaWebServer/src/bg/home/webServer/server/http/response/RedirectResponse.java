/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.http.response;

import bg.home.webServer.server.http.response.HttpResponseImpl;

/**
 *
 * @author kalin
 */
public class RedirectResponse extends HttpResponseImpl{
    
    public RedirectResponse(String redirectUrl) {
        super(redirectUrl);
    }
    
}
