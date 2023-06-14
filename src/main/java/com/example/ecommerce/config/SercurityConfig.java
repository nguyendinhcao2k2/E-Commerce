package com.example.ecommerce.config;

import com.example.ecommerce.sercurity.CustomAdminDetails;
import com.example.ecommerce.sercurity.CustomOAuth2User;
import com.example.ecommerce.sercurity.CustomOAuth2UserService;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.service.impl.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public UserDetailsService userDetailsService() {
        return new AdminService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable().authorizeHttpRequests()
                .requestMatchers("/api/user/**").permitAll()
                .requestMatchers("/403", "/login-page", "/oauth2/**", "/profile", "/contact").permitAll()
                .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/static/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    CustomAdminDetails customAdminDetails = (CustomAdminDetails) authentication.getPrincipal();
                    HttpSession session = request.getSession();
                    session.setAttribute("admin", customAdminDetails);
                    response.sendRedirect("/api/admin/home");
                })
                .failureUrl("/login-page?error=true")
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
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("http://localhost:8080/login-page");
                });

        return http.build();
    }


}
