package com.alituran.controller;

import com.alituran.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;


    @GetMapping("/oauth2login")
    public ResponseEntity<String> oAuth2Login(@AuthenticationPrincipal OAuth2User oAuth2User){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(oAuth2User==null){
            return ResponseEntity.status(401).body("Unauthorized: OAuth2User not found");
        }

        if(principal instanceof OAuth2User){
            oAuth2User = (OAuth2User) principal;
        }

      return  ResponseEntity.ok(oAuth2Service.oAuth2Login(oAuth2User));
    }



}
