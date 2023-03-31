package com.ssamal.starbucks_clone_api.v1.user.coupon.dto;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.user.coupon.model.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CouponDTO {

    private Long id;
    private String name;
    private int price;
    private String company;

    public static CouponDTO of(Coupon coupon){
        return ModelMapperUtils.getModelMapper().map(coupon,CouponDTO.class);
    }

}
