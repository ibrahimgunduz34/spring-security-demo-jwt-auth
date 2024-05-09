package org.example;

public interface JwtGenerator {
    String generate(String username, Integer ttlInMs);
}
