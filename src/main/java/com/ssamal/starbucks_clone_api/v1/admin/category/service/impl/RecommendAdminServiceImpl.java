package com.ssamal.starbucks_clone_api.v1.admin.category.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminReq.AddProductTo;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddProductOptionRes;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.dto.vo.RecommendReq.AddRecommendReq;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.user.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.mapping.ProductRecommend;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.Recommend;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.mapping.repository.ProductRecommendRepository;
import com.ssamal.starbucks_clone_api.v1.user.product.model.repository.ProductRepository;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.repository.RecommendRepository;
import com.ssamal.starbucks_clone_api.v1.admin.category.service.RecommendAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendAdminServiceImpl implements RecommendAdminService {

    private final ProductRepository productRepository;
    private final RecommendRepository recommendRepository;
    private final ProductRecommendRepository productRecommendRepository;

    @Override
    public List<AddOptionRes> addRecommend(List<AddRecommendReq> req) {
        List<AddOptionRes> response = new ArrayList<>();

        req.forEach(request -> {

            if (recommendRepository.existsByName(request.getName())) {
                throw new CustomException(ResCode.DUPLICATED_RECOMMEND_NAME);
            } else {
                Recommend recommend = Recommend.builder()
                    .name(request.getName())
                    .detailImage(request.getDetailImage())
                    .bannerImage(request.getBannerImage())
                    .status(EventStatus.ACTIVE)
                    .build();
                recommendRepository.save(recommend);
                response.add(new AddOptionRes(recommend.getId()));
            }
        });

        return response;
    }

    @Override
    public AddProductOptionRes addProductToRecommend(AddProductTo req) {
        Product product = productRepository.findById(req.getProductId())
            .orElseThrow(() -> new CustomException(ResCode.PRODUCT_NOT_FOUND));
        Recommend recommend = recommendRepository.findById(req.getMenuId())
            .orElseThrow(() -> new CustomException(ResCode.RECOMMEND_NOT_FOUND));
        ProductRecommend productRecommend = ProductRecommend.builder()
            .product(product)
            .recommend(recommend)
            .build();
        productRecommendRepository.save(productRecommend);
        return new AddProductOptionRes(product.getId(), recommend.getId());
    }

    @Override
    public Long chgViewable(Long recommendId) {
        Recommend recommend = recommendRepository.findById(recommendId)
            .orElseThrow(() -> new CustomException(ResCode.RECOMMEND_NOT_FOUND));
        recommend.chgViewable();
        recommendRepository.save(recommend);

        return recommend.getId();
    }
}
