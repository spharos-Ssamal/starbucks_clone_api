package com.ssamal.starbucks_clone_api.v1.product.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq.AddProductTo;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddProductOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.model.HashTag;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.product.model.mapping.ProductHashTag;
import com.ssamal.starbucks_clone_api.v1.product.model.mapping.repository.ProductHashTagRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.HashTagRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductRepository;
import com.ssamal.starbucks_clone_api.v1.product.service.ProductHashtagService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductHashtagServiceImpl implements ProductHashtagService {

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
