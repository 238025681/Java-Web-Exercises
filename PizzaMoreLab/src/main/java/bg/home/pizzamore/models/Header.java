/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.pizzamore.models;

import bg.home.pizzamore.settings.HtmlEnum;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kalin
 */
public class Header {

    private String location;
    private List<Cookie> cookies;

    public Header() {
        this.cookies = new ArrayList();
    }

    public void addLocation(String location) {
        this.location = location;
    }

    public void addCookie(Cookie cookie) {
        this.cookies.add(cookie);
    }

    public void printHeader() {
        System.out.println(HtmlEnum.setHtmlContentType());
        
        if (!this.cookies.isEmpty()) {
            String cookies = "";
            
            for (Cookie cookie : this.cookies) {
                cookies += cookie.getCookieValue();
            }
            System.out.println("Set-Cookie: " + cookies);
        }
        
        if (this.location != null) {
            System.out.println("Location: " + this.location);
        }
        
        //End of Header
        System.out.println("");
    }

}
