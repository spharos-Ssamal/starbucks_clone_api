package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.product.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.product.model.*;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.*;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final EventRepository eventRepository;
    private final ProductEventRepository productEventRepository;
    private final HashTagRepository hashTagRepository;
    private final ProductHashTagRepository productHashTagRepository;
    private final RecommandRepository recommandRepository;
    private final ProductRecommandRepository productRecommandRepository;
    @Override
    public List<ProdAdminRes.AddProductRes> addProduct(List<ProdAdminReq.AddProductReq> req) {
        List<ProdAdminRes.AddProductRes> result = new ArrayList<>();
        req.forEach(request -> {
            Product newProduct = Product.fromDTO(request.getProductInfo());
            if (productRepository.existsByName(newProduct.getName())) {
                throw new CustomException(CustomError.DUPLICATE_PRODUCT_NAME);
            } else {
                productRepository.save(newProduct);

                request.getCategoryIds().forEach(categoryId -> {
                    Category category = categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new CustomException(CustomError.CATEGORY_NOT_FOUND));

                    ProductCategory productCategory = ProductCategory.fromEntity(newProduct, category);
                    productCategoryRepository.save(productCategory);

                });

                request.getHashTagNames().forEach(hashTagName -> {
                    HashTag hashTag = hashTagRepository.findByName(hashTagName)
                            .orElseThrow(() -> new CustomException(CustomError.HASH_TAG_NOT_FOUND));

                    ProductHashTag productHashTag = ProductHashTag.fromEntity(newProduct, hashTag);
                    productHashTagRepository.save(productHashTag);
                });

                result.add(new ProdAdminRes.AddProductRes(newProduct.getId()));
            }
        });

        return result;
    }

    @Override
    public List<ProdAdminRes.AddMenuRes> addCategory(List<ProdAdminReq.AddCategory> req) {
        List<ProdAdminRes.AddMenuRes> response = new ArrayList<>();

        req.forEach(request -> {
            if (categoryRepository.existsByName(request.getName())) {
                throw new CustomException(CustomError.DUPLICATE_CATEGORY_NAME);
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

    @Override
    public List<ProdAdminRes.AddMenuRes> addEvent(List<ProdAdminReq.AddEvent> req) {

        List<ProdAdminRes.AddMenuRes> response = new ArrayList<>();

        req.forEach(request -> {

            if (eventRepository.existsByName(request.getName())) {
                throw new CustomException(CustomError.DUPLICATE_EVENT_NAME);
            } else {
                Event newEvent = Event.builder()
                        .name(request.getName())
                        .status(EventStatus.ACTIVE)
                        .build();
                eventRepository.save(newEvent);
                response.add(new ProdAdminRes.AddMenuRes(newEvent.getId()));
            }
        });

        return response;
    }

    @Override
    public ProdAdminRes.AddProductToMenuRes addProductToEvent(ProdAdminReq.AddProductTo req) {
        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(() -> new CustomException(CustomError.PRODUCT_NOT_FOUND));
        Event event = eventRepository.findById(req.getMenuId())
                .orElseThrow(() -> new CustomException(CustomError.EVENT_NOT_FOUND));
        ProductEvent productEvent = ProductEvent.builder()
                .product(product)
                .event(event)
                .build();
        productEventRepository.save(productEvent);
        return new ProdAdminRes.AddProductToMenuRes(product.getId(), event.getId());
    }

    @Override
    public List<ProdAdminRes.AddMenuRes> addRecommand(List<ProdAdminReq.AddRecommand> req) {
        List<ProdAdminRes.AddMenuRes> response = new ArrayList<>();

        req.forEach(request -> {

            if (recommandRepository.existsByName(request.getName())) {
                throw new CustomException(CustomError.DUPLICATE_RECOMMAND_NAME);
            } else {
                Recommand recommand = Recommand.builder()
                        .name(request.getName())
                        .status(EventStatus.ACTIVE)
                        .build();
                recommandRepository.save(recommand);
                response.add(new ProdAdminRes.AddMenuRes(recommand.getId()));
            }
        });

        return response;
    }

    @Override
    public ProdAdminRes.AddProductToMenuRes addProductToRecommand(ProdAdminReq.AddProductTo req) {
        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(() -> new CustomException(CustomError.PRODUCT_NOT_FOUND));
        Recommand recommand = recommandRepository.findById(req.getMenuId())
                .orElseThrow(() -> new CustomException(CustomError.EVENT_NOT_FOUND));
        ProductRecommand productRecommand = ProductRecommand.builder()
                .product(product)
                .recommand(recommand)
                .build();
        productRecommandRepository.save(productRecommand);
        return new ProdAdminRes.AddProductToMenuRes(product.getId(), recommand.getId());
    }

    @Override
    public List<ProdAdminRes.AddMenuRes> addHashTag(List<ProdAdminReq.AddHashTag> req) {
        List<ProdAdminRes.AddMenuRes> response = new ArrayList<>();

        req.forEach(request -> {

            if (hashTagRepository.existsByName(request.getName())) {
                throw new CustomException(CustomError.DUPLICATE_HASHTAG_NAME);
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
                .orElseThrow(() -> new CustomException(CustomError.PRODUCT_NOT_FOUND));
        HashTag hashTag = hashTagRepository.findById(req.getMenuId())
                .orElseThrow(() -> new CustomException(CustomError.HASH_TAG_NOT_FOUND));
        ProductHashTag productHashTag = ProductHashTag.builder()
                .product(product)
                .hashTag(hashTag)
                .build();
        productHashTagRepository.save(productHashTag);
        return new ProdAdminRes.AddProductToMenuRes(product.getId(), hashTag.getId());
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
            throw new CustomException(CustomError.PRODUCT_NOT_FOUND);
        }

        return new ProdAdminRes.DeleteProductRes(req.getProductId(), LocalDateTime.now().toString());
    }
}
