package com.ssamal.starbucks_clone_api.v1.cart.dto;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.cart.entity.CartItem;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class CartItemDTO {

    private Long id;

    private Product product;

    private int count;

    private boolean isDeleted;

    private String regTime;

    public static CartItemDTO of(CartItem entity) {
        return ModelMapperUtils.getModelMapper().map(entity, CartItemDTO.class);
    }
}
