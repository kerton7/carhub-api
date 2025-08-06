package com.alituran.jwt;

import com.alituran.model.RefreshToken;
import com.alituran.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtService {

    public static final String SECRET_KEY="JrMJrrVNmPZ9XX7Ax6N1tv4FRPr40NOpzliAODF91ks=";


    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public <T> T exportToken(String token, Function<Claims,T> claimsFunction) {
        Claims body = Jwts.parserBuilder().
                setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
        return claimsFunction.apply(body);
    }

    public RefreshToken generateRefreshToken(User user) {
        String refreshToken = UUID.randomUUID().toString();
        Date expiration = new Date(System.currentTimeMillis()+1000*60*60*4);

        return new RefreshToken(null,refreshToken,expiration,user);
    }

    public String getUsernameFromToken(String token) {
        return  exportToken(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date date = exportToken(token, Claims::getExpiration);
        return new Date().before(date);
    }


    public Key getKey(){
        byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decode);
    }


}
