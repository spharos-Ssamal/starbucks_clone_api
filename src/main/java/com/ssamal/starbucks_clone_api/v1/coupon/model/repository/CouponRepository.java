package com.ssamal.starbucks_clone_api.v1.coupon.model.repository;

import com.ssamal.starbucks_clone_api.v1.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
