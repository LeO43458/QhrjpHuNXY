// 代码生成时间: 2025-09-19 11:01:26
package com.example.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
# TODO: 优化性能
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
# TODO: 优化性能
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
# 增强安全性
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
# TODO: 优化性能
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RestController
public class AuthServiceApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
# TODO: 优化性能
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/authenticate").permitAll()
                .anyRequest().authenticated()
            .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    private AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        return filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/api/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticate(
                new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
                )
# 优化算法效率
            );
        } catch (Exception e) {
# NOTE: 重要实现细节
            return ResponseEntity
                .status(400)
                .body("Error: Authentication Failed");
        }
# FIXME: 处理边界情况

        final String jwt = tokenProvider.generateToken(authenticationRequest.getUsername());
# FIXME: 处理边界情况
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
# NOTE: 重要实现细节

    @GetMapping("/user/me")
    public ResponseEntity<?> getCurrentUserPrincipal() {
        return ResponseEntity.ok(new UserDTO(authentication().getPrincipal().toString()));
    }
# TODO: 优化性能

    @PostConstruct
    public void init() {
        try {
            User user = new User(
                "user",
                "password",
                Arrays.asList("ROLE_USER")
            );
            user.setFullname("John Doe");
            this.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
# TODO: 优化性能
    }

    /* Additional methods and classes omitted for brevity */
# 增强安全性
}

/* User DTO class */

class AuthenticationRequest {
    private String username;
    private String password;

    // Getters and setters
}

class AuthenticationResponse {
# 扩展功能模块
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    // Getter
}

class UserDTO {
    private final String username;

    public UserDTO(String username) {
        this.username = username;
    }

    // Getter
# 增强安全性
}

/* Additional classes and methods omitted for brevity */