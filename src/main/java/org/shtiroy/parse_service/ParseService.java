package org.shtiroy.parse_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ParseService {
    public static void main(String[] args){
        SpringApplication.run(ParseService.class, args);
    }
}
