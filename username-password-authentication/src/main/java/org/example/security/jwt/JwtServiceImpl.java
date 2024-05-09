package org.example.security.jwt;

import org.springframework.stereotype.Component;

import java.util.Date;

public class JwtServiceImpl implements JwtService {
    private final JwtGenerator jwtGenerator;
    private final JwtResolver jwtResolver;

    public JwtServiceImpl(JwtGenerator jwtGenerator, JwtResolver jwtResolver) {
        this.jwtGenerator = jwtGenerator;
        this.jwtResolver = jwtResolver;
    }

    @Override
    public String generate(String username, Integer ttlInMs) {
        return jwtGenerator.generate(username, ttlInMs);
    }

    @Override
    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) &&
                !isTokenExpired(token);
    }

    @Override
    public String extractUsername(String token) {
        return jwtResolver.extractUsername(token);
    }

    @Override
    public Date extractExpirationDate(String token) {
        return jwtResolver.extractExpirationDate(token);
    }

    private boolean isTokenExpired(String token) {
        return jwtResolver.extractExpirationDate(token).before(new Date());
    }
}
