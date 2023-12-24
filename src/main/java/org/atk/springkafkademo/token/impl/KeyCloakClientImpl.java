package org.atk.springkafkademo.token.impl;

import jakarta.annotation.PostConstruct;
import org.atk.springkafkademo.configuration.KeyCloakConfiguration;
import org.atk.springkafkademo.response.TokenResponse;
import org.atk.springkafkademo.token.KeyCloakClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class KeyCloakClientImpl implements KeyCloakClient {

    @Autowired
    private KeyCloakConfiguration keyCloakConfiguration;

    @Autowired
    private RestTemplate restTemplate;

    private String keyCloakUrl;

    @PostConstruct
    private void postConstruct(){
        this.keyCloakUrl =
                keyCloakConfiguration.getProtocol() + "://" + keyCloakConfiguration.getHost() + ":" +
                        keyCloakConfiguration.getPort() + keyCloakConfiguration.getBasePath() +
                        keyCloakConfiguration.getJwtSetUri();
    }

    @Override
    public String generateToken(String clientId, String clientSecret, String grantType,
                                String username, String password) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("grant_type", grantType);
        map.add("username", username);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, new HttpHeaders());
        String response = null;
        try{
            response = restTemplate.postForObject(keyCloakUrl, request, String.class);
        }catch(HttpClientErrorException exception){
            String errorString = exception.getResponseBodyAsString();
        }
        return response;
    }
}
