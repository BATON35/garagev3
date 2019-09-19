package com.konrad.garagev3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Garagev3Application {

    public static void main(String[] args) {
        SpringApplication.run(Garagev3Application.class, args);
    }
}

