package com.kian;

import com.kian.service.IncomeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public IncomeValidator incomeValidator() {
        return new IncomeValidator();
    }
}
