package com.example.proto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Admin-Login aus Umgebungsvariablen, mit Defaults für lokale Entwicklung
    @Value("${ADMIN_USER:admin}")
    private String adminUsername;

    @Value("${ADMIN_PASS:changeMe123!}")
    private String adminPassword;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // öffentlich – Fragebögen und Ergebnisse
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/bfi.html",
                                "/pss.html",
                                "/result.html",
                                "/app.js",
                                "/api/submit-all",
                                "/api/bfi10",
                                "/api/pss10"
                        ).permitAll()

                        // geschützt
                        .requestMatchers(
                                "/dashboard.html",
                                "/api/export/**",
                                "/h2-console/**"
                        ).authenticated()

                        .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());

        // wichtig für H2-Console
        http.headers(headers -> headers.frameOptions().disable());

        return http.build();
    }

    // Admin-Benutzer – Credentials aus ENV
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    // Passwort-Hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
