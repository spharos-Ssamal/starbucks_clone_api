package com.ssamal.starbucks_clone_api.v1.admin.coupon.service;

import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.CouponReq.CreateCouponReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.CouponReq.UpdateCouponReq;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.CouponDTO;
import java.util.List;

public interface CouponAdminService {
    Long createCoupon(CreateCouponReq req);
    List<CouponDTO> couponList();
    Long updateCoupon(UpdateCouponReq req);
    Long deleteCoupon(Long id);


}
