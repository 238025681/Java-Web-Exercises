/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.exeption;

/**
 *
 * @author kalin
 */
public class BadRequestExeption extends Exception{
    
    public BadRequestExeption(String message) {
        super(message);
    }
    
    
}
