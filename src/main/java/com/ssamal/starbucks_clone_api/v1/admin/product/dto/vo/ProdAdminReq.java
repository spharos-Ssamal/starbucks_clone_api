package com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo;

import com.ssamal.starbucks_clone_api.v1.user.product.dto.ProductDTO;
import lombok.*;

import java.util.List;

public class ProdAdminReq {

    private ProdAdminReq() {
        throw new IllegalStateException("Utility class");
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddProductInfo {
        private String name;
        private Integer price;
        private String description;
        private String thumbnail;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddProductReq {
        private AddProductInfo productInfo;
        private List<Long> categoryIds;
        private List<String> images;
        private Long seasonId;
        private Long sizeId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddImageReq {
        private Long productId;
        private List<String> imageUrls;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateProductInfo{
        private ProductDTO productDTO;
        private List<String> imageUrls;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteProduct {
        private Long productId;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BestProduct implements Comparable<BestProduct>{
        private Long ProductId;
        private String historyId;
        private Integer count;

        @Override
        public int compareTo(BestProduct bestProduct){
            if(bestProduct.count<count){
                return 1;
            }else if(bestProduct.count>count){
                return -1;
            }
            return 0;
        }
    }

}
