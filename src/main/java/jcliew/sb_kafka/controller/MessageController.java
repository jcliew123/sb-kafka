package jcliew.sb_kafka.controller;

import jcliew.sb_kafka.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
public class MessageController {
    private final KafkaProducerService producerService;

    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestBody String message) {
        producerService.sendMessage(message);
        return ResponseEntity.ok("Message sent to Kafka topic successfully");
    }
}
