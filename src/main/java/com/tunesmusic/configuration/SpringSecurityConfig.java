package com.tunesmusic.configuration;

import com.tunesmusic.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth) -> auth

                        .requestMatchers("/*").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/artist/**").hasRole("ARTIST")
                        .requestMatchers("/tunesmusic/add-to-favorite").authenticated()
                        .requestMatchers("/tunesmusic/favorite-song").authenticated()
                        .requestMatchers("/tunesmusic/playlist").authenticated()
                        .requestMatchers("/tunesmusic/playlist/**").authenticated()
                        .requestMatchers("/tunesmusic/artist-following").authenticated()
                        .requestMatchers("/tunesmusic/**").permitAll()
                        .anyRequest().authenticated()
                ).formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login")
                        .usernameParameter("username").passwordParameter("password")
                        .successHandler(new CustomAuthenticationSuccessHandler())
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/tunesmusic"));


        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/img/**", "/customcss/**", "/js/**", "/audio/**"));
    }
}
