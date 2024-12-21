package me.sina.client;

import me.sina.client.mb.kafka.MessageListener;
import me.sina.client.mb.kafka.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@EnableKafka
@SpringBootApplication
public class Client implements CommandLineRunner {

    private final String message = "Hello Sina!";
    private final MessageUtil messageUtil;

    @Autowired
    public Client(MessageUtil messageUtil, MessageListener listener) {
        this.messageUtil = messageUtil;
    }

    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CompletableFuture<SendResult<String, String>> future = messageUtil.sendMessage("mytopic", message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message = [" + message +
                        "] with offset = [" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }
}
