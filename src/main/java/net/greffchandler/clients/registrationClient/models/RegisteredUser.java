package net.greffchandler.clients.registrationClient.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisteredUser {
    private String id;
    private String jid;
    private String displayName;
    private String username;
    private String image;
    private String ephemeralPassword;
    private LocalDateTime joinedAt;
    private LocalDateTime editedAt;
}