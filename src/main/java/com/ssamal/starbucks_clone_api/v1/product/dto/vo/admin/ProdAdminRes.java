package com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ProdAdminRes {
    private ProdAdminRes () {
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

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddMenuRes {
        private Long menuId;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddProductToMenuRes {
        private Long productId;
        private Long menuId;
    }


}
