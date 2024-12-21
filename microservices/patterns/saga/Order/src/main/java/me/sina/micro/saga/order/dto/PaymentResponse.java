package me.sina.micro.saga.order.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PaymentResponse implements Serializable {
    private boolean isSuccess;
    private String amount;
    private Long orderId;
}
