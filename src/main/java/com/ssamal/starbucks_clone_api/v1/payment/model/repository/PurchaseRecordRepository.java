package com.ssamal.starbucks_clone_api.v1.payment.model.repository;

import com.ssamal.starbucks_clone_api.v1.payment.model.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRecordRepository extends JpaRepository<PurchaseHistory, Long> {
}
