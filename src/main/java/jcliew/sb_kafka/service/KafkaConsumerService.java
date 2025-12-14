package jcliew.sb_kafka.service;

import jcliew.sb_kafka.model.ActivityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {
    @KafkaListener(topics = "${my.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ActivityEvent event){
        log.info("Consumed message from topic: {}", event);
    }
}
