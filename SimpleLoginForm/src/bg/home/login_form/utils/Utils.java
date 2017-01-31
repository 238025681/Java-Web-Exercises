/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.login_form.utils;

import bg.home.login_form.settings.HtmlEnum;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kalin
 */
public class Utils {

    public static Map<String, String> getParameters(InputStream inputStream) throws IOException {
        Map<String, String> parametersMap = new LinkedHashMap();
        String inBuffer;
        if (isPost()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            inBuffer = br.readLine();
        } else {
            inBuffer = System.getProperty("cgi.query_string");
        }

        StringTokenizer parameters = new StringTokenizer(inBuffer, "&");
        while (parameters.hasMoreTokens()) {
            String pair = parameters.nextToken();
            StringTokenizer pairs = new StringTokenizer(pair, "=");
            while (pairs.hasMoreTokens()) {
                String key = pairs.nextToken();
                String value = URLDecoder.decode(pairs.nextToken(), "UTF-8");
                parametersMap.put(key, value);
            }
        }

        return parametersMap;
    }

    public static String getHtml() {

        String html = "";
        try {
            html = new String(Files.readAllBytes(Paths.get(Utils.class.getResource(HtmlEnum.MAIN.getHtmlFile()).toURI())));
        } catch (URISyntaxException | IOException ex) {
            ex.printStackTrace();
        }
        return html;
    }

    private static boolean isPost() {
        String requestMethod = System.getProperty("cgi.request_method").toLowerCase();
        boolean isPost = false;
        if (requestMethod.equals("post")) {
            isPost = true;
        }

        return isPost;
    }

    public static String login(Map<String, String> data) {
        String usernameAndPassword = "";
        
        if (!data.get("username").isEmpty() || !data.get("password").isEmpty()) {
            
        usernameAndPassword = "Hi " + data.get("username") + ", your password is " + data.get("password");
        }
        return usernameAndPassword;
    }
}
