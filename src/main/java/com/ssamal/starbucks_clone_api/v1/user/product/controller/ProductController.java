package com.ssamal.starbucks_clone_api.v1.user.product.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductReq.FilterParam;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductReq.GetProductsReq;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductReq.SearchProductsByHashtagReq;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductReq.SearchProductsReq;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.BestProductsRes;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.GetPrePurchaseProductsInfoRes;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.GetProductCategoryAggregationRes;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.SearchProductRes;
import com.ssamal.starbucks_clone_api.v1.user.product.service.ProductService;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.GetProductDetailRes;
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
    public ResponseEntity<BaseRes<SearchProductRes>> getProducts(
        @RequestParam(value = "category") Long categoryId,
        @RequestParam(value = "subCategories", required = false, defaultValue = "") List<Long> subCategories,
        @RequestParam(value = "seasons", required = false, defaultValue = "") List<Long> seasonIds,
        @RequestParam(value = "productSize", required = false, defaultValue = "") List<Long> sizeIds,
        @RequestParam(value = "priceStart", required = false, defaultValue = "-1") Integer priceStart,
        @RequestParam(value = "priceEnd", required = false, defaultValue = "-1") Integer priceEnd,
        @PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        SearchProductRes result = productService.getProducts(
            new GetProductsReq( new FilterParam(categoryId, subCategories, seasonIds, sizeIds, priceStart, priceEnd)),
            pageable);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "카테고리 별 베스트 상품 조회", description = "카테고리 별 베스트 상품 조회 API 입니다.")
    @GetMapping("/best")
    public ResponseEntity<BaseRes<BestProductsRes>> getBestProductsByCategory(
        @RequestParam(value = "categoryId", defaultValue = "") Long categoryId
    ) {
        BestProductsRes result = productService.getBestProducts(categoryId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "카테고리 별 상품 갯수 집계 (상품명)", description = "상품명 기반 카테고리 별 상품 갯수 집계 API 입니다.")
    @GetMapping("/category/aggregation/name")
    public ResponseEntity<BaseRes<List<GetProductCategoryAggregationRes>>> getCategoryAggregationByProductName(
        @RequestParam(value = "productName", defaultValue = "") String productName
    ) {
        List<GetProductCategoryAggregationRes> result = productService.getProductCategoryAggregationByProductName(productName);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "카테고리 별 상품 갯수 집계 (해시태그)", description = "해시태그 기반 카테고리 별 상품 갯수 집계 API 입니다.")
    @GetMapping("/category/aggregation/hashtag")
    public ResponseEntity<BaseRes<List<GetProductCategoryAggregationRes>>> getCategoryAggregationByHashtag(
        @RequestParam(value = "hashtag", defaultValue = "") String hashtagName
    ) {
        List<GetProductCategoryAggregationRes> result = productService.getProductCategoryAggregationByHashtag(hashtagName);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "구매 예정 상품 정보 조회 API", description = "구매 전, 구매 예정 상품 정보 조회 API 입니다.")
    @GetMapping("/prePurchase")
    public ResponseEntity<BaseRes<List<GetPrePurchaseProductsInfoRes>>> getPrePurchaseProductsInfo(
        @RequestParam(value = "productIds", defaultValue = "") List<Long> productIds
    ) {
        List<GetPrePurchaseProductsInfoRes> result = productService.getPrePurchaseProductsInfo(productIds);
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
        @RequestParam(value = "priceStart", required = false, defaultValue = "-1") Integer priceStart,
        @RequestParam(value = "priceEnd", required = false, defaultValue = "-1") Integer priceEnd,
        @PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        SearchProductRes result = productService.searchProducts(
            new SearchProductsReq(productName, new FilterParam(categoryId, subCategories, seasonIds, sizeIds,
                priceStart, priceEnd)),
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
        @RequestParam(value = "priceStart", required = false, defaultValue = "-1") Integer priceStart,
        @RequestParam(value = "priceEnd", required = false, defaultValue = "-1") Integer priceEnd,
        @PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        SearchProductRes result = productService.searchProductsByHashtag(
            new SearchProductsByHashtagReq(hashtagName, new FilterParam(categoryId, subCategories, seasonIds,
                sizeIds, priceStart, priceEnd)), pageable
        );
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "상품 단건 조회", description = "상품 단건 조회 API 입니다.")
    @GetMapping("/read")
    public ResponseEntity<BaseRes<GetProductDetailRes>> getProductById(
        @RequestParam(name = "productId", defaultValue = "") Long productId) {
        GetProductDetailRes result = productService.getProduct(productId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
