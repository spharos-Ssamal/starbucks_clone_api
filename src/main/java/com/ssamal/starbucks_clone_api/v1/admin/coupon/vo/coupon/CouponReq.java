package com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class CouponReq {

    private CouponReq () {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateCouponReq {

        private String couponName;
        private int price;
        private String company;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateCouponReq {
        private Long couponId;
        private String couponName;
        private Integer price;
        private String company;
    }

}
