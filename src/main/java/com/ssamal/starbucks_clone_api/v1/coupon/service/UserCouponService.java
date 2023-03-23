package com.ssamal.starbucks_clone_api.v1.coupon.service;

import com.ssamal.starbucks_clone_api.v1.coupon.dto.UserCouponDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.coupon.UserCouponReq;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.coupon.UserCouponRes;

import java.util.List;
import java.util.UUID;

public interface UserCouponService {

    Long insertUserCoupon(UserCouponReq userCouponReq);

    List<UserCouponRes> UserCouponList(UUID userID);

    Long updateUserCoupon(Long id, String status);

    String deleteUserCoupon(Long userCouponId);

}
