package com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.mapping.repository;

import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.mapping.ProductEvent;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.enums.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEventRepository extends JpaRepository<ProductEvent, Long> {

    Long deleteAllByProductId(Long productId);

    List<ProductEvent> findAllByEventId(Long eventId);

    List<ProductEvent> findAllByEventStatus(EventStatus status);
}
