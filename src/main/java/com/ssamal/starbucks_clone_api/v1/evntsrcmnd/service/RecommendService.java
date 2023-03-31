package com.ssamal.starbucks_clone_api.v1.evntsrcmnd.service;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes.RecommendProductRes;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.dto.vo.RecommendRes.ActivatedRecommendsRes;
import java.util.List;

public interface RecommendService {
    List<ActivatedRecommendsRes> getActivatedRecommends();
    List<RecommendProductRes> getProductsByRecommend(Long recommendId);
}
