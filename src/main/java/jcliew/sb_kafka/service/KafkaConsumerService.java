package jcliew.sb_kafka.service;

import jcliew.sb_kafka.model.ActivityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {
//    @KafkaListener(topics = "${my.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
//    public void consume(ActivityEvent event){
//        log.info("Consumed message from topic: {}", event);
//    }

    @RetryableTopic
    @KafkaListener(topics = "${my.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void testDlt(ActivityEvent event){
        log.info("Attempt to consume message from topic: {}", event);
        throw new RuntimeException("Simulation of error thrown during consumption of message");
    }

    @DltHandler
    public void handleDltEvent(@Payload ActivityEvent event,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                               @Header(KafkaHeaders.EXCEPTION_MESSAGE) String errorMessage){
        log.error("!!! MESSAGE IN DLT !!!");
        log.error("Topic: {}", topic);
        log.error("Error Message: {}", errorMessage);
        log.error("Payload: {}", event);
    }
}
