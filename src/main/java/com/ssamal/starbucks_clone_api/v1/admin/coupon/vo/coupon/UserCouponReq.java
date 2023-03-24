package com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon;

import com.ssamal.starbucks_clone_api.v1.coupon.enums.CouponStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;


public class UserCouponReq {

    private UserCouponReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class InsertUserCouponReq {

        private UUID userID;
        private Long couponID;
        private CouponStatus status;
        private LocalDate expirationDate;
    }
}
