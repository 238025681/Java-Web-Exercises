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
import java.util.Map;

public class AddCake {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String contentType = "Content-Type: text/html\n";
        String test = new String(Files.readAllBytes(Paths.get(AddCake.class.getResource("AddCake.html").toURI())));
        System.out.println(contentType);
        System.out.println(test);
        AddCake.getParams();
    }

    private static void getParams() throws IOException, URISyntaxException {
        Map<String, String> params = Utils.getParameters(System.in);
        Utils.writeData(params);
    }
}