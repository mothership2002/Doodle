package com.example.webflux.config;

import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class TransactionConfig {

    private final ConnectionFactory connectionFactory;

    @Bean
    public ReactiveTransactionManager reactiveTransactionManager() {
        return new R2dbcTransactionManager(connectionFactory);
    }
}
