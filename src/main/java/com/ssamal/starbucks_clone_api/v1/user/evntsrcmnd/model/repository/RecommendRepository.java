package com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.repository;

import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    boolean existsByName(String name);
    List<Recommend> findAllByStatus(EventStatus status);

    List<Recommend> findAllByBannerViewable(Boolean bannerViewable);
}
