package com.ssamal.starbucks_clone_api.v1.cart.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.cart.service.CartItemService;
import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartItemRes;
import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartItemReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1")
public class CartController {

    private final CartItemService cartItemService;

    @PostMapping("/cart/insert")
    public ResponseEntity<BaseRes<Long>> cartInsert(
        @RequestBody CartItemReq cartItemReq) {
        Long res = cartItemService.createCartItem(cartItemReq);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/cart/getList")
    public ResponseEntity<BaseRes<List<CartItemRes>>> getCartItemList(
        @RequestParam(name = "userId", defaultValue = "") UUID userId) {
        List<CartItemRes> res = cartItemService.getCartItemList(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/cart/update")
    public ResponseEntity<BaseRes<Long>> cartUpdate(
        @RequestParam(name = "cartId", defaultValue = "") Long cartId,
        @RequestParam(name = "count", defaultValue = "") int count) {
        Long res = cartItemService.updateCartItem(cartId, count);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/cart/delete")
    public ResponseEntity<BaseRes<Long>> cartDelete(
        @RequestParam(name = "cartId", defaultValue = "") Long cartId) {
        Long res = cartItemService.deleteCartItem(cartId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
