package me.sina.payment.dto.discount;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CouponDTO implements Serializable {
    private Long id;
    private String code;
    private BigDecimal discount;
    private LocalDate expiryDate;
}
