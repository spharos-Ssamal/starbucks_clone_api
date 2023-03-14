package com.ssamal.starbucks_clone_api.v1.product.model.repository;

import com.ssamal.starbucks_clone_api.v1.product.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.product.model.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsByName(String name);

    List<Event> findAllByStatus(EventStatus status);
}
