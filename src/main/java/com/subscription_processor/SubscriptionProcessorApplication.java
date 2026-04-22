package com.subscription_processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SubscriptionProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionProcessorApplication.class, args);
    }

}
