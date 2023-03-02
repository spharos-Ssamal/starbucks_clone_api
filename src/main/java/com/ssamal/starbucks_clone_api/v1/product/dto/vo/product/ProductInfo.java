package com.ssamal.starbucks_clone_api.v1.product.dto.vo.product;

import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import lombok.*;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInfo {
    private String name;
    private Integer price;
    private String description;
    private String thumbnail;

    public static ProductInfo fromEntity(Product entity){
        return ProductInfo.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .thumbnail(entity.getThumbnail())
                .build();
    }

    public static List<ProductInfo> fromEntities(List<Product> entities){
        return entities.stream().map(ProductInfo::fromEntity).toList();
    }
}
