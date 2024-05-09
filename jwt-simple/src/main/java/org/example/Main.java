package org.example;
import javax.crypto.SecretKey;

public class Main {
    public static void main(String[] args) {
        final String SECRET_KEY = "d201cXE5dEZMRU1VcGFOUVV5Z3NESmFUclN1QlROT1E=";
        final SecretKey secretKey = JwtService.createSecretKey(SECRET_KEY);

        final JwtResolver jwtResolver = new JwtResolverImpl(secretKey);
        final JwtGenerator jwtGenerator = new JwtGeneratorImpl(secretKey);
        final JwtService jwtService = new JwtServiceImpl(jwtGenerator, jwtResolver);

        final String username = "user";
        final Integer ttlInMs = 30 * 60 * 1000;

        // Generate jwt
        String token = jwtService.generate(username, ttlInMs);
        System.out.println(token);

        // Validate jwt
        assert jwtService.isTokenValid(token, username);

        // Extract username from the token
        String tokenUsername = jwtService.extractUsername(token);
        assert tokenUsername.equals(username);

        System.out.println(tokenUsername);
    }
}