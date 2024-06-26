package com.dewitt.petshopapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static com.dewitt.petshopapi.user.UserRole.MANAGER;
import static com.dewitt.petshopapi.user.UserRole.CLIENT;
import static com.dewitt.petshopapi.user.Permission.CLIENT_CREATE;
import static com.dewitt.petshopapi.user.Permission.CLIENT_READ;
import static com.dewitt.petshopapi.user.Permission.CLIENT_UPDATE;
import static com.dewitt.petshopapi.user.Permission.CLIENT_DELETE;
import static com.dewitt.petshopapi.user.Permission.MANAGER_CREATE;
import static com.dewitt.petshopapi.user.Permission.MANAGER_READ;
import static com.dewitt.petshopapi.user.Permission.MANAGER_UPDATE;
import static com.dewitt.petshopapi.user.Permission.MANAGER_DELETE;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpMethod.PATCH;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    private static final String[] WHITE_LIST_URL = {"/api/auth/**"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize->
                        authorize.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/api/product/**").hasAnyRole(CLIENT.name(),MANAGER.name())
                                .requestMatchers(GET, "/api/product/**").hasAnyAuthority(CLIENT_READ.name(), MANAGER_READ.name())
                                .requestMatchers(POST, "/api/product/**").hasAuthority(MANAGER_CREATE.name())
                                .requestMatchers(PUT, "/api/product/**").hasAuthority(MANAGER_UPDATE.name())
                                .requestMatchers(PATCH, "/api/product/**").hasAuthority(MANAGER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/product/**").hasAuthority(MANAGER_DELETE.name())
                                .requestMatchers("/api/shop/**").hasRole(CLIENT.name())
                                .requestMatchers(GET, "/api/shop/**").hasAuthority(CLIENT_READ.name())
                                .requestMatchers(POST, "/api/shop/**").hasAuthority(CLIENT_CREATE.name())
                                .requestMatchers(PUT, "/api/shop/**").hasAuthority(CLIENT_UPDATE.name())
                                .requestMatchers(PATCH, "/api/shop/**").hasAuthority(CLIENT_UPDATE.name())
                                .requestMatchers(DELETE, "/api/shop/**").hasAuthority(CLIENT_DELETE.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;
        return http.build();
    }
}
