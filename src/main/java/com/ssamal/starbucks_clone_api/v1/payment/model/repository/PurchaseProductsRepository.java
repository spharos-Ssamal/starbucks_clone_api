package com.ssamal.starbucks_clone_api.v1.payment.model.repository;

import com.ssamal.starbucks_clone_api.v1.payment.model.PurchaseProducts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductsRepository extends JpaRepository<PurchaseProducts, Long> {

    List<PurchaseProducts> findAllByPurchaseHistoryId(String purchaseHistoryId);

}
