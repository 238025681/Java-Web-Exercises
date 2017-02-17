/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.pizzamore.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author kalin
 */
public class Utils {

    public static Map<String, String> getParameters() throws IOException {
        Map<String, String> parametersMap = new LinkedHashMap<>();
        String input = "";
        if (Utils.isPost()) {
            BufferedReader bira = null;
            try {
                bira = new BufferedReader(new InputStreamReader(System.in));
                input = bira.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bira != null) {
                        bira.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            input = System.getProperty("cgi.query_string");
        }
        String[] parameters = new String[0];
        try {
            parameters = URLDecoder.decode(input, "UTF-8").split("&");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (String parameter : parameters) {
            String[] pair = parameter.split("=");
            String key = pair[0];
            String value = null;
            if (pair.length > 1) {
                value = pair[1];
            }
            parametersMap.put(key, value);
        }
        return parametersMap;
    }


    public static boolean isPost() {
        String requestMethod = System.getProperty("cgi.request_method").toLowerCase();
        boolean isPost = false;
        if (requestMethod.equals("post")) {
            isPost = true;
        }

        return isPost;
    }
}
