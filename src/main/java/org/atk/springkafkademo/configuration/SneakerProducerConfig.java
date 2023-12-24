package org.atk.springkafkademo.configuration;

import org.atk.springkafkademo.dto.Sneaker;
import org.atk.springkafkademo.properties.KafkaConfigProperties;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SneakerProducerConfig {

    @Bean
    Map<String, Object> producerConfigs(KafkaConfigProperties properties){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    ProducerFactory<String, Sneaker> producerFactory(KafkaConfigProperties properties){
        return new DefaultKafkaProducerFactory<>(producerConfigs(properties));
    }

    @Bean
    KafkaTemplate<String, Sneaker> kafkaTemplate(KafkaConfigProperties properties){
        return new KafkaTemplate<>(producerFactory(properties));
    }
}
