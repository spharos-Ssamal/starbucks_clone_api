package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.product.model.HashTag;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.product.model.ProductHashTag;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.HashTagRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductHashTagRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductRepository;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductHashTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductHashTagServiceimpl implements ProductHashTagService {

    private final ProductRepository productRepository;
    private final HashTagRepository hashTagRepository;
    private final ProductHashTagRepository productHashTagRepository;

    @Override
    public List<ProdAdminRes.AddMenuRes> addHashTag(List<ProdAdminReq.AddHashTag> req) {
        List<ProdAdminRes.AddMenuRes> response = new ArrayList<>();

        req.forEach(request -> {

            if (hashTagRepository.existsByName(request.getName())) {
                throw new CustomException(ResCode.DUPLICATED_HASHTAG_NAME);
            } else {
                HashTag newHashTag = HashTag.builder()
                    .name(request.getName())
                    .build();
                hashTagRepository.save(newHashTag);
                response.add(new ProdAdminRes.AddMenuRes(newHashTag.getId()));
            }
        });

        return response;
    }

    @Override
    public ProdAdminRes.AddProductToMenuRes addProductToHashTag(ProdAdminReq.AddProductTo req) {

        Product product = productRepository.findById(req.getProductId())
            .orElseThrow(() -> new CustomException(ResCode.PRODUCT_NOT_FOUND));
        HashTag hashTag = hashTagRepository.findById(req.getMenuId())
            .orElseThrow(() -> new CustomException(ResCode.HASH_TAG_NOT_FOUND));
        ProductHashTag productHashTag = ProductHashTag.builder()
            .product(product)
            .hashTag(hashTag)
            .build();
        productHashTagRepository.save(productHashTag);
        return new ProdAdminRes.AddProductToMenuRes(product.getId(), hashTag.getId());
    }
}
