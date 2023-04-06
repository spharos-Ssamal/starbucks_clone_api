package com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.user.address.dto.ShippingAddressDTO;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.PaymentDTO.ProductInfo;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.PaymentDTO.UserHistory;
import com.ssamal.starbucks_clone_api.v1.user.payment.model.PurchaseHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class PaymentRes {

    private PaymentRes() {
        throw new IllegalStateException("VO class");
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
        private ShippingAddressDTO addressInfo;
        private String regTime;
        private String updateTime;
        private Integer purchasePrice;
        private Integer shippingFee;
        private String paymentMethod;
        private Integer discountPrice;
        private Integer totalPrice;
        private boolean isCanceled;

        public static HistoryDetailInfo of(PurchaseHistory history,
            List<ProductInfo> productInfoList) {
            HistoryDetailInfo result = ModelMapperUtils.getModelMapper()
                .map(history, HistoryDetailInfo.class);
            result.setProductInfoList(productInfoList);
            result.setAddressInfo(ShippingAddressDTO.of(history.getShippingAddress()));
            return result;
        }

    }
}
