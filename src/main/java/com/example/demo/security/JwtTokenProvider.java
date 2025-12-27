package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
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

    public JwtTokenProvider() {}

    private void ensureKeyInitialized() {
        if (!keyInitialized) {
            if (jwtSecret == null || jwtSecret.length() < 32) {
                jwtSecret = "mySecretKeyThatIsLongEnoughForHMACSHA256AtLeast32CharsLongerIsBetter";
            }
            this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
            this.keyInitialized = true;
        }
    }

    public String createToken(Long userId, String email, String role) {
        ensureKeyInitialized();
        
        // ✅ JJWT 0.11.5: Add claims INDIVIDUALLY (no .claims() method)
        return Jwts.builder()
                .claim("userId", userId)                    // ✅ Individual claims
                .claim("role", role)                        // ✅ Individual claims
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            ensureKeyInitialized();
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Invalid JWT token", e);
            return false;
        }
    }

    public String getEmail(String token) {
        ensureKeyInitialized();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Long getUserId(String token) {
        ensureKeyInitialized();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("userId", Long.class);
    }

    public String getRole(String token) {
        ensureKeyInitialized();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }
}
