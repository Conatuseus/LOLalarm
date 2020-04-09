package com.conatuseus.oppalol.global.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        builder.setConnectTimeout(Duration.ofSeconds(3));
        builder.setReadTimeout(Duration.ofSeconds(3));
        return builder;
    }
}
