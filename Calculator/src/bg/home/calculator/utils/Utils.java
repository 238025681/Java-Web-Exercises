/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.calculator.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;
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
            html = new String(Files.readAllBytes(Paths.get(Utils.class.getResource("../resources/html/main.html").toURI())));
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

    public static String calculate(Map<String, String> data) throws IOException, URISyntaxException {
       String result = "";
        
        if (!data.get("operation").equalsIgnoreCase("*")
                && !data.get("operation").equalsIgnoreCase("/")
                && !data.get("operation").equalsIgnoreCase("+")
                && !data.get("operation").equalsIgnoreCase("-")) {
             result = ("<p>Invalid Sign</p>");
        } else {
            if (!data.get("firtsNumber").matches("[0-9]+") ||
                    !data.get("secondNumber").matches("[0-9]+")) {
                result = ("Not a number");
            }else{
                String operation = data.get("operation");
                String firstNumber = data.get("firtsNumber");
                String secondNumber = data.get("secondNumber");
                 result =  getResult(operation, firstNumber, secondNumber).toString();
            }
       
        }
        return  result;
    }

    private static Integer getResult(String operation, String firstNumber, String secondNumber) {
        switch(operation){
            
            case "*": 
                return  Math.multiplyExact(Integer.parseInt(firstNumber),Integer.parseInt(secondNumber));
            case "/": 
                return  Math.floorDiv(Integer.parseInt(firstNumber),Integer.parseInt(secondNumber));
            case "+": 
                return  Math.addExact(Integer.parseInt(firstNumber),Integer.parseInt(secondNumber));
            case "-": 
                return  Math.subtractExact(Integer.parseInt(firstNumber),Integer.parseInt(secondNumber));
                default: return 0;
        }
    }
}
