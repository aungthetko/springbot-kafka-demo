package org.atk.springkafkademo.producer.impl;

import org.atk.springkafkademo.dto.Sneaker;
import org.atk.springkafkademo.producer.SneakerEventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class SneakerEventProducerImpl implements SneakerEventProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SneakerEventProducerImpl.class);
    @Autowired
    private KafkaTemplate<String, Sneaker> kafkaTemplate;

    @Override
    @Async
    public void publish(String topic, Sneaker sneaker) {
        UUID key = UUID.randomUUID();
        sneaker.setId(UUID.randomUUID().toString());
        sneaker.setVerified(Boolean.TRUE);
        sneaker.setAuthentic(Boolean.TRUE);
        CompletableFuture<SendResult<String, Sneaker>> completableFuture =
                kafkaTemplate.send(topic, key.toString(), sneaker);
        completableFuture.whenComplete((result, ex) -> {
            if (ex == null && ex.getMessage().equals("")){
                LOGGER.info("Sent Message success ==> ", result.getRecordMetadata().offset());
            }else{
                LOGGER.error("Sent message failed ==> ", ex.getMessage());
            }
        });
    }
}
