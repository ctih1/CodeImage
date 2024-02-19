package xyz.ctih1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpTimeoutException;

public class ReadURL {
    public static String readURL(URL url, String token) throws IOException, RateLimitException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization","Bearer "+token);
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        if(con.getResponseCode()==401) {
            throw new RateLimitException("GitHub has ratelimited you. ");
        }
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }
}
