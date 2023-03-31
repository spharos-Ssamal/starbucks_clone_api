package com.ssamal.starbucks_clone_api.v1.user.coupon.model.repository;

import com.ssamal.starbucks_clone_api.v1.user.coupon.model.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserCouponRepository extends JpaRepository<UserCoupon,Long> {
    List<UserCoupon> findByServiceUserId(UUID serviceUser);
}
