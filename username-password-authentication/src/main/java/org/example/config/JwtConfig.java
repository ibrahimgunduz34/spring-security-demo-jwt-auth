package org.example.config;

import org.example.security.jwt.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {
    @Bean
    JwtService jwtService(SecurityConfigProperties configProperties) {
        SecretKey secretKey = JwtService.createSecretKey(configProperties.getSecret());
        JwtGenerator generator = new JwtGeneratorImpl(secretKey);
        JwtResolver jwtResolver = new JwtResolverImpl(secretKey);
        return new JwtServiceImpl(generator, jwtResolver);
    }
}
