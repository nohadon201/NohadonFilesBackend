package com.nohadon.NohadonFiles.core.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class BasicAuthSecurity (
    @Value("\${ADMIN_USR_NF}") val userName : String,
    @Value("\${ADMIN_PASSWD_NF}") val password : String
) {
        @Bean
        fun securityFilterChain(http : HttpSecurity) : SecurityFilterChain {
            http.authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/projects/delete").authenticated()
                    .requestMatchers("/projects/create").authenticated()
                    .anyRequest().permitAll()
            }
            http.csrf {
                it.disable()
            }
            http.httpBasic(Customizer.withDefaults())
            return http.build();
        }

        @Bean
        fun userDetailsService() : UserDetailsService {
            val user : UserDetails =
            User.withUsername(userName)
                .password("{noop}$password")
                .roles("USER")
                .build();
            return InMemoryUserDetailsManager(user);
        }
}