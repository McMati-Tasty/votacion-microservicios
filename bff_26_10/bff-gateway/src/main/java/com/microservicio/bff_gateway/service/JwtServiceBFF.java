package com.microservicio.bff_gateway.service;

import java.security.Key;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceBFF {

    private static final String SECRET_KEY = "sadasdasdasdasdasdasdasdasdasdapdasdasdasdasa12123dsadasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdree";

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(getKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    public boolean isTokenValid(String token) {
        //  solo checamos que no esté expirado
        return getAllClaims(token).getExpiration().after(new java.util.Date());
    }

    public String getRolFromToken(String token) {
        Claims claims = getAllClaims(token);
        return claims.get("rol", String.class);
    }
}