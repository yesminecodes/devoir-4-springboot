package com.yesmine.games.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests((requests)->requests

                        .requestMatchers("/modifierGame", "/updateGame", "/supprimerGame").hasAuthority("ADMIN")

                        .requestMatchers("/showCreate", "/saveGame").hasAnyAuthority("ADMIN", "AGENT")

                        .requestMatchers("/ListeGames").hasAnyAuthority("ADMIN", "AGENT", "USER")

                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling((exception)->
                        exception.accessDeniedPage("/accessDenied"));

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .authorities("ADMIN")
                .build();
        UserDetails userYesmine = User.withDefaultPasswordEncoder()
                .username("yesmine")
                .password("123")
                .authorities("AGENT","USER")
                .build();
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("123")
                .authorities("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, userYesmine,user1);
    }
}
