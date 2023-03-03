package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductInfo;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.product.model.ProductEvent;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductEventRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductRepository;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductEventRepository productEventRepository;

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

    @Override
    public List<ProductInfo> getProductsByEvent(Long eventId) {
        List<ProductEvent> result = productEventRepository.findAllByEventId(eventId);
        List<Product> products = result.stream().map(ProductEvent::getProduct).toList();
        return ProductInfo.fromEntities(products);
    }
}
