package com.ssamal.starbucks_clone_api.v1.product.dto;

import com.ssamal.starbucks_clone_api.v1.product.enums.ProductStatus;
import com.ssamal.starbucks_clone_api.v1.product.enums.Season;
import com.ssamal.starbucks_clone_api.v1.product.enums.Size;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
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
    private ProductStatus status;
    private Size size;
    private Season season;

    public static ProductDTO of(Product entity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, ProductDTO.class);
    }

}
