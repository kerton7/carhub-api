package com.alituran.controller;

import com.alituran.dto.AuthResponse;
import com.alituran.dto.RefreshTokenRequest;
import com.alituran.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RefreshTokenController {


    private final RefreshTokenService refreshTokenService;


    @PostMapping(path = "/refreshtoken")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws Exception {
        return ResponseEntity.ok(refreshTokenService.refreshToken(refreshTokenRequest));
    }

}
