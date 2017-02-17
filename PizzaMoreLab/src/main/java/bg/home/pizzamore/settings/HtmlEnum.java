/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | HtmlEnum
 * and open the template in the editor.
 */
package bg.home.pizzamore.settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** 
 *
 * @author kalin
 */
public enum HtmlEnum {
    MAIN {
        @Override
        public String getHtml() {
            return getRealPathToHtml("htdocs/pizzamore/resources/html/main.html");
        }

        @Override
        public String getLocation() {
            return "pizza.cgi";
        }

    },
    MAIN_DE {
        @Override
        public String getHtml() {
            return getRealPathToHtml("htdocs/pizzamore/resources/html/main_de.html");
        }

        @Override
        public String getLocation() {
            return "pizza.cgi";
        }

    },
    
    SIGNUP {
        @Override
        public String getHtml() {
            return getRealPathToHtml("htdocs/pizzamore/resources/html/signup.html");
        }

        @Override
        public String getLocation() {
            return "signup.cgi";
        }

    },
    
    SIGNIN {
        @Override
        public String getHtml() {
            return getRealPathToHtml("htdocs/pizzamore/resources/html/signin.html");
        }

        @Override
        public String getLocation() {
            return "signin.cgi";
        }

    };
    
    //these methods are abstract because thus obligated to have implementation in the fields
    public abstract String getHtml();
    
    public abstract String getLocation();
    
    //This method return simple Content Type
    public static String setHtmlContentType(){
        return "Content-type: text/html;";
    }
    
    //This method get relative path and return absolute
    
    public static String getRealPathToHtml(String path) {
        
        Path realPath = Paths.get(System.getProperty("user.dir"));
        realPath = realPath.getParent().resolve(path).normalize();
        String result = "";
        try {
            result = new String(Files.readAllBytes(realPath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
