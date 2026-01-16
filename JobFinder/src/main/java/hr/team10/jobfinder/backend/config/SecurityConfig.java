package hr.team10.jobfinder.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // nema CSRF jer nema frontend tokena
                .csrf(csrf -> csrf.disable())

                // ISKLJUČI spring login page
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())

                // dozvoli javne rute i HTML
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/*.html",
                                "/login",
                                "/register",
                                "/api/applications/**",
                                "/api/jobs/**",
                                "/api/auth/**",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                .headers(headers -> headers
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives(
                                        "default-src 'self'; " +
                                                "script-src 'self'; " +              // Strogo bez unsafe-inline
                                                "style-src 'self'; " +               // Lokalni CSS (uklonjen unsafe-inline i CDN)
                                                "font-src 'self'; " +                // Lokalni fontovi
                                                "img-src 'self' data:; " +
                                                "connect-src 'self'; " +
                                                "frame-ancestors 'none'; " +         // Protiv Clickjacking-a
                                                "form-action 'self'; " +             // Ključno za Same-Origin forme
                                                "base-uri 'self'; " +                // Sprječava base tag otimanje
                                                "object-src 'none';"                 // Onemogućava pluginove
                                )
                        )
                        .frameOptions(frame -> frame.deny())
                        .httpStrictTransportSecurity(hsts -> hsts.includeSubDomains(true).maxAgeInSeconds(31536000))
                );

        return http.build();
    }
}