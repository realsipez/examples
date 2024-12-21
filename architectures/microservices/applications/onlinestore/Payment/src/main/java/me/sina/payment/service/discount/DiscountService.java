package me.sina.payment.service.discount;

import me.sina.payment.dto.discount.CouponDTO;

public interface DiscountService {

    CouponDTO loadCouponByCode(String code);

    CouponDTO createCoupon(CouponDTO couponDTO);
}
