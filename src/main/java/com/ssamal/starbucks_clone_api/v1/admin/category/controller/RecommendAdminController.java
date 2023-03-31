package com.ssamal.starbucks_clone_api.v1.admin.category.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminReq.AddProductTo;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddProductOptionRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.service.RecommendAdminService;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.dto.vo.RecommendReq.AddRecommendReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "[어드민] 추천 상품 관리", description = "추천 상품 관리 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/recommend")
public class RecommendAdminController {

    private final RecommendAdminService adminService;

    @Operation(summary = "추천 카테고리 등록", description = "추천 카테고리 등록 API 입니다.")
    @PostMapping("/new")
    public ResponseEntity<BaseRes<List<AddOptionRes>>> addRecommend(
        @RequestBody List<AddRecommendReq> req) {
        List<AddOptionRes> result = adminService.addRecommend(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "추천 상품 등록", description = "추천 상품 등록 API 입니다.")
    @PostMapping("/addProduct")
    public ResponseEntity<BaseRes<AddProductOptionRes>> addProductToRecommend(
        @RequestBody AddProductTo req) {
        AddProductOptionRes result = adminService.addProductToRecommend(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "배너 출력 여부", description = "배너 출력 여부 변경 API 입니다.")
    @GetMapping("/viewable")
    public ResponseEntity<BaseRes<Long>> chgViewable(
        @RequestParam(name = "recommendId", defaultValue = "") Long recommendId
    ) {
        Long result = adminService.chgViewable(recommendId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }
}
