package com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.repository;

import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsByName(String name);

    List<Event> findAllByStatus(EventStatus status);

    List<Event> findAllByBannerViewable(Boolean bannerViewable);
}
