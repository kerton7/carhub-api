package com.alituran.service;

import com.alituran.jwt.JwtService;
import com.alituran.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2Service {

    private final JwtService jwtService;

    private final AuthService authService;


    public String oAuth2Login(OAuth2User oAuth2User) {

        String email = oAuth2User.getAttribute("email");

        String name = oAuth2User.getAttribute("name");

        if(email==null){
            throw new IllegalArgumentException("Email information not provided by Google");
        }
        User user = authService.saveOAuthUser(email, name);
        return jwtService.generateToken(user);
    }
}
