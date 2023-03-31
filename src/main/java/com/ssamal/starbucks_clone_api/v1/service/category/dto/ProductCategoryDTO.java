package com.ssamal.starbucks_clone_api.v1.service.category.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDTO {

    private Long id;
    private Long categoryId;
    private Long productId;
}
