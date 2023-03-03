package com.ssamal.starbucks_clone_api.v1.product.service.inter;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductInfo;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes;

import java.util.List;

public interface ProductService {
    ProductRes.SearchProductsRes searchProductFromMenu(ProductReq.SearchProductsReq req);
    ProductRes.GetProductRes getProduct(Long productId);
    List<ProductInfo> getProductsByEvent(Long eventId);

}
