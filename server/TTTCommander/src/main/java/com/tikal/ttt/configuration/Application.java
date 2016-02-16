package com.tikal.ttt.configuration;

import com.tikal.ttt.slack.Slacklog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Haim.Turkel on 2/16/2016.
 */
@SpringBootApplication
@EnableAsync
@ComponentScan("com.tikal")
public class Application {
    static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("start");
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    @Async
    public void init(){
        try {
            Slacklog.log("Start TTT Spring Boot");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
