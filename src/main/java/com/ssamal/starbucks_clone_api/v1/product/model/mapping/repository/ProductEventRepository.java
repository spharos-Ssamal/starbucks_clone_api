package com.ssamal.starbucks_clone_api.v1.product.model.mapping.repository;

import com.ssamal.starbucks_clone_api.v1.product.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.product.model.mapping.ProductEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEventRepository extends JpaRepository<ProductEvent, Long> {

    Long deleteAllByProductId(Long productId);

    List<ProductEvent> findAllByEventId(Long eventId);

    List<ProductEvent> findAllByEventStatus(EventStatus status);
}
