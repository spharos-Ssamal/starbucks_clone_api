package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes.RecommendProductRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.RecommendRes.ActivatedRecommendsRes;
import java.util.List;

public interface RecommendService {
    List<ActivatedRecommendsRes> getActivatedRecommends();
    List<RecommendProductRes> getProductsByRecommend(Long recommendId);
}
