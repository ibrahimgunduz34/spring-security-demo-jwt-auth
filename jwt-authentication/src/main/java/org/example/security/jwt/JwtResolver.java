package org.example.security.jwt;

import java.util.Date;

public interface JwtResolver {
    String extractUsername(String token);
    Date extractExpirationDate(String token);
}
