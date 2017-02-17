/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.app.controller;

import bg.home.webServer.app.view.HomeIndexView;
import bg.home.webServer.server.Model;
import bg.home.webServer.server.enumeratoin.HttpResponseCode;
import bg.home.webServer.server.http.response.HttpResponse;
import bg.home.webServer.server.http.response.ViewResponse;

/**
 *
 * @author kalin
 */
public class HomeController {

    public HttpResponse index(String name){
        Model model = new Model();
        model.add("name", name);
        return new ViewResponse(HttpResponseCode.OK, new HomeIndexView(model));
    }
}
