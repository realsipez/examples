package me.sina.micro.saga.payment.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.sina.micro.saga.payment.dto.PaymentRequest;
import me.sina.micro.saga.payment.dto.PaymentResponse;
import me.sina.micro.saga.payment.model.Payment;
import me.sina.micro.saga.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(KafkaTemplate<String, String> kafkaTemplate, PaymentRepository paymentRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.paymentRepository = paymentRepository;
    }

    @KafkaListener(topics = "payment-request-topic", groupId = "saga-group", containerFactory = "paymentListener")
    public void processPayment(String request) {
        Gson gson = new GsonBuilder().create();
        PaymentRequest paymentRequest = gson.fromJson(request, PaymentRequest.class);
        Payment payment = new Payment();
        PaymentResponse paymentResponse = new PaymentResponse();
        if (paymentRequest.getProductName().isEmpty()) {
            paymentResponse.setOrderId(paymentRequest.getOrderId());
            paymentResponse.setAmount(paymentRequest.getAmount());
            paymentResponse.setSuccess(Boolean.FALSE);
        } else {
            payment.setAmount(paymentRequest.getAmount());
            payment.setSuccess(Boolean.TRUE);
            payment.setProductName(paymentRequest.getProductName());
            paymentRepository.save(payment);
            paymentResponse.setOrderId(paymentRequest.getOrderId());
            paymentResponse.setAmount(paymentRequest.getAmount());
            paymentResponse.setSuccess(Boolean.TRUE);
        }
        String response = gson.toJson(paymentResponse, paymentResponse.getClass());
        kafkaTemplate.send("payment-response-topic", response);
    }

    @Override
    public String refundPayment(Long orderId, String amount) {
        return "refund payment success with order id " + orderId + " and amount " + amount;
    }
}
