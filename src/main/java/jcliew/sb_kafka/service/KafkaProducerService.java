package jcliew.sb_kafka.service;

import jcliew.sb_kafka.model.ActivityEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${my.kafka.topic}")
    private String topicName;

    public void sendMessage(String message){
        log.info("Producing message to topic {}: {}", topicName, message);

        // The send method returns a CompletableFuture for asynchronous handling
        kafkaTemplate.send(topicName, message).whenComplete(
                (result, ex) -> {
                    if(ex == null){
                        log.info("Message sent successfully. Offset: {}", result.getRecordMetadata().offset());
                    }else {
                        log.error("Failed to send message: {}", ex.getMessage());
                    }
                }
        );
    }

    public void sendMessage(ActivityEvent event){
        log.info("Sending event to topic {}: {}", topicName, event.toString());

        // The send method returns a CompletableFuture for asynchronous handling
        kafkaTemplate.send(topicName, event).whenComplete(
                (result, ex) -> {
                    if(ex == null){
                        log.info("Event sent successfully. partition: {} Offset: {}", result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
                    }else {
                        log.error("Failed to send event: {}", ex.getMessage());
                    }
                }
        );
    }
}
