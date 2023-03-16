package com.ssamal.starbucks_clone_api.v1.product.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.product.model.*;
import com.ssamal.starbucks_clone_api.v1.product.model.mapping.ProductOptions;
import com.ssamal.starbucks_clone_api.v1.product.model.mapping.repository.ProductEventRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.mapping.repository.ProductOptionsRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.*;
import com.ssamal.starbucks_clone_api.v1.product.service.ProductAdminService;
import java.util.Optional;
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
    private final SeasonRespository seasonRespository;
    private final SizeRepository sizeRepository;
    private final ProductEventRepository productEventRepository;
    private final ProductOptionsRepository productOptionsRepository;

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

                    ProductOptions option = new ProductOptions();

                    Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new CustomException(ResCode.CATEGORY_NOT_FOUND));

                    option.setProduct(newProduct);
                    option.setCategory(category);

                    if (request.getSeasonId() != 0) {
                        Optional<Season> season = seasonRespository.findById(request.getSeasonId());
                        season.ifPresent(option::setSeason);
                    }

                    if (request.getSizeId() != 0) {
                        Optional<Size> size = sizeRepository.findById(request.getSizeId());
                        size.ifPresent(option::setSize);
                    }

                    productOptionsRepository.save(option);

                });
                result.add(new ProdAdminRes.AddProductRes(newProduct.getId()));
            }
        });

        return result;
    }

    @Override
    @Deprecated(since = "삭제 과정에 문제가 있습니다. 추후 수정 후 재배포 하겠습니다.")
    public ProdAdminRes.DeleteProductRes deleteProduct(ProdAdminReq.DeleteProduct req) {

        if (productRepository.existsById(req.getProductId())) {
            productEventRepository.deleteAllByProductId(req.getProductId());
            productRepository.deleteById(req.getProductId());
        } else {
            throw new CustomException(ResCode.PRODUCT_NOT_FOUND);
        }

        return new ProdAdminRes.DeleteProductRes(req.getProductId(),
            LocalDateTime.now().toString());
    }
}
