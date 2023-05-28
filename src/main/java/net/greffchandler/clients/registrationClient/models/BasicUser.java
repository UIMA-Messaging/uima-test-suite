package net.greffchandler.clients.registrationClient.models;

import lombok.Data;

@Data
public class BasicUser {
    private String displayName;
    private String image;
    private ExchangeKeys exchangeKeys;

    public static BasicUser generateDefault(String displayName) {
        BasicUser basicUser = new BasicUser();
        basicUser.setDisplayName(displayName);
        basicUser.setImage(null);
        basicUser.setExchangeKeys(ExchangeKeys.generateDefault());
        return basicUser;
    }
}