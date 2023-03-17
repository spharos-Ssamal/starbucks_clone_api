package com.ssamal.starbucks_clone_api.v1.coupon.repository;

import com.ssamal.starbucks_clone_api.v1.coupon.entity.Coupon;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Optional<Coupon> findByCouponIdAndIsDeleted(Long couponId, boolean isDeleted);

}
