package com.demo.springkafkademo.producer;

import com.demo.springkafkademo.dto.Sneaker;

public interface SneakerEventProducer {

    void publish(String topic, Sneaker sneaker);

}
