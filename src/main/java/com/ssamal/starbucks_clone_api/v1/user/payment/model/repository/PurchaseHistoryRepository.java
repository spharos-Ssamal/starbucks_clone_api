package com.ssamal.starbucks_clone_api.v1.user.payment.model.repository;

import com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.user.payment.model.PurchaseHistory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, String> {
    List<PurchaseHistory> findAllByUserIdAndRegTimeBetweenOrderByRegTimeDesc(UUID userId, LocalDateTime start, LocalDateTime end);

    Integer countByHistoryId(String purchaseHistoryID);

    @Query("select ph.historyId, count(ph.historyId) AS cnt from PurchaseHistory ph group by ph.historyId order by count(ph.historyId) DESC limit 5")
    List<ProdAdminReq.BestProduct> bestProductHistoryList();
}
