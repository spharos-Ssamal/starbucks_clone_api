package com.ssamal.starbucks_clone_api.v1.user.cart.service;



import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartReq.CartItemReq;
import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartReq.RemoveCartItemReq;
import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartRes.CartItemRes;
import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartRes.RemoveCartRes;
import java.util.List;
import java.util.UUID;

public interface CartItemService {

    Long createCartItem(CartItemReq req);

    Integer getUsersCartItemAmount(UUID userId);

    List<CartItemRes> getCartItemList(UUID userId);

    Long updateCartItem(Long cartId, int count);

    Long deleteCartItem(Long cartId);
    RemoveCartRes removeCartItems(RemoveCartItemReq req);
}
