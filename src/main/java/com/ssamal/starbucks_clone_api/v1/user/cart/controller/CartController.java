package com.ssamal.starbucks_clone_api.v1.user.cart.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartReq.CartItemReq;
import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartReq.DeleteCartReq;
import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartReq.RemoveCartItemReq;
import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartReq.UpdateCartReq;
import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartRes.CartItemRes;
import com.ssamal.starbucks_clone_api.v1.user.cart.dto.vo.CartRes.RemoveCartRes;
import com.ssamal.starbucks_clone_api.v1.user.cart.service.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @Operation(summary = "현재 유저의 장바구니 상품 갯수 조회", description = "현재 유저 장바구니 상품 갯수 조회 API 입니다.")
    @GetMapping("/cart/amount")
    public ResponseEntity<BaseRes<Integer>> getCartAmount(@RequestParam(name = "userId", defaultValue = "") UUID userId) {
        Integer res = cartItemService.getUsersCartItemAmount(userId);
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
    @PutMapping("/cart/update")
    public ResponseEntity<BaseRes<Long>> cartUpdate(
        @RequestBody UpdateCartReq req) {
        Long res = cartItemService.updateCartItem(req.getCartId(), req.getCount());
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "장바구니 항목 삭제", description = "장바구니 항목 삭제 API 입니다.")
    @PutMapping("/cart/delete")
    public ResponseEntity<BaseRes<Long>> cartDelete(
        @RequestBody DeleteCartReq req) {
        Long res = cartItemService.deleteCartItem(req.getCartId());
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "장바구니 데이터 삭제 요청", description = "장바구니 데이터 삭제 요청 API 입니다.")
    @PostMapping("/cart/confirm")
    public ResponseEntity<BaseRes<RemoveCartRes>> confirmCartItemReq(
        @RequestBody RemoveCartItemReq req) {
        RemoveCartRes res = cartItemService.removeCartItems(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }


}
