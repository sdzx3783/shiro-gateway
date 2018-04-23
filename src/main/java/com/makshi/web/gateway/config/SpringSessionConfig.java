package com.makshi.web.gateway.config;

import com.makshi.web.gateway.session.spring.TokenSessionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.http.HttpSessionStrategy;

@EnableJdbcHttpSession
public class SpringSessionConfig {
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new TokenSessionStrategy();
    }
}
