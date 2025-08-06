package com.alituran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.alituran")
public class CarhubapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarhubapiApplication.class, args);
    }

}
