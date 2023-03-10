package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.ProductDTO;
import com.ssamal.starbucks_clone_api.v1.product.dto.ProductDTO.Info;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes;
import com.ssamal.starbucks_clone_api.v1.product.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.product.model.*;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.*;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductEventRepository productEventRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRecommendRepository productRecommandRepository;
    private final RecommendRepository recommendRepository;

    @Override
    public ProductRes.GetProductRes getProduct(Long productId) {
        Product result = productRepository.findById(productId)
            .orElseThrow(() -> new CustomException(CustomError.PRODUCT_NOT_FOUND));
        return new ProductRes.GetProductRes(ProductDTO.Info.of(result));
    }

    @Override
    public List<Info> getProductsByEvent(Long eventId) {
        List<ProductEvent> result = productEventRepository.findAllByEventId(eventId);
        List<Product> products = result.stream().map(ProductEvent::getProduct).toList();
        return ProductDTO.Info.of(products);
    }

    /*
     * 지금은 동적쿼리로 만들기 귀찮아서 필터링 처리하는데
     * 추후 QueryDSL이든 뭐든 써서 동적쿼리로 바꿔서 쿼리 상에서 단도리 짓는 게 제일 좋은 방법
     * or 걍 프론트에서 필터링 하든지
     * */
    @Override
    public ProductRes.SearchProductRes getProducts(ProductReq.SearchProductsReq req,
        Pageable pageable) {
        List<Product> data;

        if (req.getSubCategories().isEmpty()) {
            data = productCategoryRepository.findAllByCategoryId(req.getMainCategory(),
                    pageable.getSort())
                .stream().map(ProductCategory::getProduct).toList();
        } else {
            data = productCategoryRepository.findAllByCategoryIdIn(req.getSubCategories(),
                    pageable.getSort())
                .stream().map(ProductCategory::getProduct).toList();
        }

        List<ProductDTO.Info> result = ProductDTO.Info.of(data.stream()
            .filter(element -> req.getSize().isEmpty() || req.getSize()
                .contains(element.getSize().toString()))
            .filter(element -> req.getSeasons().isEmpty() || req.getSeasons()
                .contains(element.getSeason().toString()))
            .toList());

        int start = (int) pageable.getOffset();
        int end = (Math.min(start + pageable.getPageSize(), result.size()));

        Page<ProductDTO.Info> pageResult = new PageImpl<>(result.subList(start, end), pageable,
            result.size());

        return new ProductRes.SearchProductRes(pageResult.getContent(), pageResult.isLast(),
            pageResult.getTotalPages(), pageResult.getTotalElements());
    }

    @Override
    public Map<String, List<ProductRes.RecommendProductRes>> getProductsByActiveRecommand() {
        List<ProductRecommend> result = productRecommandRepository.findAllByRecommendStatus(
            EventStatus.ACTIVE);
        return result.stream().map(ProductRes.RecommendProductRes::of).toList()
            .stream()
            .collect(Collectors.groupingBy(ProductRes.RecommendProductRes::getCategoryName));
    }
}
