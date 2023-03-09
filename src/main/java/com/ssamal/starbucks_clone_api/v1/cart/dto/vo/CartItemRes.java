package com.ssamal.starbucks_clone_api.v1.cart.dto.vo;

import com.ssamal.starbucks_clone_api.v1.product.dto.ProductDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemRes {
    private Long id;
    private ProductDTO product;
    private int count;
}
