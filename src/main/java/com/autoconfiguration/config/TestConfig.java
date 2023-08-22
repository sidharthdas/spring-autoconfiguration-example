package com.autoconfiguration.config;

import com.autoconfiguration.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public Student getStudent() {
        return null;
    }
}
