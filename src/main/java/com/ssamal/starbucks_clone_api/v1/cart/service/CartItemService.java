package com.ssamal.starbucks_clone_api.v1.cart.service;


import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartRes;
import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartReq;

import java.util.List;
import java.util.UUID;

public interface CartItemService {
    Long createCartItem(CartReq req);
    List<CartRes> getCartItemList(UUID userId);
    Long updateCartItem(Long cartId, int count);
    Long deleteCartItem(Long cartId);
}
