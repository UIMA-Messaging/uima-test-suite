package net.greffchandler.clients.authClient.models;

import lombok.Data;

@Data
public class TokenResponse {
    private String accessToken;
    private int expiresIn;
    public String tokenType;
}
