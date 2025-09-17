// 代码生成时间: 2025-09-17 17:15:17
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class SpringBootApplicationTemplate {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationTemplate.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // You can add more REST APIs here according to your requirements

    // Example of another API
    @GetMapping("/data")
    public ResponseEntity<?> getData() {
        // This is just a placeholder for the actual data retrieval logic
        return new ResponseEntity<>("This is some data", HttpStatus.OK);
    }
}
