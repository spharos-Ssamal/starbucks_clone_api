package com.ssamal.starbucks_clone_api.v1.product.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDTO {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String thumbnail;
}
