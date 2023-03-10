package com.ssamal.starbucks_clone_api.v1.cart.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartItemReq;
import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartItemRes;
import com.ssamal.starbucks_clone_api.v1.cart.entity.CartItem;
import com.ssamal.starbucks_clone_api.v1.cart.repository.CartItemRepository;
import com.ssamal.starbucks_clone_api.v1.cart.service.CartItemService;
import com.ssamal.starbucks_clone_api.v1.product.dto.ProductDTO;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductRepository;
import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.entity.repository.ServiceUserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ServiceUserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Long createCartItem(CartItemReq req) {

        if (req.getCount() < 1) {
            throw new CustomException(CustomError.INVALID_CART_REQUEST);
        }

        ServiceUser user = userRepository.findById(req.getUserId())
            .orElseThrow(() -> new CustomException(CustomError.USER_NOT_FOUND));
        Product product = productRepository.findById(req.getProductId())
            .orElseThrow(() -> new CustomException(CustomError.PRODUCT_NOT_FOUND));

        Optional<CartItem> cartItem = cartItemRepository.findByProductIdAndIsDeleted(
            req.getProductId(), false);

        CartItem item;
        if (cartItem.isPresent()) {
            item = cartItem.get();
            item.updateCountValue(item.getCount() + req.getCount());
        } else {
            item = CartItem.builder()
                .user(user)
                .product(product)
                .count(req.getCount())
                .isDeleted(false)
                .build();

        }
        cartItemRepository.save(item);
        return item.getId();
    }

    @Override
    @Transactional
    public List<CartItemRes> getCartItemList(UUID userId) {

        List<CartItem> cartItemList = cartItemRepository.findByUserIdAndIsDeleted(userId, false);

        return cartItemList.stream().map(t -> CartItemRes.builder()
            .id(t.getId())
            .product(ProductDTO.DTO.of(t.getProduct()))
            .count(t.getCount())
            .build()).toList();
    }

    @Override
    public Long updateCartItem(Long cartId, int count) {

        CartItem cartItem = cartItemRepository.findById(cartId)
            .orElseThrow(() -> new CustomException(CustomError.CART_ITEM_NOT_FOUND));

        if (count <= 0) {
            throw new CustomException(CustomError.INVALID_CART_REQUEST);
        }

        cartItem.updateCountValue(count);
        cartItemRepository.save(cartItem);

        return cartItem.getId();
    }

    @Override
    public Long deleteCartItem(Long cartId) {

        CartItem cartItem = cartItemRepository.findByIdAndIsDeleted(cartId, false)
            .orElseThrow(() -> new CustomException(CustomError.CART_ITEM_NOT_FOUND));

        cartItem.setDeleted(true);
        cartItemRepository.save(cartItem);

        return cartId;
    }
}



