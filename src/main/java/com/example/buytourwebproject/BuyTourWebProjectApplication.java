package com.example.buytourwebproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BuyTourWebProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuyTourWebProjectApplication.class, args);
    }

}
