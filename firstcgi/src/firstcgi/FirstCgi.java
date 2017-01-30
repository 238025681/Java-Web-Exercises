package firstcgi;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FirstCgi {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String contentType = "Content-Type: text/html\n\n";
        String test = new String(Files.readAllBytes(Paths.get(FirstCgi.class.getResource("JavaCgiHtml.html").toURI())));
        System.out.println(contentType);
        System.out.println(test);
    }
}