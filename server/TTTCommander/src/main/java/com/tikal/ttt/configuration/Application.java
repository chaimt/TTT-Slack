package com.tikal.ttt.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Haim.Turkel on 2/16/2016.
 */
@SpringBootApplication
@Configuration
@ComponentScan("com.tikal")
public class Application {
    static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("start");
        SpringApplication.run(Application.class, args);
    }
}
