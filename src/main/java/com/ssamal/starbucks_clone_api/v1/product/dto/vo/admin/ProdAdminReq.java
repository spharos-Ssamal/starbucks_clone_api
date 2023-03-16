package com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
        private String size;
        private String season;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddProductReq {

        private AddProductInfo productInfo;
        private List<Long> categoryIds;
        private Long seasonId;
        private Long sizeId;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddCategory {

        private String name;
        private Long parentId;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddOption {

        private String name;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddProductTo {

        private Long menuId;
        private Long productId;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteProduct {

        private Long productId;
    }


}
