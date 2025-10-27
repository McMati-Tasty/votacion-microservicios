package com.microservicio.padron.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenVotoGenerator {

    @Value("${jwt.secret}") 
    private String SECRET_KEY;

    private final long EXPIRATION_TIME_MS = 15 * 60 * 1000; 

    
    public String generateVotoToken() {
        
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME_MS);
        String anonymousId = UUID.randomUUID().toString();

        return Jwts.builder()
                .setSubject(anonymousId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}