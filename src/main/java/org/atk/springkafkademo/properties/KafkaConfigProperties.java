package org.atk.springkafkademo.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("kafka")
public class KafkaConfigProperties {

    private String bootstrapServer;
    private KafkaConsumerProperties consumerProperties;
}
