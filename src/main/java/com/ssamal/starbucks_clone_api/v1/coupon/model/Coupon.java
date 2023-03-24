package com.ssamal.starbucks_clone_api.v1.coupon.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.CouponReq.CreateCouponReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.CouponReq.UpdateCouponReq;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "coupon_info")
@Entity
public class Coupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "coupon_name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "company")
    private String company;

    public static Coupon of (CreateCouponReq req) {
        return Coupon.builder()
            .name(req.getCouponName())
            .price(req.getPrice())
            .company(req.getCompany())
            .build();
    }

    public void updateCoupon(UpdateCouponReq req) {
        if(req.getCouponName() != null){
            this.name = req.getCouponName();
        }

        if(req.getPrice() != null) {
            this.price = req.getPrice();
        }

        if(req.getCompany() != null) {
            this.company = req.getCompany();
        }

    }

    public void deleteCoupon() {
        this.setDeleted(true);
    }

}










