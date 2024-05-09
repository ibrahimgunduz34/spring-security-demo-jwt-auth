package org.example;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public interface JwtService {
    String generate(String username, Integer ttlInMs);
    boolean isTokenValid(String token, String username);
    String extractUsername(String token);

    static SecretKey createSecretKey(String encodedSecretKeyString) {
        final byte[] keyBytes = Decoders.BASE64.decode(encodedSecretKeyString);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
