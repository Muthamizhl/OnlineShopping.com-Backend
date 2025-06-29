package com.onlineshopping.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/products", "/api/products/category/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // customer endpoints
                .antMatchers("/api/products/**").hasRole("ADMIN") // admin endpoints
                .anyRequest().authenticated()
            .and()
            .httpBasic();
        return http.build();
    }
}
