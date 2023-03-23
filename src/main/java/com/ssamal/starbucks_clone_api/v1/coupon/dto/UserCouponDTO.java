package com.ssamal.starbucks_clone_api.v1.coupon.dto;

import com.ssamal.starbucks_clone_api.v1.coupon.entity.Coupon;
import com.ssamal.starbucks_clone_api.v1.user.model.ServiceUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCouponDTO {
    private Long id;
    private String status;
    private LocalDate userDate;
    private LocalDate expirationDate;
    private ServiceUser serviceUser;
    private Coupon coupon;
}
