package com.ssamal.starbucks_clone_api.v1.cart.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartItemReq;
import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartItemRes;
import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartReq.UpdateCartReq;
import com.ssamal.starbucks_clone_api.v1.cart.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CartController {

    private final CartItemService cartItemService;

    @PostMapping("/cart/insert")
    public ResponseEntity<BaseRes<Long>> cartInsert(
        @RequestBody CartItemReq cartReq) {
        Long res = cartItemService.createCartItem(cartReq);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/cart/get")
    public ResponseEntity<BaseRes<List<CartItemRes>>> getCartItemList(
        @RequestParam(name = "userId", defaultValue = "") UUID userId) {
        List<CartItemRes> res = cartItemService.getCartItemList(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PostMapping("/cart/update")
    public ResponseEntity<BaseRes<Long>> cartUpdate(
        @RequestBody UpdateCartReq req) {
        Long res = cartItemService.updateCartItem(req.getCartId(), req.getCount());
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PutMapping("/cart/delete")
    public ResponseEntity<BaseRes<Long>> cartDelete(
        @RequestParam(name = "cartId", defaultValue = "") Long cartId) {
        Long res = cartItemService.deleteCartItem(cartId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
