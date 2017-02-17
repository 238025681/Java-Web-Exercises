/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.pizzamore.models;

/**
 *
 * @author kalin
 */
public class Cookie {
    
    private String name;
    
    private String value;

    public Cookie(String name, String value) {
        this.setName(name);
        this.setValue(value);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setValue(String value) {
        this.value = value;
    }
    
    public String getCookieValue(){
       return this.getName() + "=" + this.getValue() +" ; ";
     }
    
    
}
