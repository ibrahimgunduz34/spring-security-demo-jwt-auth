package org.example.security.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public interface JwtService {
    String generate(String username, Integer ttlInMs);
    boolean isTokenValid(String token, String username);
    String extractUsername(String token);
    Date extractExpirationDate(String token);

    static SecretKey createSecretKey(String encodedSecretKeyString) {
        final byte[] keyBytes = Decoders.BASE64.decode(encodedSecretKeyString);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
