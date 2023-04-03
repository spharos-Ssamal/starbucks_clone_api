package com.ssamal.starbucks_clone_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StarbucksCloneApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarbucksCloneApiApplication.class, args);
    }

}
