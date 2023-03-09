package com.ssamal.starbucks_clone_api.v1.cart.dto;

import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import lombok.*;
import org.springframework.security.core.userdetails.User;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class CartItemDto {

    private Long id;

    private ServiceUser user;

    private Product product;

    private int count;

    public CartItemDto(ServiceUser user, Product product, int count) {
        this.user = user;
        this.product = product;
        this.count = count;
    }
//    public static CartItem createCartItem(CartItemDto cartItem){
//        CartItem cartItemItem = CartItem
//                .builder()
//                .product(cartItem.getProduct())
//                .count(cartItem.count)
//                .user(cartItem.getUser())
//                .build();
//        return cartItemItem;
//    }
}
