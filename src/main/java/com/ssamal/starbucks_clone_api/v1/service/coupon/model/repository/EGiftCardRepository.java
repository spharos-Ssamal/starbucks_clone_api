package com.ssamal.starbucks_clone_api.v1.service.coupon.model.repository;

import com.ssamal.starbucks_clone_api.v1.service.coupon.model.EGiftCard;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EGiftCardRepository extends JpaRepository<EGiftCard, Long> {

    List<EGiftCard> findByServiceUserId(UUID serviceUser);

    Boolean existsByServiceUserIdAndIsDefault(UUID serviceUserId, Boolean isDefault);
    Optional<EGiftCard> findByServiceUserIdAndIsDefault(UUID serviceUserId, Boolean isDefault);

}
