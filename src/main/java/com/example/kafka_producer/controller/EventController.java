package com.example.kafka_producer.controller;

import com.example.kafka_producer.dto.Customer;
import com.example.kafka_producer.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;

    @PostMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try {
            for(int i =0; i<=10000;i++) {
                publisher.sendMessageToConsumer(message);
            }
            return ResponseEntity.ok("message published successfully....");
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/publish/event")
    public ResponseEntity<Customer> publishEvent(@RequestBody Customer customer) {
        try {
            for(int i =0; i<=10000;i++) {
                publisher.sendEventToConsumer(customer);
            }
            return ResponseEntity.ok(customer);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
