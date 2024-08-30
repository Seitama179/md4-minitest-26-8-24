package com.example.minitestbook.config;

import com.example.minitestbook.service.user.IAppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
    private final IAppUserService appUserService;

    public AppSecurityConfig(IAppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    //    xac thuc
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//        tiem app user service
        dao.setUserDetailsService(appUserService);
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(
                        author -> author.requestMatchers( "/error_404", "/css/**", "/js/**", "/images/**").permitAll()
                                .anyRequest().authenticated()
                );
//                .authorizeHttpRequests(
//                        authorize -> authorize.antMatchers( "/error_404", "/css/**", "/js/**", "/images/**").permitAll()
//                                .anyRequest().authenticated()
//                );
        return httpSecurity.build();
    }

}
