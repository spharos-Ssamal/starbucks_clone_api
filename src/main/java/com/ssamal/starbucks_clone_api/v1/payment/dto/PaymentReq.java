package com.ssamal.starbucks_clone_api.v1.payment.dto;

import com.ssamal.starbucks_clone_api.v1.payment.enums.PaymentMethod;
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
        private List<PaymentDTO.ProductBePurchased> purchasedList;
        private PaymentMethod method;
    }

}
