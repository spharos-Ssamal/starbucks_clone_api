package com.ssamal.starbucks_clone_api.v1.cart.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartItemReq;
import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartItemRes;
import com.ssamal.starbucks_clone_api.v1.cart.dto.vo.CartReq.UpdateCartReq;
import com.ssamal.starbucks_clone_api.v1.cart.service.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@Tag(name = "장바구니", description = "장바구니 요청 API 문서입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CartController {

    private final CartItemService cartItemService;

    @Operation(summary = "장바구니 생성", description = "장바구니 항목 생성 API 입니다.")
    @PostMapping("/cart/insert")
    public ResponseEntity<BaseRes<Long>> cartInsert(
        @RequestBody CartItemReq cartReq) {
        Long res = cartItemService.createCartItem(cartReq);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
    @Operation(summary = "장바구니 조회", description = "유저 장바구니 전체 조회입니다.")
    @GetMapping("/cart/get")
    public ResponseEntity<BaseRes<List<CartItemRes>>> getCartItemList(
        @RequestParam(name = "userId", defaultValue = "") UUID userId) {
        List<CartItemRes> res = cartItemService.getCartItemList(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "장바구니 항목 업데이트", description = "장바구니 항목 업데이트 API 입니다.")
    @PostMapping("/cart/update")
    public ResponseEntity<BaseRes<Long>> cartUpdate(
        @RequestBody UpdateCartReq req) {
        Long res = cartItemService.updateCartItem(req.getCartId(), req.getCount());
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "장바구니 항목 삭제", description = "장바구니 항목 삭제 API 입니다.")
    @PutMapping("/cart/delete")
    public ResponseEntity<BaseRes<Long>> cartDelete(
        @RequestParam(name = "cartId", defaultValue = "") Long cartId) {
        Long res = cartItemService.deleteCartItem(cartId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
