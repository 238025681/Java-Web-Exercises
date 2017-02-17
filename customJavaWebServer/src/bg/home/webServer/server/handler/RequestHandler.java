/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.handler;

import bg.home.webServer.server.interfaces.http.HttpContext;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author kalin
 */
public interface RequestHandler {

    void handle(HttpContext httpContext) throws IOException;

    void setWriter(Writer writer);
}
