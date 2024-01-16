package com.example.util;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Component
public class MessageUtil {
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream input = new FileInputStream("src/main/resources/applicationMessage.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("ApplicationMessage properties not found");
        }
    }

    public String getMessage(String key) {
        return properties.getProperty(key);
    }

}
