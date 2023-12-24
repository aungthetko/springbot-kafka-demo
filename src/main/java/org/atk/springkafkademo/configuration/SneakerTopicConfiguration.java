package org.atk.springkafkademo.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties("topic")
public class SneakerTopicConfiguration {

    private String createEventTopic;

}
