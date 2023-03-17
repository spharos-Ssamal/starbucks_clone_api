package com.ssamal.starbucks_clone_api.v1.coupon.repository;

import com.ssamal.starbucks_clone_api.v1.coupon.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon,Long> {

}
