package com.ssamal.starbucks_clone_api.v1.product.service.inter;

import com.ssamal.starbucks_clone_api.v1.product.dto.ProductDTO.ProductInfo;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {

    ProductRes.GetProductRes getProduct(Long productId);

    List<ProductInfo> getProductsByEvent(Long eventId);

    ProductRes.SearchProductRes getProducts(ProductReq.SearchProductsReq req, Pageable pageable);

    Map<String, List<ProductRes.RecommendProductRes>> getProductsByActiveRecommend();
    Map<String, List<ProductRes.EventProductRes>> getProductsByActiveEvents();

}
