package com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCouponReq {
    private Long id;
    private UUID userID;
    private Long couponID;
    private String status;
    private LocalDate expirationDate;

}
