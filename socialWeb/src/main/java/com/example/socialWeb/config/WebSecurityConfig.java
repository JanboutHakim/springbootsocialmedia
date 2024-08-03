package com.example.socialWeb.config;

import com.example.socialWeb.services.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public static SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
        httpSecurity.
                authorizeHttpRequests((requestMatcherRegistry)->

                        requestMatcherRegistry.requestMatchers("/","/login","/register")
                        .permitAll()
                                .requestMatchers(HttpMethod.POST,"/","/login","/register")
                                .permitAll()
                                .anyRequest().authenticated()).formLogin((form)->
                                 form.loginPage("/login")
                                .defaultSuccessUrl("/mainpage")
                                .loginProcessingUrl("/login")
                                .failureUrl("/login?error=true")
                                         .successHandler((request, response, authentication) -> {
                                             // Get the authenticated user's ID (you need to adapt this part)
                                             UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                                             response.sendRedirect("/loginsuccses?username=" +userDetails.getUsername());
                                         })
                                         .permitAll())

                .logout((logout)-> logout
                        .permitAll()
                        .logoutUrl("/logoutsuccses")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();

    }
    private final CustomUserDetailService customUserDetailService;
    private final PasswordEncoder passwordEncoder;
    public WebSecurityConfig(CustomUserDetailService customUserDetailService, PasswordEncoder passwordEncoder) {
        this.customUserDetailService = customUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder);
    }


}
