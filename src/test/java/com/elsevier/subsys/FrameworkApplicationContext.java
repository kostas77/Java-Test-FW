package com.elsevier.subsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

// Boilerplate required for Spring Boot context
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class FrameworkApplicationContext {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkApplicationContext.class, args);
    }

}
