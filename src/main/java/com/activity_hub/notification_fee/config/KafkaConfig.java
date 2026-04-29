package com.activity_hub.notification_fee.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Value("${app.kafka.topic.tenant-events}")
    private String topicName;

    @Bean
    public NewTopic tenantEventTopic() {
       return TopicBuilder.name(topicName)
               .partitions(3)
               .replicas(1)
               .build();
    }
}
