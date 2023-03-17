package com.ssamal.starbucks_clone_api.v1.product.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.ProductDTO;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductReq.GetProductsReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductReq.SearchProductsReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes.SearchProductRes;
import com.ssamal.starbucks_clone_api.v1.product.model.*;
import com.ssamal.starbucks_clone_api.v1.admin.category.model.ProductOptions;
import com.ssamal.starbucks_clone_api.v1.admin.category.model.repository.ProductOptionsRepository;
import com.ssamal.starbucks_clone_api.v1.admin.category.model.repository.specification.ProductOptionSpecification;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.*;
import com.ssamal.starbucks_clone_api.v1.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductOptionsRepository productOptionsRepository;

    @Override
    public ProductRes.GetProductRes getProduct(Long productId) {
        Product result = productRepository.findById(productId)
            .orElseThrow(() -> new CustomException(ResCode.PRODUCT_NOT_FOUND));
        return new ProductRes.GetProductRes(ProductDTO.of(result));
    }

    @Override
    public ProductRes.SearchProductRes getProducts(GetProductsReq req,
        Pageable pageable) {

        Specification<ProductOptions> spec = (root, query, cb) -> cb.isTrue(cb.literal(true));

        if (req.getSubCategories().isEmpty()) {
            spec = spec.and(
                ProductOptionSpecification.inCategoryId(List.of(req.getMainCategory())));
        } else {
            spec = spec.and(ProductOptionSpecification.inCategoryId(req.getSubCategories()));
        }

        if (!req.getSizeIds().isEmpty()) {
            spec = spec.and(ProductOptionSpecification.inSizeId(req.getSizeIds()));
        }

        if (!req.getSeasonIds().isEmpty()) {
            spec = spec.and(ProductOptionSpecification.inSeasonId(req.getSeasonIds()));
        }

        if (req.getPrice() != null) {
            spec = spec.and(ProductOptionSpecification.lessThanPrice(req.getPrice()));
        }

        Page<ProductDTO> result = ProductDTO.of(productOptionsRepository.findAll(spec, pageable));

        return new ProductRes.SearchProductRes(result.getContent(), result.isLast(),
            result.getTotalPages(), result.getTotalElements());
    }

    @Override
    public SearchProductRes searchProducts(SearchProductsReq req, Pageable pageable) {
        Specification<ProductOptions> spec = (root, query, cb) -> cb.isTrue(cb.literal(true));

        if (req.getSubCategories().isEmpty()) {
            spec = spec.and(
                ProductOptionSpecification.inCategoryId(List.of(req.getMainCategory())));
        } else {
            spec = spec.and(ProductOptionSpecification.inCategoryId(req.getSubCategories()));
        }

        if (!req.getProductName().isEmpty()) {
            spec = spec.and(ProductOptionSpecification.likeProductName(req.getProductName()));
        }

        if (!req.getSizeIds().isEmpty()) {
            spec = spec.and(ProductOptionSpecification.inSizeId(req.getSizeIds()));
        }

        if (!req.getSeasonIds().isEmpty()) {
            spec = spec.and(ProductOptionSpecification.inSeasonId(req.getSeasonIds()));
        }

        if (req.getPrice() != null) {
            spec = spec.and(ProductOptionSpecification.lessThanPrice(req.getPrice()));
        }

        Page<ProductDTO> result = ProductDTO.of(productOptionsRepository.findAll(spec, pageable));

        return new ProductRes.SearchProductRes(result.getContent(), result.isLast(),
            result.getTotalPages(), result.getTotalElements());
    }

}
