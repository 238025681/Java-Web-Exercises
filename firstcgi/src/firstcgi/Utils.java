package firstcgi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Utils {
    private static boolean isPost() {
        String requestMethod = System.getProperty("cgi.request_method").toLowerCase();
        boolean isPost = false;
        if (requestMethod.equals("post")) {
            isPost = true;
        }
        return isPost;
    }

    public static Map<String, String> getParameters(InputStream inputStream) throws IOException {
        String inBuffer;
        LinkedHashMap<String, String> parametersMap = new LinkedHashMap<String, String>();
        if (Utils.isPost()) {
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

    public static void writeData(Map<String, String> data) throws IOException, URISyntaxException {
        ArrayList<String> lines = new ArrayList<String>();
        StringJoiner JSONdata = new StringJoiner(", ", "", ";");
        data.forEach((k, v) -> {
            JSONdata.add(k + ": " + v);
        }
        );
        lines.add(JSONdata.toString());
        Path file = Paths.get(Utils.class.getResource("database.csv").toURI());
        Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
    }

    public static List<String> search(Map<String, String> article) throws IOException, URISyntaxException {
        ArrayList<String> result = new ArrayList<String>();
        if (article.containsKey("search")) {
            Path file = Paths.get(Utils.class.getResource("database.csv").toURI());
            List<String> dataBase = Files.readAllLines(file, Charset.forName("UTF-8"));
            dataBase.forEach(action -> {
                if (action.toLowerCase().contains(((String)article.get("search")).toLowerCase())) {
                    result.add(action.substring(0, action.length() - 1));
                }
            }
            );
        }
        return result;
    }
}