package com.ssamal.starbucks_clone_api.v1.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentDTO {

    private PaymentDTO() {
        throw new IllegalStateException("Data Class");
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductBePurchased {
        private int productId;
        private int count;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductInfo {
        private Long productId;
        private String thumbnail;
        private Integer price;
        private Integer count;
    }

}
