package org.atk.springkafkademo.token;

public interface KeyCloakClient {

    public String generateToken(String clientId, String clientSecret, String grantType, String username, String password);
}
