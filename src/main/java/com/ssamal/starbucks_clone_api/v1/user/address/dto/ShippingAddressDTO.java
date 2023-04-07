package com.ssamal.starbucks_clone_api.v1.user.address.dto;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.user.address.model.ShippingAddress;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShippingAddressDTO {

    private Long id;
    private String alias;
    private String recipient;
    private Long zipCode;
    private String baseAddress;
    private String detailAddress;
    private String contactInfo1;
    private String contactInfo2;
    private String shippingMemo;
    private boolean isDefaultAddress;

    public static ShippingAddressDTO of(ShippingAddress entity) {
        return ModelMapperUtils.getModelMapper().map(entity, ShippingAddressDTO.class);
    }
}
