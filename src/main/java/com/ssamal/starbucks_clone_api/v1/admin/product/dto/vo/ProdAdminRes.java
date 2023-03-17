package com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ProdAdminRes {

    private ProdAdminRes() {
        throw new IllegalStateException("Utility class");
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddProductRes {

        private Long productId;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteProductRes {

        private Long productId;
        private String deleteAt;
    }

}
