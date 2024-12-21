package me.sina.payment.controller.payment;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @PostMapping("/pay/{totalPrice}")
    public String pay(@PathVariable("totalPrice") String totalPrice) {
        return "Payment successful with total price: " + totalPrice;
    }
}
