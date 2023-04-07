package com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class CategoryAdminReq {

    private CategoryAdminReq() {
        throw new IllegalStateException("VO Class");
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddCategory {

        private String name;
        private Boolean isSizable;
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

}
