package me.sina.client.rest.payment.discount.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponDTO implements Serializable {
    private Long id;
    private String code;
    private BigDecimal discount;
    private LocalDate expiryDate;
}
