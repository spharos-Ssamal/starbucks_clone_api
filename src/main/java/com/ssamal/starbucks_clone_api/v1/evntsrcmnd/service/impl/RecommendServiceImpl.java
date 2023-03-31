package com.ssamal.starbucks_clone_api.v1.evntsrcmnd.service.impl;

import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.Recommend;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.mapping.ProductRecommend;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.mapping.repository.ProductRecommendRepository;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.repository.RecommendRepository;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.service.RecommendService;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes.RecommendProductRes;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.dto.vo.RecommendRes.ActivatedRecommendsRes;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.enums.EventStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService {

    private final RecommendRepository recommendRepository;
    private final ProductRecommendRepository productRecommendRepository;

    @Override
    public List<ActivatedRecommendsRes> getActivatedRecommends() {
        List<Recommend> result = recommendRepository.findAllByStatus(EventStatus.ACTIVE);
        return result.stream().map(ActivatedRecommendsRes::of).toList();
    }

    @Override
    public List<RecommendProductRes> getProductsByRecommend(Long recommendId) {
        List<ProductRecommend> result = productRecommendRepository.findAllByRecommendId(
            recommendId);
        return result.stream().map(RecommendProductRes::of).toList();
    }
}
