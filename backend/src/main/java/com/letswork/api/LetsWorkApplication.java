package com.letswork.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LetsWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(LetsWorkApplication.class, args);
    }
}
