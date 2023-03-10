package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.product.model.Category;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.product.model.ProductCategory;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.CategoryRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductCategoryRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductRepository;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProdAdminRes.AddMenuRes> addCategory(List<ProdAdminReq.AddCategory> req) {
        List<ProdAdminRes.AddMenuRes> response = new ArrayList<>();

        req.forEach(request -> {
            if (categoryRepository.existsByName(request.getName())) {
                throw new CustomException(CustomError.DUPLICATED_CATEGORY_NAME);
            } else {
                Category newCategory = Category.builder()
                    .name(request.getName())
                    .type(request.getType())
                    .build();
                categoryRepository.save(newCategory);
                response.add(new ProdAdminRes.AddMenuRes(newCategory.getId()));
            }
        });

        return response;
    }

    @Override
    public ProdAdminRes.AddProductToMenuRes addProductToCategory(ProdAdminReq.AddProductTo req) {
        Product product = productRepository.findById(req.getProductId())
            .orElseThrow(() -> new CustomException(CustomError.PRODUCT_NOT_FOUND));
        Category category = categoryRepository.findById(req.getMenuId())
            .orElseThrow(() -> new CustomException(CustomError.CATEGORY_NOT_FOUND));
        ProductCategory productCategory = ProductCategory.builder()
            .product(product)
            .category(category)
            .build();
        productCategoryRepository.save(productCategory);
        return new ProdAdminRes.AddProductToMenuRes(product.getId(), category.getId());
    }
}
