package org.example.security.jwt;

public interface JwtGenerator {
    String generate(String username, Integer ttlInMs);
}
