package com.example.dssw.config;


import com.example.dssw.security.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig //extends WebSecurityConfigurerAdapter
        {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    //@Override
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) {
        try {
//           return http.cors()
//                    .and()
//                    .csrf().disable()
//                    .httpBasic().disable()
//                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    .and()
//                    .authorizeRequests().antMatchers("/", "/auth/**").permitAll()
//                    .anyRequest().authenticated();

                    http.csrf(AbstractHttpConfigurer::disable)
                            .httpBasic(AbstractHttpConfigurer::disable)
                            .cors(Customizer.withDefaults())
                    .sessionManagement((sessionManagement) ->
                            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )

                    .authorizeHttpRequests((authorizeRequests) ->
                            authorizeRequests.requestMatchers("/", "/auth/**","/map/**","/search", "/solveBoard/list", "/solveBoard/list/**").permitAll()
                                    .anyRequest().authenticated()
                    );

                    http.addFilterAfter(
                        jwtAuthenticationFilter,
                        CorsFilter.class
                    );
                    return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        http.addFilterAfter(
//                jwtAuthenticationFilter,
//                CorsFilter.class
//        );
    }
}
