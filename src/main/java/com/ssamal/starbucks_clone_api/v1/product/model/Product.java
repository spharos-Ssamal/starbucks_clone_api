package com.ssamal.starbucks_clone_api.v1.product.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.product.dto.ProductDTO;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq.AddProductInfo;
import com.ssamal.starbucks_clone_api.v1.product.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(30) NOT NULL")
    private String name;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Integer price;

    @Column(name = "status", columnDefinition = "VARCHAR(10) default 'ON_SALE' ")
    @Enumerated(value = EnumType.STRING)
    private ProductStatus status;

    public static Product of(ProductDTO dto) {
        return ModelMapperUtils.getModelMapper().map(dto, Product.class);
    }

    public static Product of(AddProductInfo request) {
        return ModelMapperUtils.getModelMapper().map(request, Product.class);
    }

}
