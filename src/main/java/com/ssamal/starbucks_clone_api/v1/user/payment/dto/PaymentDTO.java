package com.ssamal.starbucks_clone_api.v1.user.payment.dto;

import com.ssamal.starbucks_clone_api.v1.user.payment.model.PurchaseProducts;
import com.ssamal.starbucks_clone_api.v1.user.product.model.Product;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

public class PaymentDTO {

    private PaymentDTO() {
        throw new IllegalStateException("DTO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductBePurchased {

        private Long id;
        private int count;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductInfo {

        private Long productId;
        private String productName;
        private String thumbnail;
        private Integer price;
        private Integer count;

        public static ProductInfo of(Product entity, int count) {
            ModelMapper modelMapper = new ModelMapper();
            ProductInfo result = modelMapper.map(entity, ProductInfo.class);
            result.setCount(count);
            return result;
        }

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserHistory {

        private String historyId;
        private String date;
        private List<ProductInfo> productInfoList;

        public static UserHistory of(String historyId, LocalDate date,
            List<PurchaseProducts> purchaseProducts) {
            List<ProductInfo> productInfo = purchaseProducts.stream()
                .map(t -> ProductInfo.of(t.getProduct(), t.getCount())).toList();
            return new UserHistory(historyId, date.toString(), productInfo);
        }
    }

}
