package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.product.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.product.model.ProductRecommend;
import com.ssamal.starbucks_clone_api.v1.product.model.Recommend;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductRecommendRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.RecommendRepository;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductRecommendService;
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
    public List<ProdAdminRes.AddMenuRes> addRecommend(List<ProdAdminReq.AddRecommend> req) {
        List<ProdAdminRes.AddMenuRes> response = new ArrayList<>();

        req.forEach(request -> {

            if (recommendRepository.existsByName(request.getName())) {
                throw new CustomException(CustomError.DUPLICATED_RECOMMEND_NAME);
            } else {
                Recommend recommend = Recommend.builder()
                    .name(request.getName())
                    .status(EventStatus.ACTIVE)
                    .build();
                recommendRepository.save(recommend);
                response.add(new ProdAdminRes.AddMenuRes(recommend.getId()));
            }
        });

        return response;
    }

    @Override
    public ProdAdminRes.AddProductToMenuRes addProductToRecommend(ProdAdminReq.AddProductTo req) {
        Product product = productRepository.findById(req.getProductId())
            .orElseThrow(() -> new CustomException(CustomError.PRODUCT_NOT_FOUND));
        Recommend recommend = recommendRepository.findById(req.getMenuId())
            .orElseThrow(() -> new CustomException(CustomError.EVENT_NOT_FOUND));
        ProductRecommend productRecommend = ProductRecommend.builder()
            .product(product)
            .recommend(recommend)
            .build();
        productRecommendRepository.save(productRecommend);
        return new ProdAdminRes.AddProductToMenuRes(product.getId(), recommend.getId());
    }
}
