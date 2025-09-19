// 代码生成时间: 2025-09-20 07:37:49
package com.example.dataanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api/data")
public class DataAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataAnalysisApplication.class, args);
    }

    @GetMapping("/count")
    public ResponseEntity<String> countData() {
        // 这里只是一个示例，实际应用中需要替换为真实的数据分析逻辑
        return ResponseEntity.ok("Data count: 100");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getMessage());
    }
}
