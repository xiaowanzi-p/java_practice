package com.example.java_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class JavaPracticeApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(JavaPracticeApplication.class, args);
    }

}
