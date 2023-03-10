package com.ssamal.starbucks_clone_api.v1.payment.dto.vo;

import com.ssamal.starbucks_clone_api.v1.payment.dto.PaymentDTO.ProductInfo;
import com.ssamal.starbucks_clone_api.v1.payment.dto.PaymentDTO.UserHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
        private String historyId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserHistoryRes {
        private List<UserHistory> histories;

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
    @Builder
    public static class HistoryDetailInfo{
        private String purchaseHistoryId;
        private List<ProductInfo> productInfoList;
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
