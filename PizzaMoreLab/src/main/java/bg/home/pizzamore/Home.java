/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.pizzamore;

import bg.home.pizzamore.models.Cookie;
import bg.home.pizzamore.models.Header;
import bg.home.pizzamore.models.Session;
import bg.home.pizzamore.models.SessionData;
import bg.home.pizzamore.models.user.User;
import bg.home.pizzamore.repository.SessionRepository;
import bg.home.pizzamore.repository.UserRepository;
import bg.home.pizzamore.settings.HtmlEnum;
import bg.home.pizzamore.utils.Utils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author kalin
 */
public class Home {

    private static Map<String, String> parameters;
    private static Map<String, Cookie> cookies;
    private static Header header;

    private static SessionRepository sessionRepository;

    static {
        parameters = new HashMap<>();
        cookies = new HashMap<>();
        header = new Header();
        sessionRepository = new SessionRepository();
    }

    public static void readParameters() throws IOException {
        parameters = Utils.getParameters();

        for (String params : parameters.keySet()) {
            switch (params.toLowerCase()) {
                case "language":
                    setCookie();
                    break;
                case "signin":
                    goToSignin();
                    break;
                case "signup":
                    goToSignUp();
                    break;
                case "signout":
                    deleteSession();
                    break;
            }
        }
    }

    private static void setCookie() {
        String value = parameters.get("language");
        Cookie cookie = new Cookie("lang", value);
        Home.header.addCookie(cookie);
    }

    private static void goToSignin() {
        header.addLocation(HtmlEnum.SIGNIN.getLocation());
    }

    private static void goToSignUp() {
        header.addLocation(HtmlEnum.SIGNUP.getLocation());
    }

    private static void readCookies(String... args) {
        if (args.length == 0) {
            return;
        }

        for (String incomingCookie : args) {
            String[] tokens = incomingCookie.split("=");
            String key = tokens[0].trim();
            String value = tokens[1];
            value = value.replace(";", "").trim();
            Cookie cookie = new Cookie(key, value);
            Home.cookies.put(key, cookie);
        }
    }

    private static void readHtml() {
        Cookie sessionCookie = cookies.get("sid");
        String username = null;

        if (sessionCookie != null) {
            long sid = Long.parseLong(sessionCookie.getValue());
            Session session = sessionRepository.findById(sid);

            //Sign Out
//            if (parameters.containsKey("signout")) {
//                signOut(sid);
//                session = null;
//            }
            if (session != null) {
                Set<SessionData> sessionData = session.getSessionData();
                for (SessionData data : sessionData) {
                    if (data.getKey().equals("username")) {
                        username = data.getValue();
                    }
                }
            }
        }

        Cookie languageCookie = cookies.get("lang");
        if (!Utils.isPost()) {
            if (languageCookie != null) {
                String language = languageCookie.getValue();
                changeLanguage(language, username);
            } else {
                changeLanguage("EN", username);
            }

        } else {
            String language = parameters.get("language");
            if (language != null) {
                changeLanguage(language, username);
            } else {
                changeLanguage("EN", username);
            }
        }
    }

    private static void readHtmlDe(String username) {
        String signName = "signin";
        String signButton = "Anmelden";

        if (username != null) {
            signName = "signout";
            signButton = "Abmelden ( " + username + " )";
        }
        System.out.println(String.format(HtmlEnum.MAIN_DE.getHtml(), signName, signButton));
    }

    private static void readHtmlEn(String username) {
        String signName = "signin";
        String signButton = "Sign In";

        if (username != null) {
            signName = "signout";
            signButton = "Sign Out ( " + username + " )";
        }
        System.out.println(String.format(HtmlEnum.MAIN.getHtml(), signName, signButton));
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        readParameters();
        Home.header.printHeader();
        readCookies(args);
        readHtml();
    }

    private static void changeLanguage(String language, String username) {
        switch (language) {
            case "EN":
                readHtmlEn(username);
                break;
            case "DE":
                readHtmlDe(username);
                break;
        }
    }

    private static void deleteSession() {

        Cookie sessionCookie = cookies.get("sid");
        UserRepository userRepository = new UserRepository();
        if (sessionCookie != null) {
            userRepository.createUser(new User("ok e", "1234"));
            Long sid = Long.parseLong(sessionCookie.getValue());
            sessionRepository.delete(sid);
        }
    }
}
