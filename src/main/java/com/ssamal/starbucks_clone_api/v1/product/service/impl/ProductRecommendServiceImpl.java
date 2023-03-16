package com.ssamal.starbucks_clone_api.v1.product.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddProductOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.product.model.mapping.ProductRecommend;
import com.ssamal.starbucks_clone_api.v1.product.model.Recommend;
import com.ssamal.starbucks_clone_api.v1.product.model.mapping.repository.ProductRecommendRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.RecommendRepository;
import com.ssamal.starbucks_clone_api.v1.product.service.ProductRecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRecommendServiceImpl implements ProductRecommendService {

    private final ProductRepository productRepository;
    private final RecommendRepository recommendRepository;
    private final ProductRecommendRepository productRecommendRepository;

    @Override
    public List<AddOptionRes> addRecommend(List<AddOption> req) {
        List<AddOptionRes> response = new ArrayList<>();

        req.forEach(request -> {

            if (recommendRepository.existsByName(request.getName())) {
                throw new CustomException(ResCode.DUPLICATED_RECOMMEND_NAME);
            } else {
                Recommend recommend = Recommend.builder()
                    .name(request.getName())
                    .status(EventStatus.ACTIVE)
                    .build();
                recommendRepository.save(recommend);
                response.add(new AddOptionRes(recommend.getId()));
            }
        });

        return response;
    }

    @Override
    public AddProductOptionRes addProductToRecommend(ProdAdminReq.AddProductTo req) {
        Product product = productRepository.findById(req.getProductId())
            .orElseThrow(() -> new CustomException(ResCode.PRODUCT_NOT_FOUND));
        Recommend recommend = recommendRepository.findById(req.getMenuId())
            .orElseThrow(() -> new CustomException(ResCode.EVENT_NOT_FOUND));
        ProductRecommend productRecommend = ProductRecommend.builder()
            .product(product)
            .recommend(recommend)
            .build();
        productRecommendRepository.save(productRecommend);
        return new AddProductOptionRes(product.getId(), recommend.getId());
    }
}
