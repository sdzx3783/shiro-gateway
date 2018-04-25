package com.makshi.web.gateway.config;

import com.makshi.web.gateway.security.filter.AuthInfoFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {

    @Bean
    public AuthInfoFilter authInfoFilter() {
        return  new AuthInfoFilter();
    }
}
