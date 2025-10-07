// 代码生成时间: 2025-10-08 03:09:17
package com.example.streamprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class StreamProcessorApp {

    public static void main(String[] args) {
        SpringApplication.run(StreamProcessorApp.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Stream Processor!";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Log the exception here
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
    }
}
