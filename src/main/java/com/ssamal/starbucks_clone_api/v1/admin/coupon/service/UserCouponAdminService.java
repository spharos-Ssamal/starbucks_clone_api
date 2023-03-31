package com.ssamal.starbucks_clone_api.v1.admin.coupon.service;

import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.UserCouponReq.InsertUserCouponReq;

import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.UserCouponRes.UserCouponList;
import com.ssamal.starbucks_clone_api.v1.service.coupon.enums.CouponStatus;
import java.util.UUID;

public interface UserCouponAdminService {

    Long insertUserCoupon(InsertUserCouponReq req);

    UserCouponList userCouponList(UUID userID);

    Long updateUserCoupon(Long id, CouponStatus status);

    Long deleteUserCoupon(Long userCouponId);

}
