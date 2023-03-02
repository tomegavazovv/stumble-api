package com.example.stumble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-${spring.active.profile:}.properties")
public class StumbleApplication {

    public static void main(String[] args) {
        SpringApplication.run(StumbleApplication.class, args);
    }

}
