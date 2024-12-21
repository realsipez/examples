package me.sina.order.service;

import me.sina.client.rest.payment.discount.PaymentClient;
import me.sina.order.dto.OrderDTO;
import me.sina.order.model.Order;
import me.sina.order.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final PaymentClient paymentClient;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    @Autowired
    public OrderServiceImpl(PaymentClient paymentClient, OrderRepository orderRepository, ModelMapper mapper) {
        this.paymentClient = paymentClient;
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = mapper.map(orderDTO, Order.class);
        Order savedOrder = orderRepository.save(order);
        paymentClient.pay(savedOrder.getTotalPrice());
        return mapper.map(savedOrder, OrderDTO.class);
    }
}
