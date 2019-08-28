package com.konrad.garagev3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Garagev3Application {

    public static void main(String[] args) {
        SpringApplication.run(Garagev3Application.class, args);
    }
}

