package com.ssamal.starbucks_clone_api.v1.coupon.repository;

import com.ssamal.starbucks_clone_api.v1.coupon.entity.EGiftCard;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EGiftCardRepository extends JpaRepository<EGiftCard, Long> {

    List<EGiftCard> findByServiceUser(UUID serviceUser);

}
