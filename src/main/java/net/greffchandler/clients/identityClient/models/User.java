package net.greffchandler.clients.identityClient.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String id;
    private String jid;
    private String displayName;
    private String username;
    private String image;
    private LocalDateTime joinedAt;
}
