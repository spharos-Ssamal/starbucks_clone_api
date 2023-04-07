package com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class CategoryAdminRes {

    private CategoryAdminRes() {
        throw new IllegalStateException("VO Class");
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddCategoryRes {

        private Long categoryId;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddProductOptionRes {

        private Long productId;
        private Long optionId;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddOptionRes {

        private Long optionId;
    }

}
