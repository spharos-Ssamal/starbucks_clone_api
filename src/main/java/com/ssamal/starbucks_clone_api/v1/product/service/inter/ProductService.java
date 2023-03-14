package com.ssamal.starbucks_clone_api.v1.product.service.inter;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    ProductRes.GetProductRes getProduct(Long productId);

    ProductRes.SearchProductRes getProducts(ProductReq.SearchProductsReq req, Pageable pageable);


}
