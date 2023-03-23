package com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.coupon;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponReq {

    private Long id;

    private String couponName;

    private int price;

    private String company;

}
