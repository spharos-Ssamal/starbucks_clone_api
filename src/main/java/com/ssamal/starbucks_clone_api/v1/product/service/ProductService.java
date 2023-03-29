package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductReq.GetProductsReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductReq.SearchProductsByHashtagReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductReq.SearchProductsReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes.GetProductCategoryAggregationRes;
import java.util.List;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    ProductRes.GetProductDetailRes getProduct(Long productId);

    ProductRes.SearchProductRes getProducts(GetProductsReq req, Pageable pageable);

    List<GetProductCategoryAggregationRes> getProductCategoryAggregationByProductName(
        String productName);

    List<GetProductCategoryAggregationRes> getProductCategoryAggregationByHashtag(String hashtag);

    ProductRes.SearchProductRes searchProducts(SearchProductsReq req, Pageable pageable);

    ProductRes.SearchProductRes searchProductsByHashtag(SearchProductsByHashtagReq req,
        Pageable pageable);

}
