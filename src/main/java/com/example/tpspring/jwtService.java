package com.example.tpspring;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class jwtService {

    private static final String SECRET_KEY = "votre_cle_secrete";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 heures
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
    public static User extractUserFromToken(String token) {
        Claims claims = extractClaims(token);
        User user = new User();
        user.setId((Integer) claims.get("id")); // Assurez-vous que l'ID est bien un Integer
        user.setUsername(claims.getSubject());
        user.setRole((String) claims.get("role"));
        return user;
    }
}
