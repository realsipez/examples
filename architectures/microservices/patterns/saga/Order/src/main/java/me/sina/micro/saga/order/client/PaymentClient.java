package me.sina.micro.saga.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("PAYMENT")
public interface PaymentClient {

    @PostMapping("/api/v1/payment/refund")
    String refund(@RequestParam Long orderId, @RequestParam String amount);
}
