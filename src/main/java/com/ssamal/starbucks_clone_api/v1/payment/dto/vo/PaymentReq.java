package com.ssamal.starbucks_clone_api.v1.payment.dto.vo;

import com.ssamal.starbucks_clone_api.v1.payment.dto.PaymentDTO.ProductBePurchased;
import com.ssamal.starbucks_clone_api.v1.payment.enums.PaymentMethod;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class PaymentReq {

    private PaymentReq() {
        throw new IllegalStateException("Data class");
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PurchasedInfo {

        private UUID userId;
        private List<ProductBePurchased> purchasedList;
        private PaymentMethod method;
        private Long addressId;
        private String recipient;
        private String message;
    }

}
