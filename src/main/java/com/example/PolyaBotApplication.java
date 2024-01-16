package com.example;

import io.github.nazarovctrl.telegrambotspring.annotation.EnableTelegramLongPollingBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableTelegramLongPollingBot
@SpringBootApplication
public class PolyaBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolyaBotApplication.class, args);
    }

}
