package com.alituran.configuration;

import com.alituran.jwt.AuthEntryPoint;
import com.alituran.jwt.JwtAuthenticationFilter;
import com.alituran.jwt.JwtService;
import com.alituran.utils.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    public static final String REGISTER = "/register";

    public static final String AUTHENTICATE = "/authenticate";

    public static final String REFRESHTOKEN = "/refreshtoken";



    private final AuthenticationProvider authenticationProvider;

    private final AuthEntryPoint authEntryPoint;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final OAuth2SuccessHandler  oAuth2SuccessHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                        request->request.
                                requestMatchers(REGISTER,AUTHENTICATE,REFRESHTOKEN,"/oauth2/**","/login/**").
                                permitAll().anyRequest().authenticated())
                .exceptionHandling(exception->{
                    exception.authenticationEntryPoint(authEntryPoint);
                })
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2Login(oAuth2Login->
                        oAuth2Login.successHandler(oAuth2SuccessHandler))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);
        return http.build();
    }


}
