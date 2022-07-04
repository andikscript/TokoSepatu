package com.andikscript.tokosepatu.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    // memperbolehkan akses tanpa menggunakan username dan password
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // format jika accept semua data barang : /api/brand/
        return (web) -> web.ignoring().antMatchers("/api/brand/B01", "/api/brand/B02");
    }
}
