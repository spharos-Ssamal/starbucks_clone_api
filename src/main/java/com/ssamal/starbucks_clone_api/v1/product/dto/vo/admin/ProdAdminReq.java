package com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin;

import com.ssamal.starbucks_clone_api.v1.product.dto.ProductDTO;
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
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddProductReq {

        private ProductDTO productDTO;
        private List<Long> categoryIds;
        private List<String> hashTagNames;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddCategory {

        private String name;
        private String type;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddHashTag {

        private String name;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddEvent {

        private String name;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddRecommend {

        private String name;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddSeason {

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
