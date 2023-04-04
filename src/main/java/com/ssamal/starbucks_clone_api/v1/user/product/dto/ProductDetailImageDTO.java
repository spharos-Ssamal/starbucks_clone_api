package com.ssamal.starbucks_clone_api.v1.user.product.dto;

import com.ssamal.starbucks_clone_api.v1.user.product.model.ProductDetailImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDetailImageDTO {

    private Long id;
    private String imageUrl;

    public static ProductDetailImageDTO of(ProductDetailImage productDetailImage) {
        return new ProductDetailImageDTO(productDetailImage.getId(),
            productDetailImage.getImageUrl());
    }

}
