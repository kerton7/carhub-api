package com.alituran.service;

import com.alituran.dto.AuthResponse;
import com.alituran.dto.RefreshTokenRequest;
import com.alituran.jwt.JwtService;
import com.alituran.model.RefreshToken;
import com.alituran.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtService jwtService;


    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws Exception {

        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken
                        (refreshTokenRequest.getRefreshToken()).
                orElseThrow(() -> new UsernameNotFoundException("Refresh token not found"));

        if(!isTokenExpired(refreshToken)){
            throw new Exception("Token is expired");
        }
        RefreshToken newRefreshToken = jwtService.generateRefreshToken(refreshToken.getUser());
        String accessToken = jwtService.generateToken(newRefreshToken.getUser());
        refreshTokenRepository.save(newRefreshToken);
        return new AuthResponse(accessToken,newRefreshToken.getRefreshToken());
    }

    protected void saveRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    private boolean isTokenExpired(RefreshToken refreshToken) {
        return new Date().before(refreshToken.getExpireDate());
    }

}
