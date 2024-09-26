package com.example.stone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class StoneApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoneApplication.class, args);
    }
}
