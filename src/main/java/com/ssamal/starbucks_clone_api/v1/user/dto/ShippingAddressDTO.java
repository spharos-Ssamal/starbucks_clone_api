package com.ssamal.starbucks_clone_api.v1.user.dto;

import com.ssamal.starbucks_clone_api.v1.user.entity.ShippingAddress;
import lombok.*;
import org.modelmapper.ModelMapper;


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
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(entity, DTO.class);
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
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(entity, Info.class);
        }
    }

}
