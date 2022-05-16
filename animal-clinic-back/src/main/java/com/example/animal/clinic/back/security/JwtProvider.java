package com.example.animal.clinic.back.security;

import com.example.animal.clinic.back.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.access.token.expired}")
    private long accessTokenValidityInMillis;
    @Value("${jwt.refresh.token.expired}")
    private long refreshTokenValidityInMillis;

    public String generateAccessToken(String login, List<Role> roles) {
        Date date = new Date(new Date().getTime() + accessTokenValidityInMillis);
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("scopes", roles);
        return Jwts.builder()
                .setSubject(login)
                .setClaims(claims)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


    public String generateRefreshToken(String login) {
        Date date = new Date(new Date().getTime() + refreshTokenValidityInMillis);
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}