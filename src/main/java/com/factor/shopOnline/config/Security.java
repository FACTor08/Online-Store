package com.factor.shopOnline.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {
    private final JwtFilter filter;

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();
}

    @Bean
 public SecurityFilterChain http(HttpSecurity http){
    return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/addStock").hasRole("RETAILER")
                    .requestMatchers("/admin/update").hasRole("RETAILER")
                    .requestMatchers("/update-product").hasRole("RETAILER")
                    .requestMatchers("/client/update/{*}").hasRole("CLIENT")
                    .requestMatchers("/client/update/user_handle").hasRole("CLIENT")
                    .requestMatchers("/SignUp").permitAll()
                    .requestMatchers("/MerchantSignUp").permitAll()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/error").permitAll()
                    .requestMatchers("/v3/api-docs",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html").permitAll()

                    .anyRequest()
                    .authenticated())
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
            .build();


    }
}
