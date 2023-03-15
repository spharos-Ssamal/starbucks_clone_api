package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.product.model.*;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.*;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAdminServiceImpl implements ProductAdminService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final HashTagRepository hashTagRepository;
    private final ProductHashTagRepository productHashTagRepository;
    private final ProductEventRepository productEventRepository;
    @Override
    public List<ProdAdminRes.AddProductRes> addProduct(List<ProdAdminReq.AddProductReq> req) {
        List<ProdAdminRes.AddProductRes> result = new ArrayList<>();
        req.forEach(request -> {
            Product newProduct = Product.of(request.getProductInfo());
            if (productRepository.existsByName(newProduct.getName())) {
                throw new CustomException(ResCode.DUPLICATED_PRODUCT_NAME);
            } else {
                productRepository.save(newProduct);

                request.getCategoryIds().forEach(categoryId -> {
                    Category category = categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new CustomException(ResCode.CATEGORY_NOT_FOUND));

                    ProductCategory productCategory = ProductCategory.of(newProduct, category);
                    productCategoryRepository.save(productCategory);

                });

                request.getHashTagNames().forEach(hashTagName -> {
                    HashTag hashTag = hashTagRepository.findByName(hashTagName)
                            .orElseThrow(() -> new CustomException(ResCode.HASH_TAG_NOT_FOUND));

                    ProductHashTag productHashTag = ProductHashTag.of(newProduct, hashTag);
                    productHashTagRepository.save(productHashTag);
                });

                result.add(new ProdAdminRes.AddProductRes(newProduct.getId()));
            }
        });

        return result;
    }

    @Override
    @Deprecated(since = "삭제 과정에 문제가 있습니다. 추후 수정 후 재배포 하겠습니다.")
    public ProdAdminRes.DeleteProductRes deleteProduct(ProdAdminReq.DeleteProduct req) {

        if(productRepository.existsById(req.getProductId())){
            productEventRepository.deleteAllByProductId(req.getProductId());
            productCategoryRepository.deleteAllByProductId(req.getProductId());
            productHashTagRepository.deleteAllByProductId(req.getProductId());
            productRepository.deleteById(req.getProductId());
        } else {
            throw new CustomException(ResCode.PRODUCT_NOT_FOUND);
        }

        return new ProdAdminRes.DeleteProductRes(req.getProductId(), LocalDateTime.now().toString());
    }
}
