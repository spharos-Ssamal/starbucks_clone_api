package com.ssamal.starbucks_clone_api.v1.product.dto.vo.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
    private String name;
    private Integer price;
    private String description;
    private String thumbnail;
}
