package com.fiap.airlogic.api.security;

import java.util.Date;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private long expirationMs;

    private SecretKey key() {
        // Nova API: cria SecretKey HMAC a partir do segredo
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {
        var now = new Date();
        var exp = new Date(now.getTime() + expirationMs);

        // Nova API fluent: sem SignatureAlgorithm enum, sem set*
        return Jwts.builder().subject(username).issuedAt(now).expiration(exp).signWith(key(), Jwts.SIG.HS256) // sem deprecated
                .compact();
    }

    public String validateAndGetSubject(String token) {
        // Nova API de parser/verify: sem parserBuilder/setSigningKey deprecados
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
