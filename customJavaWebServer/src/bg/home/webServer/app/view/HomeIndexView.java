/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.app.view;

import bg.home.webServer.server.Model;
import bg.home.webServer.server.interfaces.view.View;

/**
 *
 * @author kalin
 */
public class HomeIndexView implements View{

    private final Model model;

    public HomeIndexView( Model model) {
        this.model = model;
    }
    
    
    @Override
    public String view() {
        return String.format("<html><body><i>Main View of</i><strong> %s</strong></body></html>", this.model.get("name").toString());
    }
    
}
