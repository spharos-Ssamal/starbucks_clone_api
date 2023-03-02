package com.ssamal.starbucks_clone_api.v1.product.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<BaseRes<ProductRes.SearchProductsRes>> searchProducts(
            @RequestParam(name = "categoryId", defaultValue = "") Long categoryId,
            @RequestParam(name = "seasonId", defaultValue = "") Long seasonId,
            @RequestParam(name = "price", defaultValue = "") Integer price) {
         ProductRes.SearchProductsRes result = productService.searchProductFromMenu(new ProductReq.SearchProductsReq(categoryId, seasonId, price));
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}