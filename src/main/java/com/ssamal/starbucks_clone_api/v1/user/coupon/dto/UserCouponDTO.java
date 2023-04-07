package com.ssamal.starbucks_clone_api.v1.user.coupon.dto;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.user.coupon.model.UserCoupon;
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
    private LocalDate useDate;
    private LocalDate expirationDate;

    public static UserCouponDTO of(UserCoupon coupon) {
        return ModelMapperUtils.getModelMapper().map(coupon, UserCouponDTO.class);
    }
}
