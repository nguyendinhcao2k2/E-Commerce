package com.example.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author caodinh
 */
@Configuration
@EnableWebSecurity
public class SercurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable().authorizeHttpRequests()
                .requestMatchers("/api/user/**").permitAll()
                .requestMatchers("/403").permitAll()
                .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/static/**").permitAll()
                .and()
//                .oauth2Login()
//                .successHandler(null).and()
                .exceptionHandling().
                authenticationEntryPoint((request, response, authException) -> response.sendRedirect("http://localhost:8080/403"));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**", "/css/**");
    }

}
