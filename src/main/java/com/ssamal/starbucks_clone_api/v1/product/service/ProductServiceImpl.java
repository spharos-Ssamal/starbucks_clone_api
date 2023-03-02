package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Override
    public ProductRes.SearchProductsRes searchProductFromMenu(ProductReq.SearchProductsReq req) {
        return null;
    }
}
