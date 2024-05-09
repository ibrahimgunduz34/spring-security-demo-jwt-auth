package org.example.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

public class JwtResolverImpl implements JwtResolver {
    private final JwtParser jwtParser;

    public JwtResolverImpl(SecretKey secretKey) {
        this.jwtParser = Jwts.parser()
                .verifyWith(secretKey)
                .build();
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) throws JwtException {
        return jwtParser.parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) throws JwtException {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }
}
