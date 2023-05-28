package net.greffchandler.clients.registrationClient.models;

import lombok.Data;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

@Data
public class ExchangeKeys {
    private String identityKey;
    private String signedPreKey;
    private String[] oneTimePreKeys;
    private String signature;

    public static ExchangeKeys generateDefault() {
        ExchangeKeys keys = new ExchangeKeys();
        keys.setIdentityKey(generateHexString());
        keys.setSignedPreKey(generateHexString());
        keys.setOneTimePreKeys(List.of(generateHexString(), generateHexString(), generateHexString()).toArray(new String[0]));
        keys.setSignature("thisIsASignature");
        return keys;
    }

    public static String generateHexString() {
        return new BigInteger(130, new SecureRandom()).toString(16);
    }
}