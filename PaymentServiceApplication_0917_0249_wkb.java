// 代码生成时间: 2025-09-17 02:49:21
package com.example.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

    @GetMapping("/process-payment/{amount}")
    public ResponseEntity<String> processPayment(@PathVariable("amount") double amount) {
        if(amount <= 0) {
            throw new PaymentException("Amount must be greater than zero.");
        }
        return ResponseEntity.ok("Payment processed successfully for amount: " + amount);
    }

    @ExceptionHandler(PaymentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handlePaymentException(PaymentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}

class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super(message);
    }
}
