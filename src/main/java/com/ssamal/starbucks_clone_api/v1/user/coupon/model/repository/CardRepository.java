package com.ssamal.starbucks_clone_api.v1.user.coupon.model.repository;

import com.ssamal.starbucks_clone_api.v1.user.coupon.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {

}
