package me.sina.micro.saga.payment.service;

public interface PaymentService {

    String refundPayment(Long orderId, String amount);
}
