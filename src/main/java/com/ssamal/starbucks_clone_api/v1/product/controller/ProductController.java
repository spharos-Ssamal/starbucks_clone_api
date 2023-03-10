package com.ssamal.starbucks_clone_api.v1.product.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.ProductDTO;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

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

    @GetMapping("/read")
    public ResponseEntity<BaseRes<ProductRes.GetProductRes>> getProductById(
        @RequestParam(name = "productId", defaultValue = "") Long productId) {
        ProductRes.GetProductRes result = productService.getProduct(productId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @GetMapping("/read/event")
    public ResponseEntity<BaseRes<List<ProductDTO.Info>>> getProductsByEvent(
        @RequestParam(name = "eventId", defaultValue = "") Long eventId) {
        List<ProductDTO.Info> result = productService.getProductsByEvent(eventId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @GetMapping("/recommend/isActive")
    public ResponseEntity<BaseRes<List<Long>>> getActiveRecommend() {
        List<Long> result = productService.getActiveRecommendId();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @GetMapping("/recommend")
    public ResponseEntity<BaseRes<Map<String, List<ProductRes.RecommendProductRes>>>> getProductsByRecommand() {
        Map<String, List<ProductRes.RecommendProductRes>> result = productService.getProductsByActiveRecommand();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
