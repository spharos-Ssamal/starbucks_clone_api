package com.ssamal.starbucks_clone_api.v1.coupon.repository;

import com.ssamal.starbucks_clone_api.v1.coupon.dto.UserCouponDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserCouponRepository extends JpaRepository<UserCoupon,Long> {
    List<UserCoupon> findByServiceUser(UUID serviceUser);
}
