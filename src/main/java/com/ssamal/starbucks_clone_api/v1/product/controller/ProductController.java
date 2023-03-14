package com.ssamal.starbucks_clone_api.v1.product.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "상품", description = "상품 조회 및 검색 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;


    @Operation(summary = "전체 상품 조회", description = "전체 상품 조회 API 입니다.")
    @GetMapping("/search")
    public ResponseEntity<BaseRes<ProductRes.SearchProductRes>> searchProducts(
        @RequestParam(value = "category") Long categoryId,
        @RequestParam(value = "subCategories", required = false, defaultValue = "") List<Long> subCategories,
        @RequestParam(value = "seasons", required = false, defaultValue = "") List<String> seasons,
        @RequestParam(value = "productSize", required = false, defaultValue = "") List<String> size,
        @PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        ProductRes.SearchProductRes result = productService.getProducts(
            new ProductReq.SearchProductsReq(categoryId, subCategories, seasons, size, 0),
            pageable);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "상품 단건 조회", description = "상품 단건 조회 API 입니다.")
    @GetMapping("/read")
    public ResponseEntity<BaseRes<ProductRes.GetProductRes>> getProductById(
        @RequestParam(name = "productId", defaultValue = "") Long productId) {
        ProductRes.GetProductRes result = productService.getProduct(productId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "이벤트 상품 조회", description = "이벤트 상품 조회 API 입니다.")
    @GetMapping("/read/event")
    public ResponseEntity<BaseRes<Map<String, List<ProductRes.EventProductRes>>>> getProductsByEvent() {
        Map<String, List<ProductRes.EventProductRes>> result = productService.getProductsByActiveEvents();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "추천 상품 조회", description = "추천 상품 조회 API 입니다.")
    @GetMapping("/recommend")
    public ResponseEntity<BaseRes<Map<String, List<ProductRes.RecommendProductRes>>>> getProductsByRecommend() {
        Map<String, List<ProductRes.RecommendProductRes>> result = productService.getProductsByActiveRecommend();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
