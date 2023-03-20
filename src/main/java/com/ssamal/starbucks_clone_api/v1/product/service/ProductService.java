package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductReq.GetProductsReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductReq.SearchProductsByHashtagReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductReq.SearchProductsReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    ProductRes.GetProductRes getProduct(Long productId);

    ProductRes.SearchProductRes getProducts(GetProductsReq req, Pageable pageable);

    ProductRes.SearchProductRes searchProducts(SearchProductsReq req, Pageable pageable);

    ProductRes.SearchProductRes searchProductsByHashtag(SearchProductsByHashtagReq req,
        Pageable pageable);

}
