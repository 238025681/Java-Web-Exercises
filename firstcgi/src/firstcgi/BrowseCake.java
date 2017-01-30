package firstcgi;

import firstcgi.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BrowseCake {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String contentType = "Content-Type: text/html\n";
        String test = new String(Files.readAllBytes(Paths.get(BrowseCake.class.getResource("browse_cake.html").toURI())));
        System.out.println(contentType);
        System.out.println(test);
        List<String> result = BrowseCake.browseArticle();
        result.forEach(action -> {
            System.out.println("<p>" + action + "</p>");
        }
        );
    }

    private static List<String> browseArticle() throws IOException, URISyntaxException {
        Map<String, String> params = Utils.getParameters(System.in);
        return Utils.search(params);
    }
}