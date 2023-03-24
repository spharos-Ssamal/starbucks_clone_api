package com.ssamal.starbucks_clone_api.v1.admin.coupon.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.service.CouponAdminService;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.CouponReq.CreateCouponReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.CouponReq.UpdateCouponReq;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.CouponDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.model.Coupon;
import com.ssamal.starbucks_clone_api.v1.coupon.model.repository.CouponRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponAdminServiceImpl implements CouponAdminService {

    private final CouponRepository couponRepository;

    @Override
    public Long createCoupon(CreateCouponReq req) {
        Coupon coupon = Coupon.of(req);
        couponRepository.save(coupon);
        return coupon.getId();
    }

    @Override
    public List<CouponDTO> couponList() {
        List<Coupon> couponList = couponRepository.findAll();
        return couponList.stream().map(CouponDTO::of).toList();
    }

    @Override
    public Long updateCoupon(UpdateCouponReq req) {
        Coupon coupon = couponRepository.findById(req.getCouponId())
            .orElseThrow(() -> new CustomException(ResCode.COUPON_NOT_FOUND));
        coupon.updateCoupon(req);
        couponRepository.save(coupon);
        return coupon.getId();
    }

    @Override
    public Long deleteCoupon(Long id) {
        Coupon coupon = couponRepository.findById(id)
            .orElseThrow(() -> new CustomException(ResCode.COUPON_NOT_FOUND));
        coupon.deleteCoupon();
        couponRepository.save(coupon);
        return coupon.getId();
    }
}
