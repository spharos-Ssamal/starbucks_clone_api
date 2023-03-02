package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductInfo;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductRepository;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductRes.SearchProductsRes searchProductFromMenu(ProductReq.SearchProductsReq req) {
        return null;
    }

    @Override
    public ProductRes.GetProductRes getProduct(Long productId) {
        Product result = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(CustomError.PRODUCT_NOT_FOUND));
        return new ProductRes.GetProductRes(ProductInfo.fromEntity(result));
    }
}
