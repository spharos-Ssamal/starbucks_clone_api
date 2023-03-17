package com.ssamal.starbucks_clone_api.v1.coupon.entity;

import com.ssamal.starbucks_clone_api.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "coupon_info")
@Entity
public class Coupon extends BaseEntity {
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



    public void updateCoupon(String name, int price, String company){
        this.name = name;
        this.price = price;
        this.company = company;
    }
}










