/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.routing;

import bg.home.webServer.server.enumeratoin.HttpRequestType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author kalin
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value();
    
    HttpRequestType method() default HttpRequestType.GET;
}
