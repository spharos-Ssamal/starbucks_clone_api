package com.ssamal.starbucks_clone_api.v1.admin.product.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo.ProdAdminReq.AddImageReq;
import com.ssamal.starbucks_clone_api.v1.admin.product.service.ProductAdminService;
import com.ssamal.starbucks_clone_api.v1.service.cart.repository.CartItemRepository;
import com.ssamal.starbucks_clone_api.v1.service.category.model.Category;
import com.ssamal.starbucks_clone_api.v1.service.category.model.repository.CategoryRepository;
import com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.service.options.model.mapping.repository.ProductHashTagRepository;
import com.ssamal.starbucks_clone_api.v1.service.payment.model.repository.PurchaseProductsRepository;
import com.ssamal.starbucks_clone_api.v1.service.options.model.mapping.ProductOptions;
import com.ssamal.starbucks_clone_api.v1.service.evntsrcmnd.model.mapping.repository.ProductEventRepository;
import com.ssamal.starbucks_clone_api.v1.service.options.model.mapping.repository.ProductOptionsRepository;
import com.ssamal.starbucks_clone_api.v1.service.options.model.Season;
import com.ssamal.starbucks_clone_api.v1.service.options.model.Size;
import com.ssamal.starbucks_clone_api.v1.service.options.model.repository.SeasonRespository;
import com.ssamal.starbucks_clone_api.v1.service.options.model.repository.SizeRepository;

import com.ssamal.starbucks_clone_api.v1.service.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.service.product.model.ProductDetailImage;
import com.ssamal.starbucks_clone_api.v1.service.product.model.repository.ProductDetailImageRepository;
import com.ssamal.starbucks_clone_api.v1.service.product.model.repository.ProductRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductAdminServiceImpl implements ProductAdminService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SeasonRespository seasonRespository;
    private final SizeRepository sizeRepository;
    private final ProductEventRepository productEventRepository;
    private final ProductOptionsRepository productOptionsRepository;
    private final ProductDetailImageRepository productDetailImageRepository;
    private final ProductHashTagRepository productHashTagRepository;
    private final CartItemRepository cartItemRepository;
    private final PurchaseProductsRepository purchaseProductsRepository;

    @Override
    @Transactional
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

                request.getImages().forEach(imageUrl -> {
                    ProductDetailImage image = ProductDetailImage.of(newProduct, imageUrl);
                    productDetailImageRepository.save(image);
                });

                result.add(new ProdAdminRes.AddProductRes(newProduct.getId()));
            }
        });

        return result;
    }

    @Override
    public List<Long> addProductDetailImages(AddImageReq req) {
        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(() -> new CustomException(ResCode.PRODUCT_NOT_FOUND));

        return req.getImageUrls().stream().map(imageUrl -> {
            ProductDetailImage image = ProductDetailImage.of(product, imageUrl);
            productDetailImageRepository.save(image);
            return image.getId();
        }).toList();
    }

    @Override
    public List<Long> updateProductAndProductDetailImages(ProdAdminReq.UpdateProductInfo req) {
        Long id = req.getProductDTO().getId();
        Product product = Product.of(req.getProductDTO());
        productRepository.save(product);
        productDetailImageRepository.deleteAllByProductId(id);
        return req.getImageUrls().stream().map(imageUrl -> {
            ProductDetailImage image = ProductDetailImage.of(product, imageUrl);
            productDetailImageRepository.save(image);
            return image.getId();
        }).toList();
    }

    @Override
    @Deprecated(since = "삭제 과정에 문제가 있습니다. 추후 수정 후 재배포 하겠습니다.")
    public ProdAdminRes.DeleteProductRes deleteProduct(ProdAdminReq.DeleteProduct req) {

        if (productRepository.existsById(req.getProductId())) {
            productEventRepository.deleteAllByProductId(req.getProductId());
            productOptionsRepository.deleteAllByProductId(req.getProductId());
            productHashTagRepository.deleteAllByProductId(req.getProductId());
            productDetailImageRepository.deleteAllByProductId(req.getProductId());
            cartItemRepository.deleteAllByProductId(req.getProductId());
            purchaseProductsRepository.deleteAllByProductId(req.getProductId());
            productRepository.deleteById(req.getProductId());
        } else {
            throw new CustomException(ResCode.PRODUCT_NOT_FOUND);
        }

        return new ProdAdminRes.DeleteProductRes(req.getProductId(),
                LocalDateTime.now().toString());
    }
}
