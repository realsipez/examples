package me.sina.client.mb.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class MessageUtil {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MessageUtil(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, String>> sendMessage(String topic, String message) {
        return kafkaTemplate.send(topic, message);
    }
}
