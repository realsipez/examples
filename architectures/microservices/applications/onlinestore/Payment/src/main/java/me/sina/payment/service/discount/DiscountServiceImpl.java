package me.sina.payment.service.discount;


import me.sina.payment.dto.discount.CouponDTO;
import me.sina.payment.model.discount.Coupon;
import me.sina.payment.repository.discount.CouponRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final CouponRepository couponRepository;
    private final ModelMapper mapper;

    @Autowired
    public DiscountServiceImpl(CouponRepository couponRepository, ModelMapper mapper) {
        this.couponRepository = couponRepository;
        this.mapper = mapper;
    }

    @Override
    public CouponDTO loadCouponByCode(String code) {
        Optional<Coupon> coupon = couponRepository.findByCode(code);
        return coupon.map(value -> mapper.map(value, CouponDTO.class)).orElse(null);
    }

    @Override
    public CouponDTO createCoupon(CouponDTO couponDTO) {
        Coupon coupon = mapper.map(couponDTO, Coupon.class);
        Coupon savedCoupon = couponRepository.save(coupon);
        return mapper.map(savedCoupon, CouponDTO.class);
    }
}
