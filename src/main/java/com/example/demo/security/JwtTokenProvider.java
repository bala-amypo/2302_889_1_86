package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secret;
    private final long jwtExpirationMs;

    public JwtTokenProvider(
            @Value("${app.jwt.secret:ThisIsAReallyLongJwtSecretKeyForDemoProject123456}") String secret,
            @Value("${app.jwt.expiration-ms:86400000}") long jwtExpirationMs) {
        this.secret = secret;
        this.jwtExpirationMs = jwtExpirationMs;
    }

    public String createToken(Long userId, String email, String role) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException |
                 MalformedJwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public String getEmail(String token) {
        return parseClaims(token).getBody().get("email", String.class);
    }

    public Long getUserId(String token) {
        Number n = parseClaims(token).getBody().get("userId", Number.class);
        return n == null ? null : n.longValue();
    }

    public String getRole(String token) {
        return parseClaims(token).getBody().get("role", String.class);
    }

    private Jws<Claims> parseClaims(String token) {
        // jjwt 0.9.1: parser() returns JwtParser directly [web:40]
        JwtParser parser = Jwts.parser().setSigningKey(secret);
        return parser.parseClaimsJws(token);
    }
}
