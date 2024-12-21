package me.sina.payment.controller.discount;

import me.sina.payment.dto.discount.CouponDTO;
import me.sina.payment.service.discount.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment/discount")
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }


    @PostMapping("/add")
    public CouponDTO createCoupon(@RequestBody CouponDTO couponDTO) {
        return discountService.createCoupon(couponDTO);
    }


    @GetMapping("/get/{code}")
    public CouponDTO findByCouponCode(@PathVariable("code") String code) {
        return discountService.loadCouponByCode(code);
    }
}
