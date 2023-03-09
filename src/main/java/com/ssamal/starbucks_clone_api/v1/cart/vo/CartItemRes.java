package com.ssamal.starbucks_clone_api.v1.cart.vo;

import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemRes {
    private Product product;
    private int count;
}
