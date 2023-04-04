package com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.service;

import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.dto.vo.RecommendRes.ActivatedRecommendsRes;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.RecommendProductRes;
import java.util.List;

public interface RecommendService {
    List<ActivatedRecommendsRes> getActivatedRecommends();
    List<RecommendProductRes> getProductsByRecommend(Long recommendId);
}
