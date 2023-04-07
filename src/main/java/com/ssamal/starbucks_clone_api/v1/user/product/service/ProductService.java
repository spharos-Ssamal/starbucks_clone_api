package com.ssamal.starbucks_clone_api.v1.user.product.service;

import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductReq.GetProductsReq;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductReq.SearchProductsByHashtagReq;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductReq.SearchProductsReq;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.BestProductsRes;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.GetPrePurchaseProductsInfoRes;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.GetProductCategoryAggregationRes;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.GetProductDetailRes;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.SearchProductRes;
import java.util.List;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    GetProductDetailRes getProduct(Long productId);

    SearchProductRes getProducts(GetProductsReq req, Pageable pageable);

    BestProductsRes getBestProducts(Long categoryId);

    List<GetProductCategoryAggregationRes> getProductCategoryAggregationByProductName(
        String productName);

    List<GetProductCategoryAggregationRes> getProductCategoryAggregationByHashtag(String hashtag);

    List<GetPrePurchaseProductsInfoRes> getPrePurchaseProductsInfo(List<Long> productId);

    SearchProductRes searchProducts(SearchProductsReq req, Pageable pageable);

    SearchProductRes searchProductsByHashtag(SearchProductsByHashtagReq req,
        Pageable pageable);

}
