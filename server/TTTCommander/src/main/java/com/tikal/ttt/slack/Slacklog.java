package com.tikal.ttt.slack;

import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class Slacklog{

    public static void log(String message) throws URISyntaxException {
        String payload = String.format("{\"channel\": \"#ttt-log\", \"username\": \"tikal\", \"text\": \"%s\", \"icon_emoji\": \":ttt:\"}",message);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(new URI("https://hooks.slack.com/services/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"), payload,String.class);
    }

}