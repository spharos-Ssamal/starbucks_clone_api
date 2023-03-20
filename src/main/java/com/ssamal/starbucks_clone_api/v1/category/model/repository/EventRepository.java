package com.ssamal.starbucks_clone_api.v1.category.model.repository;

import com.ssamal.starbucks_clone_api.v1.category.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.category.model.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsByName(String name);

    List<Event> findAllByStatus(EventStatus status);

    List<Event> findAllByBannerViewable(Boolean bannerViewable);
}
