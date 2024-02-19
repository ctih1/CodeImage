package xyz.ctih1;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Code {
    Byte[] bytes;
    JSONObject file;
    JSONObject json;
    URL url;
    String fileContent;
    List<String> fileContents;
    String fileName;
    Logger logger = Logger.getLogger(Main.class.getName());
    public Code() {
        fileContents = new ArrayList<>();
    }
    public List<String> get(String user, String repo, String branch, String fileType, String token) throws IOException, RateLimitException {
        url = new URL(String.format("https://api.github.com/repos/%s/%s/git/trees/%s?recursive=1",user,repo,branch));
        String response = ReadURL.readURL(url,token);
        json = new JSONObject(response);
        for(Object f: json.getJSONArray("tree")) {
            file = new JSONObject(f.toString());
            fileName = file.getString("path");
            if(fileName.endsWith(fileType)) {
                System.out.printf("[Code] Getting file %s \n",fileName);
                url = new URL(file.getString("url"));
                fileContent = new JSONObject(ReadURL.readURL(url,token)).getString("content");
                fileContents.add(new String(
                        Base64.getDecoder().decode(fileContent.replace("\n",""))
                ,StandardCharsets.UTF_8));
            }
        }
        return fileContents;
    }
}
