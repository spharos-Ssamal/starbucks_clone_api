package com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon;

import com.ssamal.starbucks_clone_api.v1.service.coupon.dto.UserCouponDTO;
import java.util.List;
import lombok.*;


public class UserCouponRes {

    private UserCouponRes() {
        throw new IllegalStateException("VO Class");
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class UserCouponList {

        private List<UserCouponDTO> couponList;
    }

}
