/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.http.response;

import bg.home.webServer.server.enumeratoin.HttpResponseCode;
import bg.home.webServer.server.interfaces.view.View;

/**
 *
 * @author kalin
 */
public class ViewResponse extends HttpResponseImpl{
    
    public ViewResponse(HttpResponseCode responseCode, View view) {
        super(responseCode, view);
    }
    
}
