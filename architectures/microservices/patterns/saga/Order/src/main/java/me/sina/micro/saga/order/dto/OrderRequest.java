package me.sina.micro.saga.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderRequest implements Serializable {
    private String amount;
    private String productName;
}
