package bg.home.calculator;

import bg.home.calculator.utils.Utils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String contentType = "Content-Type: text/html\n";
        String html = Utils.getHtml();
        System.out.println(contentType);
        System.out.println(html);
        getParams();
    }
    
     private static void getParams() throws IOException, URISyntaxException {
        Map<String, String> params = Utils.getParameters(System.in);
        String result = Utils.calculate(params);
         System.out.println("<p>" + result + "</p>");
    }
}
