package com.ssamal.starbucks_clone_api.v1.product.dto;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDTO {
    private Long id;
    private Long categoryId;
    private Long productId;
}
