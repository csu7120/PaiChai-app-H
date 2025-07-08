package com.paichai.health.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.paichai.health.common.jwt.JwtAuthenticationFilter;
import com.paichai.health.common.jwt.JwtProvider;
import com.paichai.health.user.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
          .userDetailsService(userDetailsService)
          .passwordEncoder(passwordEncoder());
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);

        authBuilder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());

        return authBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .authenticationManager(authenticationManager(http))

          .csrf(csrf -> csrf.disable())
          .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

          .authorizeHttpRequests(auth -> auth
              .requestMatchers(POST, "/api/users/login", "/api/users/register").permitAll()
              .anyRequest().authenticated()
          );

        // JWT 인증 필터
        JwtAuthenticationFilter jwtFilter =
            new JwtAuthenticationFilter(jwtProvider.getKey());
        http.addFilterBefore(jwtFilter,
            UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
