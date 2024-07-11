package com.omegron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OmegronApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmegronApplication.class, args);
    }
}
