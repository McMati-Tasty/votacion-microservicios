package com.microservicio.padron.security;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.microservicio.padron.entidades.Ciudadano;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {


    @Value("${jwt.secret}")
    private String SECRET_KEY;

 
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

  
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()) 
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

   
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

   
    /**
      genera un token de acceso (con el DNI) para el ciudadano.
      este es el JWT que se usará para votar.
     */
    public String generateAuthToken(Ciudadano ciudadano) {
        return Jwts.builder()
                .setSubject(ciudadano.getDni()) // <-- El "Subject" es el DNI
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // <-- Usa la clave correcta
                .compact();
    }

    /**
      extrae el DNI (el "subject") del token.
     */
    public String extractDni(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * extrae la fecha de expiracion.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     verifica si el token esta expirado.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
    valida un token (JWT de acceso) contra un ciudadano. 
    revisa que el DNI coincida y que no esté expirado.
     */
    public boolean isTokenValid(String token, Ciudadano ciudadano) {
        final String dni = extractDni(token);
        // compara el dni del token con el dni del ciudadano Y revisa la expiración
        return (dni.equals(ciudadano.getDni()) && !isTokenExpired(token));
    }

  
}