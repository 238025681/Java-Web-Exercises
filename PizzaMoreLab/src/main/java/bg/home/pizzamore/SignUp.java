/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.pizzamore;

import bg.home.pizzamore.models.Header;
import bg.home.pizzamore.models.user.User;
import bg.home.pizzamore.repository.UserRepository;
import bg.home.pizzamore.settings.HtmlEnum;
import bg.home.pizzamore.utils.Utils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kalin
 */
public class SignUp {
    private static Map<String, String> parameters;
    private static Header header;
    private static UserRepository userRepository;
    
    static {
        parameters = new HashMap();
        header = new Header();
        userRepository = new UserRepository();
    
        
}
        public static void readParameters() throws IOException{
           parameters = Utils.getParameters();
           String username = null;
           String password = null;
            for (String param : parameters.keySet()) {
                switch (param) {
                    case "username":
                        username = parameters.get("username");
                        break;
                    case "password":
                        password = parameters.get("password");
                        break;
                    case "signup":
                        if (username == null || password == null) {
                            return;
                        }
                        User user = new User(username, password);
                        createUser(user);
                        header.addLocation(HtmlEnum.SIGNIN.getLocation());
                        break;
                    case "main":
                        header.addLocation(HtmlEnum.MAIN.getLocation());
                        break;
                }
            }
        }

    private static void createUser(User user) {
        userRepository.createUser(user);
    }
    
    private static void readHtml() {

        System.out.println(HtmlEnum.SIGNUP.getHtml());
    }
    
    public static void main(String[] args) throws IOException {
        readParameters();
        header.printHeader();
        readHtml();
    }
}