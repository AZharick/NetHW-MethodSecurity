package com.example.hiber.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

   @Bean
   public static PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
              .authorizeHttpRequests(auth -> {
//                         auth.requestMatchers("/hi").permitAll();
//                         auth.requestMatchers("/create", "/update", "/delete").hasAuthority("write");
//                         auth.requestMatchers("/getAll", "/getByCity", "/youngerThan", "/getByPK").hasAuthority("read");
                         auth.anyRequest().authenticated();
                      }
              )
              .formLogin(withDefaults());
      return http.build();
   }

   @Bean
   protected UserDetailsService userDetailsService() {
      var admin = User.builder()
              .username("admin")
              .password("{noop}admin")
              .authorities("read", "write")
              .build();
      var user = User.builder()
              .username("Alex")
              .password("{noop}qwerty")
              .authorities("read")
              .build();

      //additional users for method security
      var reader = User.withUsername("read")
              .password(passwordEncoder().encode("read"))
              .roles("READ")
              .build();
      var writer = User.withUsername("write")
              .password(passwordEncoder().encode("write"))
              .roles("WRITE")
              .build();
      var deleter = User.withUsername("delete")
              .password(passwordEncoder().encode("delete"))
              .roles("DELETE")
              .build();

      return new InMemoryUserDetailsManager(admin, user, reader, writer, deleter);
   }
}