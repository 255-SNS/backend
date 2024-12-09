package com.sns255.sns255;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Sns255Application {

    public static void main(String[] args) {
        SpringApplication.run(Sns255Application.class, args);
    }

}
