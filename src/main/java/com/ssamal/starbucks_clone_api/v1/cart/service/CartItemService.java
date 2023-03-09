package com.ssamal.starbucks_clone_api.v1.cart.service;


import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartItemRes;
import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartItemReq;

import java.util.List;
import java.util.UUID;

public interface CartItemService {
    Long createCartItem(CartItemReq req);
    List<CartItemRes> getCartItemList(UUID userId);
    Long updateCartItem(Long cartId, int count);
    Long deleteCartItem(Long cartId);
}
