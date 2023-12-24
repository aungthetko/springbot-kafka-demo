package org.atk.springkafkademo.producer;

import org.atk.springkafkademo.dto.Sneaker;

public interface SneakerEventProducer {

    void publish(String topic, Sneaker sneaker);

}
