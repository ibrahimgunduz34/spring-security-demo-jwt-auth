package org.example;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtGeneratorImpl implements JwtGenerator {
    private final SecretKey secretKey;


    public JwtGeneratorImpl(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String generate(String username, Integer ttlInMs) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ttlInMs))
                .signWith(secretKey)
                .compact();
    }
}
