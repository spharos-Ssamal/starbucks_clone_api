package com.ssamal.starbucks_clone_api.v1.admin.category.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminReq.AddProductTo;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddProductOptionRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.service.HashtagAdminService;
import com.ssamal.starbucks_clone_api.v1.category.model.HashTag;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.category.model.mapping.ProductHashTag;
import com.ssamal.starbucks_clone_api.v1.category.model.mapping.repository.ProductHashTagRepository;
import com.ssamal.starbucks_clone_api.v1.category.model.repository.HashTagRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HashtagAdminServiceImpl implements HashtagAdminService {

    private final ProductRepository productRepository;
    private final HashTagRepository hashTagRepository;
    private final ProductHashTagRepository productHashTagRepository;

    @Override
    public List<AddOptionRes> addHashTag(List<AddOption> req) {
        List<AddOptionRes> response = new ArrayList<>();

        req.forEach(request -> {

            if (hashTagRepository.existsByName(request.getName())) {
                throw new CustomException(ResCode.DUPLICATED_HASHTAG_NAME);
            } else {
                HashTag newHashTag = HashTag.builder()
                    .name(request.getName())
                    .build();
                hashTagRepository.save(newHashTag);
                response.add(new AddOptionRes(newHashTag.getId()));
            }
        });

        return response;
    }

    @Override
    public AddProductOptionRes addProductToHashTag(AddProductTo req) {
        Product product = productRepository.findById(req.getProductId())
            .orElseThrow(() -> new CustomException(ResCode.PRODUCT_NOT_FOUND));
        HashTag hashTag = hashTagRepository.findById(req.getMenuId())
            .orElseThrow(() -> new CustomException(ResCode.HASH_TAG_NOT_FOUND));
        ProductHashTag productHashTag = ProductHashTag.builder()
            .product(product)
            .hashTag(hashTag)
            .build();
        productHashTagRepository.save(productHashTag);
        return new AddProductOptionRes(product.getId(), hashTag.getId());
    }
}
