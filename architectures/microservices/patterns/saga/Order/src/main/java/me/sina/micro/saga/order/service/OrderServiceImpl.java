package me.sina.micro.saga.order.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.sina.micro.saga.order.client.PaymentClient;
import me.sina.micro.saga.order.dto.OrderRequest;
import me.sina.micro.saga.order.dto.PaymentRequest;
import me.sina.micro.saga.order.dto.PaymentResponse;
import me.sina.micro.saga.order.model.Order;
import me.sina.micro.saga.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    @Autowired
    public OrderServiceImpl(KafkaTemplate<String, String> kafkaTemplate, OrderRepository orderRepository, PaymentClient paymentClient) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderRepository = orderRepository;
        this.paymentClient = paymentClient;
    }

    @Override
    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setAmount(orderRequest.getAmount());
        order.setState("WAITING FOR PAYMENT");
        Order savedOrder = orderRepository.save(order);
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderId(savedOrder.getId());
        paymentRequest.setAmount(savedOrder.getAmount());
        paymentRequest.setProductName(orderRequest.getProductName());
        Gson gson = new GsonBuilder().create();
        String request = gson.toJson(paymentRequest, PaymentRequest.class);
        kafkaTemplate.send("payment-request-topic", request);
    }

    @KafkaListener(topics = "payment-response-topic", groupId = "saga-group", containerFactory = "orderListener")
    public void handlePaymentResponse(String response) {
        Gson gson = new GsonBuilder().create();
        PaymentResponse paymentResponse = gson.fromJson(response, PaymentResponse.class);
        if (paymentResponse.isSuccess()) {
            updateOrderState(paymentResponse.getOrderId(), "COMPLETED");
        } else {
            updateOrderState(paymentResponse.getOrderId(), "FAILED");
            handleCompensation(paymentResponse.getOrderId(), paymentResponse.getAmount());
        }
    }

    private void handleCompensation(Long orderId, String amount) {
        paymentClient.refund(orderId, amount);
    }

    private void updateOrderState(Long orderId, String state) {
        Optional<Order> order = orderRepository.findOrderById(orderId);
        if (order.isPresent()) {
            order.get().setState(state);
            orderRepository.save(order.get());
        }
    }
}