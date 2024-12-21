package me.sina.order.service;

import me.sina.order.dto.OrderDTO;

public interface OrderService {
    OrderDTO saveOrder(OrderDTO orderDTO);
}
