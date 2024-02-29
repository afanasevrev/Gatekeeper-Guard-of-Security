package com.alrosa.staa.gatekeeper_server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    private final PasswordEncoder pwEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    
    @Bean
    UserDetailsService authentication() {
        UserDetails root = User.builder().username("root").password(pwEncoder.encode("gatekeeper")).roles("USER","ADMIN").build();
        UserDetails user = User.builder().username("user").password(pwEncoder.encode("12345")).roles("USER").build();
        return new InMemoryUserDetailsManager(root, user);
    }

}
