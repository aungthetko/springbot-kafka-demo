package org.atk.springkafkademo.controller;

import org.atk.springkafkademo.token.KeyCloakClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/create")
public class TokenResource {

    @Autowired
    private KeyCloakClient keyCloakClient;

    @PostMapping("/token")
    public String createToken(String client_id, String client_secret, String grant_type, String username, String password){
        String token = keyCloakClient.generateToken(client_id, client_secret, grant_type, username, password);
        return token;
    }
}
