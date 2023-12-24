package org.atk.springkafkademo.controller;

import org.atk.springkafkademo.configuration.SneakerTopicConfiguration;
import org.atk.springkafkademo.dto.Sneaker;
import org.atk.springkafkademo.producer.SneakerEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sneakers")
public class SneakerResource {

    @Autowired
    private SneakerEventProducer sneakerEventProducer;

    @Autowired
    private SneakerTopicConfiguration sneakerTopicConfiguration;

    @PostMapping("/create")
    public ResponseEntity<Sneaker> register(@RequestBody Sneaker sneaker){
        sneakerEventProducer.publish(sneakerTopicConfiguration.getCreateEventTopic(), sneaker);
        return new ResponseEntity<>(sneaker, HttpStatus.CREATED);
    }
}
