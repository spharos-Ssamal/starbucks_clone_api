package com.ssamal.starbucks_clone_api.v1.product.controller.admin;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddProductOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.service.ProductRecommendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "[어드민] 추천 상품 관리", description = "추천 상품 관리 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/recommend")
public class ProductRecommendController {

    private final ProductRecommendService adminService;

    @Operation(summary = "추천 카테고리 등록", description = "추천 카테고리 등록 API 입니다.")
    @PostMapping("/recommend/new")
    public ResponseEntity<BaseRes<List<AddOptionRes>>> addRecommend(
        @RequestBody List<ProdAdminReq.AddOption> req) {
        List<AddOptionRes> result = adminService.addRecommend(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "추천 상품 등록", description = "추천 상품 등록 API 입니다.")
    @PostMapping("/recommend/addProduct")
    public ResponseEntity<BaseRes<AddProductOptionRes>> addProductToRecommend(
        @RequestBody ProdAdminReq.AddProductTo req) {
        AddProductOptionRes result = adminService.addProductToRecommend(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }
}
