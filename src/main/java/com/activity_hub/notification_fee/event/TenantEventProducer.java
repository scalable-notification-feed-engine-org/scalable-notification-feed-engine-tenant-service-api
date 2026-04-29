package com.activity_hub.notification_fee.event;

import com.activity_hub.notification_fee.dto.event.TenantCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TenantEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${app.kafka.topic.tenant-events}")
    private String topicName;

    public void sendTenantCreatEvent(TenantCreatedEvent event){
        log.info("Publishing Tenant Created Event to Kafka: {}", event.slug());

        kafkaTemplate.send(topicName, event.slug(), event);
    }

}
