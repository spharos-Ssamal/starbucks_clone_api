package com.ssamal.starbucks_clone_api.v1.payment.dto.vo;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.payment.dto.PaymentDTO.ProductInfo;
import com.ssamal.starbucks_clone_api.v1.payment.dto.PaymentDTO.UserHistory;
import com.ssamal.starbucks_clone_api.v1.payment.model.PurchaseHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class HistoryDetailInfo {

        private String purchaseHistoryId;
        private List<ProductInfo> productInfoList;
        private String baseAddress;
        private String detailAddress;
        private String message;
        private String regTime;
        private String updateTime;
        private Integer purchasePrice;
        private Integer discountPrice;
        private Integer totalPrice;
        private boolean isCanceled;

        public static HistoryDetailInfo of(PurchaseHistory history,
            List<ProductInfo> productInfoList) {
            HistoryDetailInfo result = ModelMapperUtils.getModelMapper()
                .map(history, HistoryDetailInfo.class);
            result.setProductInfoList(productInfoList);
            return result;
        }

    }
}
