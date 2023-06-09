package com.example.ecommerce.config;

import com.example.ecommerce.sercurity.CustomOAuth2User;
import com.example.ecommerce.sercurity.CustomOAuth2UserService;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Autowired
    private UserService userService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable().authorizeHttpRequests()
                .requestMatchers("/api/user/**").permitAll()
                .requestMatchers("/403", "/login-page", "/oauth2/**", "/profile", "/contact").permitAll()
                .requestMatchers("/api/admin/**").permitAll()
                .requestMatchers("/static/**").permitAll()
                .and()
                .oauth2Login()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and()
                .successHandler((request, response, authentication) -> {
                    CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                    userService.processOAuthPostLogin(oauthUser);
                    response.sendRedirect("/api/user/home");
                })
                .failureHandler((request, response, exception) -> {
                    response.sendRedirect("/api/user/home");
                    // Xử lý thất bại đăng nhập OAuth2
                }).and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/api/user/home").permitAll()
                .invalidateHttpSession(true).and()
                .exceptionHandling().accessDeniedPage("/403")
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("http://localhost:8080/api/user/home");
                });

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/css/**");
    }

}
