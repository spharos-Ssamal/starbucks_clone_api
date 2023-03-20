package com.ssamal.starbucks_clone_api.v1.address.dto;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.address.model.ShippingAddress;
import lombok.*;


public class ShippingAddressDTO {

    private ShippingAddressDTO() {
        throw new IllegalStateException("DTO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DTO {

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

        public static DTO of(ShippingAddress entity) {
            return ModelMapperUtils.getModelMapper().map(entity, DTO.class);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Info {

        private String alias;
        private String recipient;
        private Long zipCode;
        private String baseAddress;
        private String detailAddress;
        private String contactInfo1;
        private String contactInfo2;
        private String shippingMemo;
        private boolean isDefaultAddress;

        public static Info of(ShippingAddress entity) {
            return ModelMapperUtils.getModelMapper().map(entity, Info.class);
        }
    }

}