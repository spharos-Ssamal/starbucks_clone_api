package com.ssamal.starbucks_clone_api.v1.coupon.service;

import com.ssamal.starbucks_clone_api.v1.coupon.dto.CouponDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.coupon.CouponReq;
import java.util.List;

public interface CouponService {
    Long createCoupon(CouponDTO couponDTO);
    List<CouponDTO> couponList();
    Long updateCoupon(CouponReq couponReq);

    String deleteCoupon(Long id);


}
