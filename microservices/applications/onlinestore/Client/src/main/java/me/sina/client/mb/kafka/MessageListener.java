package me.sina.client.mb.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @KafkaListener(id = "myListener", topics = "mytopic")
    public void listen(String msg) {
        System.out.println("received message: " + msg);
    }
}
