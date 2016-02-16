package com.tikal.ttt.slack;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Slacklog{
    public static void log(String message) throws IOException {
        String SlackURL = "https://hooks.slack.com/services/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";
        String JSON = "payload={\"channel\": \"#ttt-log\", \"username\": \"webhookbot\", \"text\": \""+message+"\", \"icon_emoji\": \":ttt:\"}";

        HttpURLConnection httpcon = (HttpURLConnection) ((new URL(SlackURL).openConnection()));
        httpcon.setDoOutput(true);
        httpcon.setRequestProperty("Content-Type", "application/json");
        httpcon.setRequestProperty("Accept", "application/json");
        httpcon.setRequestMethod("POST");
        httpcon.connect();

        StringReader reader = new StringReader(JSON);
        OutputStream os = httpcon.getOutputStream();

//        byte[] buffer = new byte[4096];
//        int bytes_read;
//        while((bytes_read = reader.read(buffer)) != -1) {
//            os.write(buffer, 0, bytes_read);// I am getting compilation error here
//        }
        os.close();
    }
}