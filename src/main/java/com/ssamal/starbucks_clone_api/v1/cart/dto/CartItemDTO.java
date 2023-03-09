package com.ssamal.starbucks_clone_api.v1.cart.dto;

import com.ssamal.starbucks_clone_api.v1.cart.entity.CartItem;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import lombok.*;
import org.modelmapper.ModelMapper;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class CartItemDTO {

    private Long id;

    private ServiceUser user;

    private Product product;

    private int count;

    private boolean isDeleted;

    public static CartItemDTO of(CartItem entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, CartItemDTO.class);
    }
}
