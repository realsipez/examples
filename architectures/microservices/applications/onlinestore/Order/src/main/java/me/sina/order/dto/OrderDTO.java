package me.sina.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderDTO implements Serializable {
    private Long id;
    private String code;
    private String totalPrice;
}
