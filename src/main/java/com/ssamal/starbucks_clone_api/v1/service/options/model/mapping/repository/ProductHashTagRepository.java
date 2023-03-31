package com.ssamal.starbucks_clone_api.v1.service.options.model.mapping.repository;

import com.ssamal.starbucks_clone_api.v1.service.options.model.mapping.ProductHashTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductHashTagRepository extends JpaRepository<ProductHashTag, Long> {

    List<ProductHashTag> findAllByHashTagIdIn(List<Long> hashtagId);
    Long deleteAllByProductId(Long productId);
}
