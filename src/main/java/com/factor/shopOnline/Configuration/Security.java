package com.factor.shopOnline.Configuration;


import com.factor.shopOnline.Service.ClientLogic;
import com.factor.shopOnline.Service.MerchantLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {
    @Bean
    public UserDetailsService loadUserByUsername(MerchantLogic merchantLogic, ClientLogic clientLogic){
        return username -> {
            try{
            return merchantLogic.loadUserByUsername(username);
            } catch (UsernameNotFoundException ignored) {
            }return clientLogic.loadUserByUsername(username);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();
}

    @Bean
 public SecurityFilterChain http(HttpSecurity http){
    return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/addStock").hasRole("RETAILER")
                    .requestMatchers("/update/*").hasRole("RETAILER")
                    .requestMatchers("/SignUp").permitAll()
                    .requestMatchers("/MerchantSignUp").permitAll()
                    //admin registration to avoid easy application from any random user
                    .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .build();


    }
}
