package com.example.CBS.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.example.CBS.model.CSRAuthority.Role;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Query to fetch user details (CSR)
        jdbcUserDetailsManager.setUsersByUsernameQuery(
            "SELECT name_CSR, password, available FROM CSR WHERE name_CSR = ?"
        );

        // Updated query to fetch authorities using the many-to-many join table
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "SELECT csr.name_CSR, authority.role " +
            "FROM CSR csr " +
            "JOIN csr_authority_mapping mapping ON csr.csr_id = mapping.csr_id " +
            "JOIN CSR_authority authority ON mapping.authority_id = authority.authority_id " +
            "WHERE csr.name_CSR = ?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                    .requestMatchers(HttpMethod.GET, "api/csrs").hasRole(Role.ROLE_VIEW_ONLY.toString())
                    .requestMatchers(HttpMethod.POST, "api/csrs/by-id").hasRole(Role.ROLE_VIEW_ONLY.toString())
                    .requestMatchers(HttpMethod.POST, "api/csrs").hasRole(Role.ROLE_CSR_MANAGEMENT.toString())
                    .requestMatchers(HttpMethod.PUT, "api/csrs").hasRole(Role.ROLE_CSR_MANAGEMENT.toString())
                    .requestMatchers(HttpMethod.DELETE, "api/csrs/by-id").hasRole(Role.ROLE_CSR_MANAGEMENT.toString())
                    .requestMatchers(HttpMethod.POST, "api/add/auth").hasRole(Role.ROLE_CSR_MANAGEMENT.toString())
                    .requestMatchers(HttpMethod.POST, "api/create/offer").hasRole(Role.ROLE_CSR_MANAGEMENT.toString())
        );

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
