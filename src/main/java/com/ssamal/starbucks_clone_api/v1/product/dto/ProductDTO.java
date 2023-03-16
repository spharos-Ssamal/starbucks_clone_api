package com.ssamal.starbucks_clone_api.v1.product.dto;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String thumbnail;

    public static ProductDTO of(Product entity) {
        return ModelMapperUtils.getModelMapper().map(entity, ProductDTO.class);
    }

    public static List<ProductDTO> of(List<Product> entities) {
        return entities.stream().map(ProductDTO::of).toList();
    }

}
