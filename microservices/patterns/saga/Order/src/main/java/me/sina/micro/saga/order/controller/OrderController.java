package me.sina.micro.saga.order.controller;

import me.sina.micro.saga.order.dto.OrderRequest;
import me.sina.micro.saga.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order/")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/create")
    public void saveOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
    }
}
