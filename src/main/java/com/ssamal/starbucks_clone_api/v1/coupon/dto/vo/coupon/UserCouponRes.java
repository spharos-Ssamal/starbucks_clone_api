package com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.coupon;

import com.ssamal.starbucks_clone_api.v1.coupon.entity.Coupon;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserCouponRes {
    private Coupon coupon;
    private String status;
}
