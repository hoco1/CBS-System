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
        
        jdbcUserDetailsManager.setUsersByUsernameQuery(
        	    "SELECT name_CSR, password, available FROM CSR WHERE name_CSR = ?"
        	);

        	jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
        	    "SELECT csr.name_CSR, authority.role " +
        	    "FROM CSR csr " +
        	    "JOIN CSR_authority authority ON csr.csr_id = authority.csr_id " +
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
        );

        http.httpBasic(Customizer.withDefaults());

        // Disable CSRF for testing, recommended to enable in production
        http.csrf(csrf -> csrf.disable());
        
        return http.build();
    }
}
