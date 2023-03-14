package com.ssamal.starbucks_clone_api.v1.product.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes.RecommendProductRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.RecommendRes.ActivatedRecommendsRes;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.RecommendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "추천", description = "추천 항목 및 관련 상품 조회 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    @Operation(summary = "현재 활성화 된 추천 항목 조회", description = "현재 활성화 된 추천 항목 조회 API 입니다.")
    @GetMapping("/active")
    public ResponseEntity<BaseRes<List<ActivatedRecommendsRes>>> getRecommendsByStatus() {
        List<ActivatedRecommendsRes> result = recommendService.getActivatedRecommends();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "추천 항목 상품 단건 조회", description = "추천 항목 상품 단건 조회 API 입니다.")
    @GetMapping("/get")
    public ResponseEntity<BaseRes<List<RecommendProductRes>>> getProductsByRecommend(
        @RequestParam(name = "recommendId", defaultValue = "") Long recommendId) {
        List<RecommendProductRes> result = recommendService.getProductsByRecommend(recommendId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
