package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    
    @Value("${app.jwt.expiration-ms:86400000}")
    private long jwtExpirationMs;
    
    private SecretKey key;

    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String createToken(Long userId, String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);
        
        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            log.error("Invalid JWT token", e);
            return false;
        }
    }

    public String getEmail(String token) {
        return parseClaims(token).getSubject();
    }

    public Long getUserId(String token) {
        return parseClaims(token).get("userId", Long.class);
    }

    public String getRole(String token) {
        return parseClaims(token).get("role", String.class);
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }
}
