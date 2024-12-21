package me.sina.payment.model.discount;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String code;
    @NonNull
    private BigDecimal discount;
    @NonNull
    @Column(name = "expiry_date")
    @JsonProperty("date")
    private LocalDate expiryDate;
}