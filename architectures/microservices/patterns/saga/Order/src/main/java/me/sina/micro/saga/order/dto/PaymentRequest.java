package me.sina.micro.saga.order.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PaymentRequest implements Serializable {
    private Long orderId;
    private String amount;
    private String productName;
}
