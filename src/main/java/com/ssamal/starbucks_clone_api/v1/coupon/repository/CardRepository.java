package com.ssamal.starbucks_clone_api.v1.coupon.repository;

import com.ssamal.starbucks_clone_api.v1.coupon.entity.Card;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {

    Optional<Card> findByIdAndDeleted(Long cardId, boolean isDeleted);

}
