package org.example.api.dto;

import java.util.Date;

public class LoginResponse {
    private final Long timestamp;
    private final String accessToken;
    private final Date expiresAt;

    public LoginResponse(Long timestamp, String accessToken, Date expiresAt) {
        this.timestamp = timestamp;
        this.accessToken = accessToken;
        this.expiresAt = expiresAt;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }
}
