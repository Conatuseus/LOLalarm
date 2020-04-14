package com.conatuseus.oppalol.global.security;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@TestConfiguration
public class SecurityConfig implements WebSecurityConfigurer<WebSecurity> {

    @Override
    public void init(WebSecurity builder) throws Exception {
        builder.ignoring().requestMatchers(
            new AntPathRequestMatcher("/**"));
    }

    @Override
    public void configure(WebSecurity builder) throws Exception {
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}