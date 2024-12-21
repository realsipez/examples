package me.sina.micro.saga.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderResponse implements Serializable {
    private Long orderId;
    private String amount;
    private String productName;
}
