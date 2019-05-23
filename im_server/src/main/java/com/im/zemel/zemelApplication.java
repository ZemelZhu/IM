package com.im.zemel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class zemelApplication {
    public static void main(String[] args) {
        SpringApplication.run(zemelApplication.class, args);

    }
}
