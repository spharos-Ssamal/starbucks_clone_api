package com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.mapping.repository;

import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.mapping.ProductRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRecommendRepository extends JpaRepository<ProductRecommend, Long> {

    Long deleteAllByProductId(Long productId);

    List<ProductRecommend> findAllByRecommendId(Long recommendId);

    List<ProductRecommend> findAllByRecommendStatus(EventStatus status);
}
