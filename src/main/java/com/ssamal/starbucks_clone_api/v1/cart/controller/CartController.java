package com.ssamal.starbucks_clone_api.v1.cart.controller;

import com.ssamal.starbucks_clone_api.v1.cart.dto.CartItemDto;
import com.ssamal.starbucks_clone_api.v1.cart.service.CartItemService;
import com.ssamal.starbucks_clone_api.v1.cart.vo.CartItemRes;
import com.ssamal.starbucks_clone_api.v1.cart.vo.CartItemReq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1")
public class CartController {
    private final CartItemService cartItemService;

    @PostMapping("/cart/insert")
    public void cartInsert(@RequestBody CartItemReq cartItemReq /*,@AuthenticationPrincipal SecurityUser principal*/){
        cartItemService.createCartItem(cartItemReq);
    }

    @GetMapping("/cart/getList/{user_id}")
    public List<CartItemRes> getCartItemList(@PathVariable("user_id") UUID user_Id){
        return cartItemService.getCartItemList(user_Id);
    }

    @GetMapping("/cart/update")
    public CartItemDto cartUpdate(@RequestParam Long id, @RequestParam int count){
        return cartItemService.updateCartItem(id, count);
    }

    @GetMapping("/cart/delete/{cart_id}")
    public String cartDelete(@PathVariable Long cart_id){
        return cartItemService.deleteCartItem(cart_id);
    }

}
