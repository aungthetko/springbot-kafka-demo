package org.atk.springkafkademo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "keycloak.config")
public class KeyCloakConfiguration {

    private String protocol;
    private String host;
    private String port;
    private String basePath;
    private String jwtSetUri;

}
