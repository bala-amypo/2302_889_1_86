package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
    private boolean keyInitialized = false;

    // ✅ FIXED: EMPTY constructor - no initialization
    public JwtTokenProvider() {
        // Do NOTHING here - let Spring inject @Value first
    }

    // ✅ LAZY INITIALIZATION - called on first use
    private void ensureKeyInitialized() {
        if (!keyInitialized) {
            if (jwtSecret == null || jwtSecret.length() < 32) {
                jwtSecret = "mySecretKeyThatIsLongEnoughForHMACSHA256AtLeast32CharsLongerIsBetter";
                log.warn("JWT secret too short, using default");
            }
            this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
            this.keyInitialized = true;
            log.info("JWT key initialized successfully");
        }
    }

    public String createToken(Long userId, String email, String role) {
        ensureKeyInitialized();
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
            ensureKeyInitialized();
            parseClaims(token);
            return true;
        } catch (Exception e) {
            log.error("Invalid JWT token", e);
            return false;
        }
    }

    public String getEmail(String token) {
        ensureKeyInitialized();
        return parseClaims(token).getSubject();
    }

    public Long getUserId(String token) {
        ensureKeyInitialized();
        return parseClaims(token).get("userId", Long.class);
    }

    public String getRole(String token) {
        ensureKeyInitialized();
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
