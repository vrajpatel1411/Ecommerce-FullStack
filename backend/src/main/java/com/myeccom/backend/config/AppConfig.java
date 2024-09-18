package com.myeccom.backend.config;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

//     so by default spring provide a session policy which determines how to create a session so will keep it stateless and declare our own policy to declare state and manage.
        http.sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize->Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                .csrf(csrfConfigurer->csrfConfigurer.disable())
                .cors(
                        corsConfigurer->
                                corsConfigurer.configurationSource(
                                        new CorsConfigurationSource() {
                                            @Override
                                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                                CorsConfiguration cfg = new CorsConfiguration();
                                                cfg.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
                                                cfg.setAllowedMethods(Collections.singletonList("*"));
                                                cfg.setAllowCredentials(true);
                                                cfg.setAllowedHeaders(Collections.singletonList("*"));
                                                cfg.setExposedHeaders(Arrays.asList("Authorization"));
                                                cfg.setMaxAge(3600L);
                                                return cfg;
                                            }
                                        }
                                )
                )
                .httpBasic(Customizer.withDefaults())
                ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
