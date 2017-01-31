package bg.home.login_form;

import bg.home.login_form.utils.Utils;
import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        String contentType = "Content-Type: text/html\n";
        String html = Utils.getHtml();
        System.out.println(contentType);
        System.out.println(html);
        getLoginInfo();
    }

    private static void getLoginInfo() throws IOException {
        Map<String, String> params = Utils.getParameters(System.in);
        String result = Utils.login(params);
        System.out.println("<p>" + result + "</p>");
    }
}
