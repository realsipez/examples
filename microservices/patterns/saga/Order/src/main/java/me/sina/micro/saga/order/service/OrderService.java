package me.sina.micro.saga.order.service;

import me.sina.micro.saga.order.dto.OrderRequest;

public interface OrderService {
    void createOrder(OrderRequest orderRequest);
}
