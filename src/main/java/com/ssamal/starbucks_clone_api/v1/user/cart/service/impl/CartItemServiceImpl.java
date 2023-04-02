package com.ssamal.starbucks_clone_api.v1.user.cart.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartReq.CartItemReq;
import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartReq.RemoveCartItemReq;
import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartRes.CartItemRes;
import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartRes.RemoveCartRes;
import com.ssamal.starbucks_clone_api.v1.user.cart.entity.CartItem;
import com.ssamal.starbucks_clone_api.v1.user.cart.repository.CartItemRepository;
import com.ssamal.starbucks_clone_api.v1.user.cart.service.CartItemService;
import com.ssamal.starbucks_clone_api.v1.user.options.model.mapping.repository.ProductOptionsRepository;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.ProductDTO;
import com.ssamal.starbucks_clone_api.v1.user.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.user.product.model.repository.ProductRepository;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.model.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.model.repository.ServiceUserRepository;
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
    private final ProductOptionsRepository productOptionsRepository;

    @Override
    public Long createCartItem(CartItemReq req) {

        if (req.getCount() < 1) {
            throw new CustomException(ResCode.INVALID_CART_REQUEST);
        }

        ServiceUser user = userRepository.findById(req.getUserId())
            .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        Product product = productRepository.findById(req.getProductId())
            .orElseThrow(() -> new CustomException(ResCode.PRODUCT_NOT_FOUND));

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
                .build();

        }
        cartItemRepository.save(item);
        return item.getId();
    }

    @Override
    public Integer getUsersCartItemAmount(UUID userId) {
        return cartItemRepository.findByUserIdAndIsDeleted(userId, false).size();
    }

    @Override
    @Transactional
    public List<CartItemRes> getUsersCartItemList(UUID userId) {

        List<CartItem> cartItemList = cartItemRepository.findByUserIdAndIsDeleted(userId, false);

        return cartItemList.stream().map(t -> CartItemRes.builder()
            .id(t.getId())
            .product(ProductDTO.of(t.getProduct()))
            .count(t.getCount())
            .check(false)
            .isFrozen(
                productOptionsRepository.existsByCategoryIdAndProductId(2L, t.getProduct().getId()))
            .build()).toList();
    }

    @Override
    public CartItemRes getCartItem(Long cartId) {
        CartItem result = cartItemRepository.findById(cartId)
            .orElseThrow(() -> new CustomException(ResCode.CART_ITEM_NOT_FOUND));
        return CartItemRes.builder()
            .id(result.getId())
            .product(ProductDTO.of(result.getProduct()))
            .count(result.getCount())
            .check(false)
            .isFrozen(productOptionsRepository.existsByCategoryIdAndProductId(2L,
                result.getProduct().getId()))
            .build();
    }

    @Override
    public List<CartItemRes> getCartItemList(List<Long> cartIds) {
        return cartIds.stream()
            .map(id -> {
                CartItem result = cartItemRepository.findById(id)
                    .orElseThrow(() -> new CustomException(ResCode.CART_ITEM_NOT_FOUND));
                return CartItemRes.builder()
                    .id(result.getId())
                    .product(ProductDTO.of(result.getProduct()))
                    .count(result.getCount())
                    .check(false)
                    .isFrozen(productOptionsRepository.existsByCategoryIdAndProductId(2L,
                        result.getProduct().getId()))
                    .build();
            })
            .toList();
    }

    @Override
    public Long updateCartItem(Long cartId, int count) {

        CartItem cartItem = cartItemRepository.findById(cartId)
            .orElseThrow(() -> new CustomException(ResCode.CART_ITEM_NOT_FOUND));

        if (cartItem.isDeleted()) {
            throw new CustomException(ResCode.BAD_REQUEST);
        } else {
            if (count <= 0) {
                throw new CustomException(ResCode.INVALID_CART_REQUEST);
            }

            cartItem.updateCountValue(count);
            cartItemRepository.save(cartItem);

            return cartItem.getId();
        }
    }

    @Override
    public Long deleteCartItem(Long cartId) {

        CartItem cartItem = cartItemRepository.findByIdAndIsDeleted(cartId, false)
            .orElseThrow(() -> new CustomException(ResCode.CART_ITEM_NOT_FOUND));

        cartItem.setDeleted(true);
        cartItemRepository.save(cartItem);

        return cartId;
    }

    @Override
    public RemoveCartRes removeCartItems(RemoveCartItemReq req) {
        return new RemoveCartRes(req.getCartItemIds().stream().map(itemId -> {
            CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new CustomException(ResCode.CART_ITEM_NOT_FOUND));
            item.deleteCartItem();
            cartItemRepository.save(item);
            return item.getId();
        }).toList());
    }
}



