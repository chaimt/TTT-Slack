package com.tikal.ttt.slack;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Slacklog{

    public static void log(String message) throws URISyntaxException {
        String payload = String.format("{\"channel\": \"#ttt-log\", \"username\": \"webhookbot\", \"text\": \"%s\", \"icon_emoji\": \":ghost:\"}",message);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(new URI("https://hooks.slack.com/services/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"), payload,String.class);
    }

}