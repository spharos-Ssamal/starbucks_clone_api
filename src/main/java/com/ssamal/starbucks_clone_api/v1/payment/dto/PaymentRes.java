package com.ssamal.starbucks_clone_api.v1.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class PaymentRes {

    private PaymentRes() {
        throw new IllegalStateException("Data class");
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PurchaseRes {
        private Long purchaseId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserHistoryRes {
        private LocalDate date;
        private List<PaymentDTO.ProductInfo> productInfoList;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeliveryStatusRes {
        private Integer productReady;
        private Integer shippingReady;
        private Integer shipping;
        private Integer complete;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HistoryDetailInfo{
        private String purchaseId;
        private List<PaymentDTO.ProductInfo> productInfoList;
        private String address;
        private String message;
        private LocalDate date;
        private LocalDate canceledDate;
        private Integer price;
        private Integer discountPrice;
        private Integer shippingPrice;
        private boolean isCanceled;

    }
}
