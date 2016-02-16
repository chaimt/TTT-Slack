package com.tikal.data;

import com.tikal.api.TimeTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haim.Turkel on 2/16/2016.
 */
@Service
public class TimeTrackerImpl implements TimeTracker {
    static Logger logger = LoggerFactory.getLogger(TimeTrackerImpl.class);


    public String login(){
        String sessionId="";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<String> request = new HttpEntity<String>(headers);

            String todayDate = "2016-02-16";
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("login", "gilad");
            map.add("password", "tikal123");
            map.add("browser_today", todayDate);
            HttpEntity<MultiValueMap<String, String>> request1 = new HttpEntity<MultiValueMap<String, String>>(map,headers);

            URI uri = new URI("https://timetracker.anuko.com/login.php");
            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, request1, String.class);
            List<String> strings = response.getHeaders().get("Set-Cookie");

            for (String entry : strings){
                if (entry.startsWith("tt_PHPSESSID")){
                    sessionId = entry.substring("tt_PHPSESSID=".length(),entry.indexOf(";"));
                }
            }
            logger.debug(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionId;
    }

    public boolean submitTime(String sessionId){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add(HttpHeaders.COOKIE,"tt_login=gilad; tt_PHPSESSID="+sessionId);
            HttpEntity<String> request = new HttpEntity<String>(headers);

            String submitDate = "2016-02-14";
            String todayDate = "2016-02-16";
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("project", "103016");
//            map.add("task", "5");
            map.add("start", "08:00");
            map.add("finish", "18:00");
            map.add("date", submitDate);
            map.add("note", "spring boot 2");
            map.add("onBehalfUser", "45773");
            map.add("btn_submit", "Submit");
            map.add("browser_today", todayDate);
            HttpEntity<MultiValueMap<String, String>> request1 = new HttpEntity<MultiValueMap<String, String>>(map,headers);


            URI uri = new URI("https://timetracker.anuko.com/time.php?date=" + submitDate);
            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, request1, String.class);
            List<String> strings = response.getHeaders().get("Set-Cookie");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
