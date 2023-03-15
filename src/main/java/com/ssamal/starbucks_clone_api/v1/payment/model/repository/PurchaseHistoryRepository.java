package com.ssamal.starbucks_clone_api.v1.payment.model.repository;

import com.ssamal.starbucks_clone_api.v1.payment.model.PurchaseHistory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, String> {
    List<PurchaseHistory> findAllByUserIdAndRegTimeBetween(UUID userId, LocalDateTime start, LocalDateTime end);

}
