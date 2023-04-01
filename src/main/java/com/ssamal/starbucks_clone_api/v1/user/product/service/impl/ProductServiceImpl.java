package com.ssamal.starbucks_clone_api.v1.user.product.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.user.category.model.Category;
import com.ssamal.starbucks_clone_api.v1.user.options.model.HashTag;
import com.ssamal.starbucks_clone_api.v1.user.options.model.mapping.repository.ProductHashTagRepository;
import com.ssamal.starbucks_clone_api.v1.user.category.model.repository.CategoryRepository;
import com.ssamal.starbucks_clone_api.v1.user.options.model.repository.HashTagRepository;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.ProductDTO;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.ProductDetailImageDTO;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductReq.GetProductsReq;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductReq.SearchProductsByHashtagReq;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductReq.SearchProductsReq;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.GetPrePurchaseProductsInfoRes;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.GetProductCategoryAggregationRes;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.SearchProductRes;
import com.ssamal.starbucks_clone_api.v1.user.options.model.mapping.ProductOptions;
import com.ssamal.starbucks_clone_api.v1.user.options.model.mapping.repository.ProductOptionsRepository;
import com.ssamal.starbucks_clone_api.v1.user.options.model.mapping.repository.specification.ProductOptionSpecification;
import com.ssamal.starbucks_clone_api.v1.user.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.user.product.service.ProductService;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.GetProductDetailRes;
import com.ssamal.starbucks_clone_api.v1.user.product.model.repository.ProductDetailImageRepository;
import com.ssamal.starbucks_clone_api.v1.user.product.model.repository.ProductRepository;
import java.util.ArrayList;
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
    private final CategoryRepository categoryRepository;
    private final ProductOptionsRepository productOptionsRepository;
    private final HashTagRepository hashTagRepository;
    private final ProductHashTagRepository productHashTagRepository;
    private final ProductDetailImageRepository productDetailImageRepository;

    @Override
    public GetProductDetailRes getProduct(Long productId) {

        Product result = productRepository.findById(productId)
            .orElseThrow(() -> new CustomException(ResCode.PRODUCT_NOT_FOUND));

        List<ProductDetailImageDTO> imageResults = productDetailImageRepository.findAllByProductIdOrderByIdAsc(
            productId).stream().map(ProductDetailImageDTO::of).toList();

        return new GetProductDetailRes(ProductDTO.of(result), imageResults);
    }

    @Override
    public SearchProductRes getProducts(GetProductsReq req,
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

        return new SearchProductRes(result.getContent(), result.isLast(),
            result.getTotalPages(), result.getTotalElements());
    }

    @Override
    public List<GetProductCategoryAggregationRes> getProductCategoryAggregationByProductName(
        String productName) {

        List<Long> productIdList = productRepository.findAllByNameContaining(productName)
            .stream().map(Product::getId).toList();

        if (productIdList.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<Long> categoryList = categoryRepository.findAllByParentId(1L)
                .stream().map(Category::getId).toList();

            return productOptionsRepository.categoryAggregate(productIdList,
                    categoryList)
                .stream().map(GetProductCategoryAggregationRes::of).toList();
        }
    }

    @Override
    public List<GetProductCategoryAggregationRes> getProductCategoryAggregationByHashtag(
        String hashtag) {

        List<Long> hashTagIdList = hashTagRepository.findAllByNameStartingWith(hashtag)
            .stream().map(HashTag::getId).toList();

        if (hashTagIdList.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<Long> categoryList = categoryRepository.findAllByParentId(1L)
                .stream().map(Category::getId).toList();

            List<Long> productIdList = productHashTagRepository.findAllByHashTagIdIn(hashTagIdList)
                .stream().map(t -> t.getProduct().getId()).toList();

            return productOptionsRepository.categoryAggregate(productIdList,
                    categoryList)
                .stream().map(GetProductCategoryAggregationRes::of).toList();
        }
    }

    @Override
    public List<GetPrePurchaseProductsInfoRes> getPrePurchaseProductsInfo(List<Long> productId) {
        return productRepository.findAllByIdIn(productId).stream()
            .map(GetPrePurchaseProductsInfoRes::of).toList();
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

        return new SearchProductRes(result.getContent(), result.isLast(),
            result.getTotalPages(), result.getTotalElements());
    }

    @Override
    public SearchProductRes searchProductsByHashtag(SearchProductsByHashtagReq req,
        Pageable pageable) {

        List<Long> hashTagIdList = hashTagRepository.findAllByNameStartingWith(req.getHashtagName())
            .stream().map(HashTag::getId).toList();

        List<Long> productIdList = productHashTagRepository.findAllByHashTagIdIn(hashTagIdList)
            .stream().map(t -> t.getProduct().getId()).toList();

        Specification<ProductOptions> spec = (root, query, cb) -> cb.isTrue(cb.literal(true));

        spec = spec.and(ProductOptionSpecification.inProductId(productIdList));

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

        return new SearchProductRes(result.getContent(), result.isLast(),
            result.getTotalPages(), result.getTotalElements());
    }

}