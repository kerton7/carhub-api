package com.alituran.service;

import com.alituran.dto.AuthRequest;
import com.alituran.dto.AuthResponse;
import com.alituran.jwt.JwtService;
import com.alituran.mapper.UserMapper;
import com.alituran.model.RefreshToken;
import com.alituran.model.User;
import com.alituran.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationProvider authenticationProvider;

    private final RefreshTokenService refreshTokenService;

    public AuthRequest register(AuthRequest authRequest){
        User user = userMapper.toUser(authRequest);
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        authRepository.save(user);
        return authRequest;
    }


    public AuthResponse authenticate(AuthRequest authRequest){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        authenticationProvider.authenticate(authenticationToken);

        User user = authRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        String accessToken = jwtService.generateToken(user);

        RefreshToken refreshToken = jwtService.generateRefreshToken(user);
        refreshTokenService.saveRefreshToken(refreshToken);
        return new AuthResponse(accessToken,refreshToken.getRefreshToken());
    }

}
