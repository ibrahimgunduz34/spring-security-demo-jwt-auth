package org.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.security")
public class SecurityConfigProperties {
    private String secret;
    private Integer ttl;

    public String getSecret() {
        return secret;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }
}
