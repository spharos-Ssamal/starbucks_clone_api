package com.ssamal.starbucks_clone_api.v1.cart.service;


import com.ssamal.starbucks_clone_api.v1.cart.dto.CartItemDto;
import com.ssamal.starbucks_clone_api.v1.cart.vo.CartItemRes;
import com.ssamal.starbucks_clone_api.v1.cart.vo.CartItemReq;

import java.util.List;
import java.util.UUID;

public interface CartItemService {
    void createCartItem(CartItemReq cartItemReq);
    List<CartItemRes> getCartItemList(UUID user_id);
    CartItemDto updateCartItem(Long id, int count);
    String deleteCartItem(Long id);
}
