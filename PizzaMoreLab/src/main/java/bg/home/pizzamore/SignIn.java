/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.pizzamore;

import bg.home.pizzamore.models.Cookie;
import bg.home.pizzamore.models.Header;
import bg.home.pizzamore.models.Session;
import bg.home.pizzamore.models.user.User;
import bg.home.pizzamore.repository.SessionRepository;
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
public class SignIn {

    private static Map<String, String> parameters;
    private static Map<String, Cookie> cookies;
    private static Header header;
    private static UserRepository userRepository;
    private static SessionRepository sessionRepository;

    static {

        parameters = new HashMap();
        cookies = new HashMap();
        header = new Header();
        userRepository = new UserRepository();
        sessionRepository = new SessionRepository();

    }

    public static void readParameters() throws IOException {
        parameters = Utils.getParameters();
        for (String params : parameters.keySet()) {
            switch (params) {
                case "signin":
                    signIn();
                    break;
                case "main":
                    goToMain();
            }
        }
    }

    private static void signIn() {
        String username = parameters.get("username");
        String password = parameters.get("password");
        User user = userRepository.findByUserAndPassword(username, password);
        
        if (user != null) {
            Session session = new Session();
            session.addSessionData("username", username);
            long sid = sessionRepository.createSession(session);
            Cookie sessionCookie = new Cookie("sid", String.valueOf(sid));
            header.addCookie(sessionCookie);
            Cookie rememberMeCookie = new Cookie("rememberme", "on");
            header.addCookie(rememberMeCookie);
            header.addLocation(HtmlEnum.MAIN.getLocation());
        }
    }
    
    private static void readHtml() {

        System.out.println(HtmlEnum.SIGNIN.getHtml());
    }

    private static void goToMain() {
            header.addLocation(HtmlEnum.MAIN.getLocation());
    }
    public static void main(String[] args) throws IOException {
        readParameters();
        header.printHeader();
        readHtml();
    }
}
