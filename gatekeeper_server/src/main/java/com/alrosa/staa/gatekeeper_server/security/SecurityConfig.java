package com.alrosa.staa.gatekeeper_server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
    private final PasswordEncoder pwEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Bean
    UserDetailsService authentication() {
        UserDetails root = User.builder().username("root").password(pwEncoder.encode("gatekeeper")).roles("USER","ADMIN").build();

        UserDetails user = User.builder().username("user").password(pwEncoder.encode("12345")).roles("USER").build();

        System.out.println(">>> Root's password: " + root.getPassword());

        System.out.println(">>> User's password: " + user.getPassword());

        return new InMemoryUserDetailsManager(root, user);
    }
}
