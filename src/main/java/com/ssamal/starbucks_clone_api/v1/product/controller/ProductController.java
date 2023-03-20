package com.ssamal.starbucks_clone_api.v1.product.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductReq.GetProductsReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductReq.SearchProductsByHashtagReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductReq.SearchProductsReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes.SearchProductRes;
import com.ssamal.starbucks_clone_api.v1.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "상품", description = "상품 조회 및 검색 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "전체 상품 조회", description = "전체 상품 조회 API 입니다.")
    @GetMapping("/get")
    public ResponseEntity<BaseRes<ProductRes.SearchProductRes>> getProducts(
        @RequestParam(value = "category") Long categoryId,
        @RequestParam(value = "subCategories", required = false, defaultValue = "") List<Long> subCategories,
        @RequestParam(value = "seasons", required = false, defaultValue = "") List<Long> seasonIds,
        @RequestParam(value = "productSize", required = false, defaultValue = "") List<Long> sizeIds,
        @RequestParam(value = "price", required = false, defaultValue = "") Integer price,
        @PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        ProductRes.SearchProductRes result = productService.getProducts(
            new GetProductsReq(categoryId, subCategories, seasonIds, sizeIds, price),
            pageable);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "상품 명 기반 검색 API", description = "검색어 기반 상품 조회 API 입니다.")
    @GetMapping("/search")
    public ResponseEntity<BaseRes<SearchProductRes>> searchProducts(
        @RequestParam(value = "productName", defaultValue = "") String productName,
        @RequestParam(value = "category") Long categoryId,
        @RequestParam(value = "subCategories", required = false, defaultValue = "") List<Long> subCategories,
        @RequestParam(value = "seasons", required = false, defaultValue = "") List<Long> seasonIds,
        @RequestParam(value = "productSize", required = false, defaultValue = "") List<Long> sizeIds,
        @RequestParam(value = "price", required = false, defaultValue = "") Integer price,
        @PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        SearchProductRes result = productService.searchProducts(
            new SearchProductsReq(productName, categoryId, subCategories, seasonIds, sizeIds,
                price),
            pageable);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "해시태그 기반 검색 API", description = "해시태그 기반 상품 조회 API 입니다.")
    @GetMapping("/search/hashtag")
    public ResponseEntity<BaseRes<SearchProductRes>> searchProductsByHashtag(
        @RequestParam(value = "hashtagName", defaultValue = "") String hashtagName,
        @RequestParam(value = "category") Long categoryId,
        @RequestParam(value = "subCategories", required = false, defaultValue = "") List<Long> subCategories,
        @RequestParam(value = "seasons", required = false, defaultValue = "") List<Long> seasonIds,
        @RequestParam(value = "productSize", required = false, defaultValue = "") List<Long> sizeIds,
        @RequestParam(value = "price", required = false, defaultValue = "") Integer price,
        @PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        SearchProductRes result = productService.searchProductsByHashtag(
            new SearchProductsByHashtagReq(hashtagName, categoryId, subCategories, seasonIds,
                sizeIds, price), pageable
        );
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "상품 단건 조회", description = "상품 단건 조회 API 입니다.")
    @GetMapping("/read")
    public ResponseEntity<BaseRes<ProductRes.GetProductDetailRes>> getProductById(
        @RequestParam(name = "productId", defaultValue = "") Long productId) {
        ProductRes.GetProductDetailRes result = productService.getProduct(productId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
