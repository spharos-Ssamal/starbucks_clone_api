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
import com.ssamal.starbucks_clone_api.v1.user.product.dto.vo.ProductRes.BestProductsRes;
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
import org.springframework.cache.annotation.Cacheable;
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

        if (req.getFilterParam().getSubCategories().isEmpty()) {
            spec = spec.and(
                ProductOptionSpecification.inCategoryId(
                    List.of(req.getFilterParam().getMainCategory())));
        } else {
            spec = spec.and(
                ProductOptionSpecification.inCategoryId(req.getFilterParam().getSubCategories()));
        }

        if (!(req.getFilterParam().getSizeIds() != null && req.getFilterParam().getSizeIds()
            .isEmpty())) {
            spec = spec.and(ProductOptionSpecification.inSizeId(req.getFilterParam().getSizeIds()));
        }

        if (req.getFilterParam().getSeasonIds() != null && !req.getFilterParam().getSeasonIds()
            .isEmpty()) {
            spec = spec.and(
                ProductOptionSpecification.inSeasonId(req.getFilterParam().getSeasonIds()));
        }

        if (req.getFilterParam().getPriceStart() != -1
            && req.getFilterParam().getPriceEnd() != -1) {
            if (req.getFilterParam().getPriceEnd() != 0) {
                spec = spec.and(
                    ProductOptionSpecification.betweenPrice(req.getFilterParam().getPriceStart(),
                        req.getFilterParam().getPriceEnd()));
            } else {
                spec = spec.and(ProductOptionSpecification.greaterThanPrice(
                    req.getFilterParam().getPriceStart()));
            }
        }

        Page<ProductDTO> result = ProductDTO.of(productOptionsRepository.findAll(spec, pageable));

        return new SearchProductRes(result.getContent(), result.getNumber(), result.isLast(),
            result.getTotalPages(), result.getTotalElements());
    }

    @Override
    public BestProductsRes getBestProducts(Long categoryId) {
        return new BestProductsRes(productOptionsRepository.findByCategoryId(categoryId).stream().map(ProductOptions::getProduct)
            .filter(Product::isBest).map(ProductDTO::of).toList());
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

        if (req.getFilterParam().getSubCategories().isEmpty()) {
            spec = spec.and(
                ProductOptionSpecification.inCategoryId(
                    List.of(req.getFilterParam().getMainCategory())));
        } else {
            spec = spec.and(
                ProductOptionSpecification.inCategoryId(req.getFilterParam().getSubCategories()));
        }

        if (!req.getProductName().isEmpty()) {
            spec = spec.and(ProductOptionSpecification.likeProductName(req.getProductName()));
        }

        if (!req.getFilterParam().getSizeIds().isEmpty()) {
            spec = spec.and(ProductOptionSpecification.inSizeId(req.getFilterParam().getSizeIds()));
        }

        if (!req.getFilterParam().getSeasonIds().isEmpty()) {
            spec = spec.and(
                ProductOptionSpecification.inSeasonId(req.getFilterParam().getSeasonIds()));
        }

        if (req.getFilterParam().getPriceStart() != -1
            && req.getFilterParam().getPriceEnd() != -1) {
            if (req.getFilterParam().getPriceEnd() != 0) {
                spec = spec.and(
                    ProductOptionSpecification.betweenPrice(req.getFilterParam().getPriceStart(),
                        req.getFilterParam().getPriceEnd()));
            } else {
                spec = spec.and(ProductOptionSpecification.greaterThanPrice(
                    req.getFilterParam().getPriceStart()));
            }
        }

        Page<ProductDTO> result = ProductDTO.of(productOptionsRepository.findAll(spec, pageable));

        return new SearchProductRes(result.getContent(), result.getNumber(), result.isLast(),
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

        if (req.getFilterParam().getSubCategories().isEmpty()) {
            spec = spec.and(
                ProductOptionSpecification.inCategoryId(
                    List.of(req.getFilterParam().getMainCategory())));
        } else {
            spec = spec.and(
                ProductOptionSpecification.inCategoryId(req.getFilterParam().getSubCategories()));
        }

        if (req.getFilterParam().getSizeIds() != null && !req.getFilterParam().getSizeIds()
            .isEmpty()) {
            spec = spec.and(ProductOptionSpecification.inSizeId(req.getFilterParam().getSizeIds()));
        }

        if (req.getFilterParam().getSeasonIds() != null && !req.getFilterParam().getSeasonIds()
            .isEmpty()) {
            spec = spec.and(
                ProductOptionSpecification.inSeasonId(req.getFilterParam().getSeasonIds()));
        }

        if (req.getFilterParam().getPriceStart() != -1
            && req.getFilterParam().getPriceEnd() != -1) {
            if (req.getFilterParam().getPriceEnd() != 0) {
                spec = spec.and(
                    ProductOptionSpecification.betweenPrice(req.getFilterParam().getPriceStart(),
                        req.getFilterParam().getPriceEnd()));
            } else {
                spec = spec.and(ProductOptionSpecification.greaterThanPrice(
                    req.getFilterParam().getPriceStart()));
            }
        }

        Page<ProductDTO> result = ProductDTO.of(productOptionsRepository.findAll(spec, pageable));

        return new SearchProductRes(result.getContent(), result.getNumber(), result.isLast(),
            result.getTotalPages(), result.getTotalElements());
    }

}
